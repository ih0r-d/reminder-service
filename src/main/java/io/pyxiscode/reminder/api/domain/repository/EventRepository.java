package io.pyxiscode.reminder.api.domain.repository;

import io.pyxiscode.reminder.api.domain.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    @Query("{status:'PUBLISHED'}")
    @RestResource(path = "published")
    List<Event> findAllPublishedEvents();

    @Query("{status:'EXPIRED'}")
    @RestResource(path = "expired")
    List<Event> findAllExpiredEvents();
}
