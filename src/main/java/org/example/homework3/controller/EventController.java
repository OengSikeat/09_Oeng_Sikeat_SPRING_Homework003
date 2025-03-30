package org.example.homework3.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.example.homework3.Exception.NotFoundException;
import org.example.homework3.model.entity.Event;
import org.example.homework3.model.entity.Request.EventRequest;
import org.example.homework3.model.entity.Response.ApiResponse;
import org.example.homework3.model.entity.Venue;
import org.example.homework3.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvent(@Positive int page,@Positive int size){
        int offset = (page-1)*size;
        List<Event> events=eventService.getAllEvents(offset,size);

        ApiResponse<List<Event>> response=ApiResponse.<List<Event>>builder()
                .success(true)
                .message("Get All Events Successfully!")
                .status(HttpStatus.OK)
                .payload(events)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> saveEvent(@Valid @RequestBody EventRequest request){
        Event event=eventService.saveEvent(request);
        if (request.getVenueId()==null){
            throw new NotFoundException("Venue id "+request.getVenueId()+" Not Found");
        }


        ApiResponse<Event> response=ApiResponse.<Event>builder()
                .success(true)
                .message("Created Event Successfully!")
                .status(HttpStatus.CREATED)
                .payload(event)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> getEvent(@PathVariable("event-id") Long eventId){

        Event event=eventService.getEventById(eventId);
        if (event == null){
            throw new NotFoundException("Event id "+eventId+" Not Found");
        }
        ApiResponse<Event> response=ApiResponse.<Event>builder()
                .success(true)
                .message("Get Event "+eventId+" Successfully!")
                .status(HttpStatus.OK)
                .payload(event)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> updateEventById(@Valid @PathVariable("event-id") Long eventId,@RequestBody EventRequest request){
        Event event=eventService.updateEventById(eventId,request);
        if (event == null){
            throw new NotFoundException("Event id "+eventId+" Not Found");
        }
        ApiResponse<Event> response=ApiResponse.<Event>builder()
                .success(true)
                .message("Updated Event "+eventId+" Successfully!")
                .status(HttpStatus.OK)
                .payload(event)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> deleteEventById(@PathVariable("event-id") Long eventId){
        Event event = eventService.deleteEventById(eventId);
        if (event == null){
            throw new NotFoundException("Event id "+eventId+" Not Found");
        }
        ApiResponse<Event> response=ApiResponse.<Event>builder()
                .success(true)
                .message("Deleted Event "+eventId+" Successfully!")
                .status(HttpStatus.OK)
                .payload(event)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
