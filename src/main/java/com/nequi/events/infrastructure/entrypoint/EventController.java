package com.nequi.events.infrastructure.entrypoint;

import com.nequi.events.application.usecase.CreateEventUseCase;
import com.nequi.events.application.usecase.DeleteEventUseCase;
import com.nequi.events.application.usecase.GetAllEventsUseCase;
import com.nequi.events.application.usecase.GetEventUseCase;
import com.nequi.events.domain.model.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final GetEventUseCase getEventUseCase;
    private final GetAllEventsUseCase getAllEventsUseCase;
    private final DeleteEventUseCase deleteEventUseCase;

    public EventController(CreateEventUseCase createEventUseCase,
                           GetEventUseCase getEventUseCase,
                           GetAllEventsUseCase getAllEventsUseCase,
                           DeleteEventUseCase deleteEventUseCase) {
        this.createEventUseCase = createEventUseCase;
        this.getEventUseCase = getEventUseCase;
        this.getAllEventsUseCase = getAllEventsUseCase;
        this.deleteEventUseCase = deleteEventUseCase;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return new ResponseEntity<>(createEventUseCase.execute(event), HttpStatus.CREATED);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable String eventId) {
        return getEventUseCase.execute(eventId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(getAllEventsUseCase.execute());
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String eventId) {
        deleteEventUseCase.execute(eventId);
        return ResponseEntity.noContent().build();
    }
}
