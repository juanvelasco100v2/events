package com.nequi.events.application.usecase;

import com.nequi.events.domain.model.Event;
import com.nequi.events.domain.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    private GetAllEventsUseCase getAllEventsUseCase;

    @BeforeEach
    void setUp() {
        getAllEventsUseCase = new GetAllEventsUseCase(eventRepository);
    }

    @Test
    void execute_ShouldReturnListOfEvents() {
        Event event1 = new Event("1", "Concert", LocalDate.now(), "Stadium", 100, 100);
        Event event2 = new Event("2", "Theater", LocalDate.now(), "Hall", 50, 50);
        List<Event> events = List.of(event1, event2);
        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = getAllEventsUseCase.execute();

        assertEquals(events, result);
        verify(eventRepository).findAll();
    }
}
