package com.nequi.events.infrastructure.adapter.repository;

import com.nequi.events.domain.model.Event;
import com.nequi.events.infrastructure.adapter.entity.EventEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DynamoDbEventRepositoryTest {

    @Mock
    private DynamoDbEnhancedClient enhancedClient;

    @Mock
    private DynamoDbTable<EventEntity> eventTable;

    private DynamoDbEventRepository repository;

    @BeforeEach
    void setUp() {
        when(enhancedClient.table(eq("events"), any(TableSchema.class))).thenReturn(eventTable);
        repository = new DynamoDbEventRepository(enhancedClient);
    }

    @Test
    void save_ShouldPutItem() {
        Event event = new Event("1", "Concert", LocalDate.now(), "Stadium", 100, 100);

        Event result = repository.save(event);

        assertEquals(event, result);
        verify(eventTable).putItem(any(EventEntity.class));
    }

    @Test
    void findById_ShouldReturnEvent_WhenExists() {
        String eventId = "1";
        EventEntity entity = new EventEntity("1", "Concert", LocalDate.now(), "Stadium", 100, 100);
        when(eventTable.getItem(any(Key.class))).thenReturn(entity);

        Optional<Event> result = repository.findById(eventId);

        assertTrue(result.isPresent());
        assertEquals(eventId, result.get().eventId());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenNotExists() {
        when(eventTable.getItem(any(Key.class))).thenReturn(null);

        Optional<Event> result = repository.findById("missing");

        assertTrue(result.isEmpty());
    }

    @Test
    void findAll_ShouldReturnList() {
        EventEntity entity = new EventEntity("1", "Concert", LocalDate.now(), "Stadium", 100, 100);
        
        PageIterable<EventEntity> mockPageIterable = mock(PageIterable.class);
        SdkIterable<EventEntity> mockSdkIterable = mock(SdkIterable.class);

        when(eventTable.scan()).thenReturn(mockPageIterable);
        when(mockPageIterable.items()).thenReturn(mockSdkIterable);
        when(mockSdkIterable.stream()).thenReturn(Stream.of(entity));

        List<Event> result = repository.findAll();

        assertFalse(result.isEmpty());
        assertEquals("1", result.get(0).eventId());
    }

    @Test
    void deleteById_ShouldDeleteItem() {
        String eventId = "1";

        repository.deleteById(eventId);

        verify(eventTable).deleteItem(any(Key.class));
    }
}
