package com.nequi.events.infrastructure.adapter.repository;

import com.nequi.events.domain.model.Event;
import com.nequi.events.domain.repository.EventRepository;
import com.nequi.events.infrastructure.adapter.entity.EventEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DynamoDbEventRepository implements EventRepository {

    private final DynamoDbTable<EventEntity> eventTable;

    public DynamoDbEventRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.eventTable = dynamoDbEnhancedClient.table("events", TableSchema.fromBean(EventEntity.class));
    }

    @Override
    public Event save(Event event) {
        EventEntity entity = toEntity(event);
        eventTable.putItem(entity);
        return event;
    }

    @Override
    public Optional<Event> findById(String eventId) {
        EventEntity entity = eventTable.getItem(Key.builder().partitionValue(eventId).build());
        return Optional.ofNullable(entity).map(this::toDomain);
    }

    @Override
    public List<Event> findAll() {
        return eventTable.scan().items().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String eventId) {
        eventTable.deleteItem(Key.builder().partitionValue(eventId).build());
    }

    private EventEntity toEntity(Event event) {
        return new EventEntity(
                event.eventId(),
                event.name(),
                event.date(),
                event.location(),
                event.totalCapacity(),
                event.availableCapacity()
        );
    }

    private Event toDomain(EventEntity entity) {
        return new Event(
                entity.getEventId(),
                entity.getName(),
                entity.getDate(),
                entity.getLocation(),
                entity.getTotalCapacity(),
                entity.getAvailableCapacity()
        );
    }
}
