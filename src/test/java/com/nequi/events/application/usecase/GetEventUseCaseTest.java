package com.nequi.events.application.usecase;

import com.nequi.events.domain.model.Event;
import com.nequi.events.domain.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetEventUseCaseTest {

    @Mock
    private EventRepository eventRepository;

    private GetEventUseCase getEventUseCase;

    @BeforeEach
    void setUp() {
        getEventUseCase = new GetEventUseCase(eventRepository);
    }

    @Test
    void execute_ShouldReturnEvent_WhenExists() {
        String eventId = "1";
        Event event = new Event(eventId, "Concert", LocalDate.now(), "Stadium", 100, 100);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        Optional<Event> result = getEventUseCase.execute(eventId);

        assertTrue(result.isPresent());
        assertEquals(event, result.get());
        verify(eventRepository).findById(eventId);
    }
}
