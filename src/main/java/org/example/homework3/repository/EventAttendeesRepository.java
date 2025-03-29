package org.example.homework3.repository;


import org.apache.ibatis.annotations.*;
import org.example.homework3.model.entity.Attendee;

import java.util.List;

@Mapper
public interface EventAttendeesRepository {

    @Results(id = "AttendeeByEventIdMapper", value = {
            @Result(property = "attendeeId",column = "attendee_id"),
            @Result(property = "attendeeName",column = "attendee_name"),
            @Result(property = "email",column = "email")
    })
    @Select("""
            select a.* from event_attendees ea 
            inner join attendees a
            on a.attendee_id = ea.attendee_id 
            where event_id= #{eventId};
    """)
    List<Attendee> getAttendeeByEventId(Long eventId);


    @Insert("""
        INSERT INTO event_attendees (event_id, attendee_id) 
        VALUES (#{eventId}, #{attendeeId});
    """)
    void insertEventAndAttendeesId(Long eventId, Long attendeeId);
}
