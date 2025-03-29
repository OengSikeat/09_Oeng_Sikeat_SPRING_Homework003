package org.example.homework3.service;

import org.example.homework3.model.entity.Attendee;
import org.example.homework3.model.entity.Request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {

    List<Attendee> getAllAttendees(int offset, int size);

    Attendee saveAttendee(AttendeeRequest request);

    Attendee getAttendeeById(Long attendeeId);

    Attendee updateAttendeeById(Long attendeeId, AttendeeRequest request);

    Attendee deleteAttendeeById(Long attendeeId);
}
