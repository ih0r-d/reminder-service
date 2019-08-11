package io.pyxiscode.reminder.api.integration;

import io.pyxiscode.reminder.api.domain.model.Event;
import io.pyxiscode.reminder.api.domain.model.Status;
import io.pyxiscode.reminder.api.domain.web.controller.EventController;
import io.pyxiscode.reminder.api.integration.controller.ResourceController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class EventResourceProcessor implements ResourceProcessor<Resource<Event>> {
    @Override
    public Resource<Event> process(Resource<Event> resource) {
        Event event = resource.getContent();
        if (event.getStatus() == Status.NEW) {
            resource.add(linkTo(methodOn(ResourceController.class)
                    .publish(event.getId(), null))
                    .withRel("publishing"));
        }
        if (event.getStatus() == Status.PUBLISHED) {
            resource.add(linkTo(methodOn(ResourceController.class)
                    .expire(event.getId(), null))
                    .withRel("expiration"));
        }

        resource.add(linkTo(methodOn(EventController.class)
                .getEventById(event.getId()))
                .withRel("update"));

        resource.add(linkTo(methodOn(EventController.class)
                .deleteEvent(event.getId()))
                .withRel("delete"));

        return resource;
    }
}
