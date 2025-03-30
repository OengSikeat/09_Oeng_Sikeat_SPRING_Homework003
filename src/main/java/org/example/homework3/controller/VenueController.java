package org.example.homework3.controller;



import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.example.homework3.Exception.NotFoundException;
import org.example.homework3.model.entity.Request.VenueRequest;
import org.example.homework3.model.entity.Response.ApiResponse;
import org.example.homework3.model.entity.Venue;
import org.example.homework3.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venue")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenue(@Positive int page, @Positive int size){
        int offset=(page-1)*size;
        List<Venue> venues=venueService.getAllVenues(offset,size);
        ApiResponse<List<Venue>> response=ApiResponse.<List<Venue>>builder()
                .success(true)
                .message("Get All Venues Successfully!")
                .status(HttpStatus.OK)
                .payload(venues)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> saveVenue(@RequestBody VenueRequest request){
        Venue venue=venueService.saveVenue(request);

        ApiResponse<Venue> response=ApiResponse.<Venue>builder()
                .success(true)
                .message("Created Venues Successfully!")
                .status(HttpStatus.CREATED)
                .payload(venue)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{venue-id}")
    public ResponseEntity<?> getVenueById(@PathVariable("venue-id") Long venueId) {
        // Directly call the service method without Optional
        Venue venue = venueService.getVenueById(venueId);

        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .success(true)
                .message("Get Venue " + venueId + " Successfully!")
                .status(HttpStatus.OK)
                .payload(venue)
                .instant(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PutMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenueById(@Valid @PathVariable("venue-id") Long venueId, @RequestBody VenueRequest request){
        Venue venue = venueService.updateVenueById(venueId,request);
        if (venue == null){
            throw new NotFoundException("Venue id "+venueId+" Not Found");
        }
        ApiResponse<Venue> response=ApiResponse.<Venue>builder()
                .success(true)
                .message("Updated Venue "+venueId+" Successfully!")
                .status(HttpStatus.OK)
                .payload(venue)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> deleteVenueById(@PathVariable("venue-id") Long venueId){
        Venue venue = venueService.deleteVenueById(venueId);
        if (venue == null){
            throw new NotFoundException("Venue id "+venueId+" Not Found");
        }
        ApiResponse<Venue> response=ApiResponse.<Venue>builder()
                .success(true)
                .message("Deleted Venue "+venueId+" Successfully!")
                .status(HttpStatus.OK)
                .payload(venue)
                .instant(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
