package com.nequi.events.application.usecase;

import com.nequi.events.domain.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteEventUseCaseTest {

    @Mock
    private EventRepository eventRepository;

    private DeleteEventUseCase deleteEventUseCase;

    @BeforeEach
    void setUp() {
        deleteEventUseCase = new DeleteEventUseCase(eventRepository);
    }

    @Test
    void execute_ShouldDeleteEvent() {
        String eventId = "1";

        deleteEventUseCase.execute(eventId);

        verify(eventRepository).deleteById(eventId);
    }
}
