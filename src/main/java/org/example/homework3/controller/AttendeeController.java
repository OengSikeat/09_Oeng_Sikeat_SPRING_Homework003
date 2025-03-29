package org.example.homework3.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import org.example.homework3.Exception.NotFoundException;
import org.example.homework3.model.entity.Attendee;
import org.example.homework3.model.entity.Event;
import org.example.homework3.model.entity.Request.AttendeeRequest;
import org.example.homework3.model.entity.Response.ApiResponse;
import org.example.homework3.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendee")
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendee(@Positive int page, @Positive int size){
        int offset = (page-1)*size;
        List<Attendee> attendees=attendeeService.getAllAttendees(offset,size);
        ApiResponse<List<Attendee>> response=ApiResponse.<List<Attendee>>builder()
                .success(true)
                .message("Get All Attendees Successfully!")
                .status(HttpStatus.OK)
                .payload(attendees)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> saveAttendee(@Valid @RequestBody AttendeeRequest request){
        Attendee attendee=attendeeService.saveAttendee(request);
        ApiResponse<Attendee> response=ApiResponse.<Attendee>builder()
                .success(true)
                .message("Saved Attendee Successfully!")
                .status(HttpStatus.CREATED)
                .payload(attendee)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") Long attendeeId){
        Attendee attendee=attendeeService.getAttendeeById(attendeeId);
        if (attendee== null){
            throw new NotFoundException("Attendee id "+attendeeId+" Not Found");
        }
        ApiResponse<Attendee> response=ApiResponse.<Attendee>builder()
                .success(true)
                .message("Get Attendee "+attendeeId+" Successfully!")
                .status(HttpStatus.OK)
                .payload(attendee)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(@Valid @PathVariable("attendee-id") Long attendeeId, @RequestBody AttendeeRequest request){
        Attendee attendee = attendeeService.updateAttendeeById(attendeeId,request);
        if (attendee== null){
            throw new NotFoundException("Attendee id "+attendeeId+" Not Found");
        }
        ApiResponse<Attendee> response=ApiResponse.<Attendee>builder()
                .success(true)
                .message("Updated Attendee "+attendeeId+" Successfully!")
                .status(HttpStatus.OK)
                .payload(attendee)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> deleteAttendeeById(@PathVariable("attendee-id") Long attendeeId){
        Attendee attendee=attendeeService.deleteAttendeeById(attendeeId);
        if (attendee== null){
            throw new NotFoundException("Attendee id "+attendeeId+" Not Found");
        }
        ApiResponse<Attendee> response=ApiResponse.<Attendee>builder()
                .success(true)
                .message("Deleted Attendee "+attendeeId+" Successfully!")
                .status(HttpStatus.OK)
                .payload(attendee)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
