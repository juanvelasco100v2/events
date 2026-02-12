package com.nequi.events.application.usecase;

import com.nequi.events.domain.model.Event;
import com.nequi.events.domain.repository.EventRepository;
import java.util.List;

public class GetAllEventsUseCase {
    private final EventRepository eventRepository;

    public GetAllEventsUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> execute() {
        return eventRepository.findAll();
    }
}
