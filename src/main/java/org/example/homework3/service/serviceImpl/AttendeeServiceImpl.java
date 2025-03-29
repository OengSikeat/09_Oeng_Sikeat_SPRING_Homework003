package org.example.homework3.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.homework3.model.entity.Attendee;
import org.example.homework3.model.entity.Request.AttendeeRequest;
import org.example.homework3.repository.AttendeeRepository;
import org.example.homework3.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;


    @Override
    public List<Attendee> getAllAttendees(int offset, int size) {
        return attendeeRepository.getAllAttendees(offset,size);
    }

    @Override
    public Attendee saveAttendee(AttendeeRequest request) {
        return attendeeRepository.saveAttendee(request);
    }

    @Override
    public Attendee getAttendeeById(Long attendeeId) {
        return attendeeRepository.getAttendeeById(attendeeId);
    }

    @Override
    public Attendee updateAttendeeById(Long attendeeId, AttendeeRequest request) {
        return attendeeRepository.updateAttendeeById(attendeeId,request);
    }

    @Override
    public Attendee deleteAttendeeById(Long attendeeId) {
        return attendeeRepository.deleteAttendeeById(attendeeId);
    }
}
