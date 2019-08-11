package io.pyxiscode.reminder.api.domain.repository;

import io.pyxiscode.reminder.api.domain.model.Event;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
}
