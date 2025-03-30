package org.example.homework3.service.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.example.homework3.Exception.NotFoundException;
import org.example.homework3.model.entity.Request.VenueRequest;
import org.example.homework3.model.entity.Venue;
import org.example.homework3.repository.VenueRepository;
import org.example.homework3.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;
    @Override
    public List<Venue> getAllVenues(int offset, int size) {
        return venueRepository.getAllVenues(offset,size);
    }

    @Override
    public Venue saveVenue(VenueRequest request) {
        return venueRepository.saveVenue(request);
    }

    @Override
    public Venue getVenueById(Long venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        if (venue == null) {
            throw new NotFoundException("Venue id " + venueId + " Not Found");
        }
        return venue;
    }

    @Override
    public Venue updateVenueById(Long venueId, VenueRequest request) {
        return venueRepository.updateVenueById(venueId,request);
    }

    @Override
    public Venue deleteVenueById(Long venueId) {
        return venueRepository.deleteVenueByID(venueId);
    }
}
