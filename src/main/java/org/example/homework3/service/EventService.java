package org.example.homework3.service;

import org.example.homework3.model.entity.Event;
import org.example.homework3.model.entity.Request.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(int offset, int size);

    Event saveEvent(EventRequest request);

    Event getEventById(Long eventId);

    Event updateEventById(Long eventId, EventRequest request);

    Event deleteEventById(Long eventId);
}
