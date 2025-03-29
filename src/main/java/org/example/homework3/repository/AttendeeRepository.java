package org.example.homework3.repository;

import org.apache.ibatis.annotations.*;
import org.example.homework3.model.entity.Attendee;
import org.example.homework3.model.entity.Request.AttendeeRequest;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId",column = "attendee_id"),
            @Result(property = "attendeeName",column = "attendee_name")
    })
    @Select("""
        SELECT * from attendees OFFSET #{offset} Limit #{size};
    """)
    List<Attendee> getAllAttendees(int offset, int size);

    @ResultMap("attendeeMapper")
    @Select("""
        insert into attendees values (default, #{req.attendeeName}, #{req.email})
    """)
    Attendee saveAttendee(@Param("req") AttendeeRequest request);


    @ResultMap("attendeeMapper")
    @Select("""
        select * from attendees where attendee_id = #{attendeeId}
    """)
    Attendee getAttendeeById(Long attendeeId);

    @ResultMap("attendeeMapper")
    @Select("""
        UPDATE attendees set attendee_name = #{request.attendeeName}, email = #{request.email} where attendee_id= #{attendeeId}
        Returning *;
    """)
    Attendee updateAttendeeById(Long attendeeId, AttendeeRequest request);

    @ResultMap("attendeeMapper")
    @Select("""
        DELETE from attendees where attendee_id = #{attendeeId}
    """)
    Attendee deleteAttendeeById(Long attendeeId);
}
