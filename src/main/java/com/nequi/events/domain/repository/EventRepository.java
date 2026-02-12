package com.nequi.events.domain.repository;

import com.nequi.events.domain.model.Event;
import java.util.List;
import java.util.Optional;

public interface EventRepository {
    Event save(Event event);
    Optional<Event> findById(String eventId);
    List<Event> findAll();
    void deleteById(String eventId);
}
