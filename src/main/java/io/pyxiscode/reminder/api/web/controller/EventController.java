package io.pyxiscode.reminder.api.web.controller;

import io.pyxiscode.reminder.api.domain.model.Event;
import io.pyxiscode.reminder.api.service.EventService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.findAllEvents();
    }

    @PostMapping("/events")
    public void getAllEvents(@Valid @RequestBody Event event) {
        eventService.createEvent(event);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return eventService.findEventById(id)
                .map(e -> ResponseEntity.status(HttpStatus.CREATED).body(e))
                .orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @PatchMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable String id,
                                             @Valid @RequestBody Event event) {
        eventService.updateEvent(id, event);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value="/events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
