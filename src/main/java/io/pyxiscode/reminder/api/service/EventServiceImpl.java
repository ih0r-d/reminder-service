package io.pyxiscode.reminder.api.service;

import io.pyxiscode.reminder.api.domain.model.Event;
import io.pyxiscode.reminder.api.domain.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(Event event) {
        if (eventRepository.existsById(event.getId())) {
            log.info("Event by id {}, already exists in the database", event.getId());
        } else {
            eventRepository.save(event);
        }
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findEventById(String id) {
        return eventRepository.findById(id);
    }

    @Override
    public void updateEvent(String id, Event e) {
        eventRepository.findById(id)
                .map(event -> {
                    event.setTitle(e.getTitle());
                    event.setCompleted(e.getCompleted());
                    eventRepository.save(event);
                    return event;
                })
                .orElseThrow(() -> new RuntimeException("Event isn't present for update"));
    }

    @Override
    public void deleteEvent(String id) {
        if (eventRepository.findById(id).isPresent()) {
            eventRepository.deleteById(id);
        } else {
            log.error("Cannot remove event {} ", id);
        }
    }


}
