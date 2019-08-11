package io.pyxiscode.reminder.api.integration.service;

import io.pyxiscode.reminder.api.domain.model.Event;
import io.pyxiscode.reminder.api.domain.repository.EventRepository;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.stereotype.Service;

@Service
public class EventResourceService {

    private final EventRepository eventRepository;

    public EventResourceService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public PersistentEntityResource publishResource(String id, PersistentEntityResourceAssembler asm) {
        Event event = eventRepository.findById(id).get();
        event.publish();
        return asm.toFullResource(eventRepository.save(event));
    }

    public PersistentEntityResource expireResource(String id, PersistentEntityResourceAssembler asm) {
        Event event = eventRepository.findById(id).get();
        event.expire();
        return asm.toFullResource(eventRepository.save(event));
    }
}
