package com.nequi.events.application.usecase;

import com.nequi.events.domain.model.Event;
import com.nequi.events.domain.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateEventUseCaseTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private CreateEventUseCase createEventUseCase;

    @Test
    void shouldCreateEvent() {
        Event event = new Event("1", "Concert", LocalDate.of(2023, 12, 1), "Stadium", 100, 100);
        when(eventRepository.save(event)).thenReturn(event);

        Event createdEvent = createEventUseCase.execute(event);

        assertEquals(event, createdEvent);
        verify(eventRepository).save(event);
    }
}
