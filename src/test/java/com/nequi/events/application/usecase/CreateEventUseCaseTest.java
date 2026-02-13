package com.nequi.events.application.usecase;

import com.nequi.events.domain.model.Event;
import com.nequi.events.domain.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    private CreateEventUseCase createEventUseCase;

    @BeforeEach
    void setUp() {
        createEventUseCase = new CreateEventUseCase(eventRepository);
    }

    @Test
    void execute_ShouldSaveAndReturnEvent() {
        Event event = new Event("1", "Concert", LocalDate.now(), "Stadium", 100, 100);
        when(eventRepository.save(event)).thenReturn(event);

        Event result = createEventUseCase.execute(event);

        assertEquals(event, result);
        verify(eventRepository).save(event);
    }
}
