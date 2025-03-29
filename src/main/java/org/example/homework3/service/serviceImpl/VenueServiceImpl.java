package org.example.homework3.service.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.example.homework3.model.entity.Request.VenueRequest;
import org.example.homework3.model.entity.Venue;
import org.example.homework3.repository.VenueRepository;
import org.example.homework3.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return venueRepository.getVenueById(venueId);
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
