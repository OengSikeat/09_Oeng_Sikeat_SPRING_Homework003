insert into venues(venue_name, location) VALUES ('pool','kpc'),
                                                ('olympic','pp'),
                                                ('colossuem','rome');
insert into events(event_name, event_date, venue_id) values ('p diddy party',default,1),
                                                            ('tiktok rizz party',default,2),
                                                            ('hrd party',default,1);
insert into attendees(attendee_name, email) values ('sikeat','sikeat@gmail.com'),
                                                   ('markara','markara@gmail.com'),
                                                   ('thavorn','thavorn@gmail,com'),
                                                   ('nimol','nimol@gmail.com');
insert into event_attendees(event_id, attendee_id) VALUES (1,1),
                                                          (1,2),
                                                          (2,3),
                                                          (2,5);

select a.* from event_attendees ea inner join attendees a on a.attendee_id = ea.attendee_id where event_id=1;


