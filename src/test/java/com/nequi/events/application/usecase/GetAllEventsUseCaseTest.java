package com.nequi.events.application.usecase;

import com.nequi.events.domain.model.Event;
import com.nequi.events.domain.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        Event event1 = new Event("1", "Concert", "2023-12-01", "Stadium");
        Event event2 = new Event("2", "Festival", "2023-12-02", "Park");
        List<Event> events = List.of(event1, event2);
        when(eventRepository.findAll()).thenReturn(events);

        List<Event> foundEvents = getAllEventsUseCase.execute();

        assertEquals(events, foundEvents);
        verify(eventRepository).findAll();
    }
}
