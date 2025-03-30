package org.example.homework3.service;

import org.example.homework3.model.entity.Request.VenueRequest;
import org.example.homework3.model.entity.Venue;

import java.util.List;
import java.util.Optional;


public interface VenueService {
    List<Venue> getAllVenues(int offset, int size);

    Venue saveVenue(VenueRequest request);

    Venue getVenueById(Long venueId);

    Venue updateVenueById(Long venueId, VenueRequest request);

    Venue deleteVenueById(Long venueId);
}
