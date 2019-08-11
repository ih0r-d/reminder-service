package io.pyxiscode.reminder.api.domain.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.pyxiscode.reminder.api.domain.exception.InvalidEventStateException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Document(collection = "events")
@JsonIgnoreProperties(value = {"createAt"}, allowGetters = true)
@Getter
@Setter
public class Event {

    @Id
    private String id;

    @NotBlank
    @Size(max = 120, message = "Title should be less 120 characters")
    @Indexed(unique = true)
    private String title;

    @JsonEnumDefaultValue
    private Status status = Status.NEW;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    private LocalDateTime createAt = LocalDateTime.now();

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    private LocalDateTime publishedAt;

    public void publish() {
        if (status == Status.NEW) {
            publishedAt = LocalDateTime.now();
            status = Status.PUBLISHED;
        } else {
            throw new InvalidEventStateException("Event is already published");
        }
    }

    public void expire() {
        if (status == Status.PUBLISHED) {
            status = Status.EXPIRED;
        } else {
            throw new InvalidEventStateException("The event can be finished only when it's in the published state" );
        }
    }

}
