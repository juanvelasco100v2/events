package com.nequi.events.application.usecase;

import com.nequi.events.domain.model.Event;
import com.nequi.events.domain.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllEventsUseCaseTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private GetAllEventsUseCase getAllEventsUseCase;

    @Test
    void shouldGetAllEvents() {
        Event event1 = new Event("1", "Concert", LocalDate.of(2023, 12, 1), "Stadium", 100, 100);
        Event event2 = new Event("2", "Festival", LocalDate.of(2023, 12, 2), "Park", 200, 200);
        List<Event> events = List.of(event1, event2);
        when(eventRepository.findAll()).thenReturn(events);

        List<Event> foundEvents = getAllEventsUseCase.execute();

        assertEquals(events, foundEvents);
        verify(eventRepository).findAll();
    }
}
