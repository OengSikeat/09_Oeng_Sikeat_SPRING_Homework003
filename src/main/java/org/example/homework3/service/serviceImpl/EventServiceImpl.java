package org.example.homework3.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.homework3.Exception.NotFoundException;
import org.example.homework3.model.entity.Attendee;
import org.example.homework3.model.entity.Event;
import org.example.homework3.model.entity.Request.EventRequest;
import org.example.homework3.model.entity.Venue;
import org.example.homework3.repository.EventAttendeesRepository;
import org.example.homework3.repository.EventRepository;
import org.example.homework3.repository.VenueRepository;
import org.example.homework3.service.AttendeeService;
import org.example.homework3.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventAttendeesRepository eventAttendeesRepository;
    private final VenueRepository venueRepository;


    @Override
    public List<Event> getAllEvents(int offset, int size) {
        return eventRepository.getAllEvents(offset,size);
    }

    @Override
    public Event saveEvent(EventRequest request) {
        Venue venue = venueRepository.getVenueById(request.getVenueId());
        if (venue == null) {
            throw new NotFoundException("Venue id " + request.getVenueId() + " Not Found");
        }

        for (Long attendeeId : request.getAttendeesId()) {
            List<Attendee> attendees = eventAttendeesRepository.getAttendeeByEventId(attendeeId);
            if (attendees == null || attendees.isEmpty()) {
                throw new NotFoundException("Attendee id " + attendeeId + " Not Found");
            }
        }
        Event event = eventRepository.saveEvent(request);


        for (Long attendeeId : request.getAttendeesId()) {
            eventAttendeesRepository.insertEventAndAttendeesId(event.getEventId(), attendeeId);
        }

        return eventRepository.getEventById(event.getEventId());
    }



    @Override
    public Event getEventById(Long eventId) {
        return eventRepository.getEventById(eventId);
    }

    @Override
    public Event updateEventById(Long eventId, EventRequest request) {

        Venue venue = venueRepository.getVenueById(request.getVenueId());
        if (venue == null) {
            throw new NotFoundException("Venue id " + request.getVenueId() + " Not Found");
        }


        for (Long attendeeId : request.getAttendeesId()) {
            Attendee attendee = eventAttendeesRepository.getAttendeeById(attendeeId);
            if (attendee == null) {
                throw new NotFoundException("Attendee id " + attendeeId + " Not Found");
            }
        }


        return eventRepository.updateEventById(eventId, request);
    }

    @Override
    public Event deleteEventById(Long eventId) {
        return eventRepository.deleteEventById(eventId);
    }
}
