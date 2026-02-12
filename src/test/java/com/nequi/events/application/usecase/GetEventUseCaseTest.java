package com.nequi.events.application.usecase;

import com.nequi.events.domain.model.Event;
import com.nequi.events.domain.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private GetEventUseCase getEventUseCase;

    @Test
    void shouldGetEvent() {
        String eventId = "1";
        Event event = new Event(eventId, "Concert", LocalDate.of(2023, 12, 1), "Stadium", 100, 100);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        Optional<Event> foundEvent = getEventUseCase.execute(eventId);

        assertTrue(foundEvent.isPresent());
        assertEquals(event, foundEvent.get());
        verify(eventRepository).findById(eventId);
    }
}
