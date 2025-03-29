package org.example.homework3.repository;


import org.apache.ibatis.annotations.*;
import org.example.homework3.model.entity.Request.VenueRequest;
import org.example.homework3.model.entity.Venue;

import java.util.List;

@Mapper
public interface VenueRepository {


    @Select("""
        SELECT * from venues OFFSET #{offset} LIMIT #{size};
    """)
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId",column = "venue_id"),
            @Result(property = "venueName",column = "venue_name")
    })
    List<Venue> getAllVenues(int offset, int size);


    @ResultMap("venueMapper")
    @Select("""
    INSERT INTO venues values (default, #{req.venueName}, #{req.location})
    returning *;
    """)
    Venue saveVenue(@Param("req") VenueRequest request);


    @ResultMap("venueMapper")
    @Select("""
    SELECT * from venues where venue_id = #{venueId}
    """)
    Venue getVenueById(Long venueId);


    @ResultMap("venueMapper")
    @Select("""
        UPDATE venues SET venue_name = #{request.venueName}, location = #{request.location} 
                  Where venue_id = #{venue_id} Returning *;
    """)
    Venue updateVenueById(@Param("venue_id") Long venueId ,@Param("request") VenueRequest request);


    @ResultMap("venueMapper")
    @Select("""
        DELETE from venues where venue_id = #{venueId}
    """)
    Venue deleteVenueByID(Long venueId);
}
