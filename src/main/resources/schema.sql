create table if not exists venues(
    venue_id serial not null primary key,
    venue_name varchar(100) not null ,
    location varchar(255) not null
);

create table if not exists events(
      event_id serial not null primary key,
      event_name varchar(255) not null ,
      event_date date default not null ,
      venue_id int not null ,
      constraint fk_venue_id foreign key (venue_id) references venues(venue_id) on update cascade on delete cascade
);

create table if not exists attendees(
    attendee_id serial not null primary key,
    attendee_name varchar(255) not null,
    email varchar(255) not null
);
create table if not exists event_attendees(
    id serial primary key not null ,
    event_id int not null,
    attendee_id int not null,
    constraint fk_event foreign key (event_id) references events(event_id) on update cascade on delete cascade,
    constraint fk_attendee foreign key (attendee_id) references attendees(attendee_id) on update cascade on delete cascade
);
