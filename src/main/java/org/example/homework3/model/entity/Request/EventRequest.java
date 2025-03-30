package org.example.homework3.model.entity.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.homework3.model.entity.Venue;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    @NotNull
    @NotBlank
    private String eventName;


    private Date eventDate;


    private Long venueId;


    private List<Long> attendeesId;
}
