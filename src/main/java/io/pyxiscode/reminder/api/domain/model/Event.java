package io.pyxiscode.reminder.api.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Document(collection = "events")
@JsonIgnoreProperties(value = {"createAt"}, allowGetters = true)
@Data
public class Event {

    @Id
    private String id;

    @NotBlank
    @Size(max = 120, message = "Title should be less 120 characters")
    @Indexed(unique = true)
    private String title;
    
    private Boolean completed = false;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    private LocalDate createAt = LocalDate.now();

}
