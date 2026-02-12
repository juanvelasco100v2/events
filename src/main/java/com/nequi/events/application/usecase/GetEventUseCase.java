package com.nequi.events.application.usecase;

import com.nequi.events.domain.model.Event;
import com.nequi.events.domain.repository.EventRepository;
import java.util.Optional;

public class GetEventUseCase {
    private final EventRepository eventRepository;

    public GetEventUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Optional<Event> execute(String eventId) {
        return eventRepository.findById(eventId);
    }
}
