package com.nequi.events.application.usecase;

import com.nequi.events.domain.repository.EventRepository;

public class DeleteEventUseCase {
    private final EventRepository eventRepository;

    public DeleteEventUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void execute(String eventId) {
        eventRepository.deleteById(eventId);
    }
}
