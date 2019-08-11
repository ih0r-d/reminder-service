package io.pyxiscode.reminder.api.service;

import io.pyxiscode.reminder.api.domain.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    void createEvent(Event event);

    List<Event> findAllEvents();

    Optional<Event> findEventById(String id);

    void updateEvent(String id, Event event);

    void deleteEvent(String id);
}
