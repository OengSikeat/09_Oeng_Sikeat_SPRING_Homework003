package org.example.homework3.repository;


import org.apache.ibatis.annotations.*;
import org.example.homework3.model.entity.Event;
import org.example.homework3.model.entity.Request.EventRequest;
import org.example.homework3.model.entity.Request.VenueRequest;

import java.util.List;

@Mapper
public interface EventRepository {


    @Results(id = "EventMapper", value = {
            @Result(property = "eventId",column = "event_id"),
            @Result(property = "eventName",column = "event_name"),
            @Result(property = "eventDate",column = "event_date"),
            @Result(property = "venue",column = "venue_id",
            one = @One(select = "org.example.homework3.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees",column = "event_id",
            many = @Many(select = "org.example.homework3.repository.EventAttendeesRepository.getAttendeeByEventId"))

    })
    @Select("""
        SELECT * from events OFFSET #{offset} Limit #{size};
    """)
    List<Event> getAllEvents(int offset, int size);


    @ResultMap("EventMapper")
    @Select("""
        INSERT INTO events values (default,#{req.eventName},#{req.eventDate},#{req.venueId})
        RETURNING *;
    """)
    Event saveEvent(@Param("req") EventRequest request);


    @ResultMap("EventMapper")
    @Select("""
        SELECT * from events where event_id = #{eventId}
    """)
    Event getEventById(Long eventId);


    @ResultMap("EventMapper")
    @Select("""
        UPDATE events set event_name = #{request.eventName}, event_date = #{request.eventDate}, venue_id = #{request.venueId} where event_id= #{event_id}
        RETURNING *;
    """)
    Event updateEventById(@Param("event_id") Long eventId ,@Param("request") EventRequest request);

    @ResultMap("EventMapper")
    @Select("""
        DELETE from events where event_id= #{eventId}
    """)
    Event deleteEventById(Long eventId);
}
