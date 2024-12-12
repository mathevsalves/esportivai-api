package com.esportivai.application.usecase;

import com.esportivai.domain.dtos.EventDto;
import com.esportivai.domain.dtos.DashboardEventView;
import com.esportivai.domain.entity.Event;
import com.esportivai.domain.entity.User;
import com.esportivai.domain.repository.EventRepository;
import com.esportivai.domain.repository.UserRepository;
import com.esportivai.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    private final UserRepository userRepository;


    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<EventDto> getEventByUserId(Long userId) {

        List<Event> events = eventRepository.findAllByOrganizerId(userId);
        return events.stream()
                .map(event -> new EventDto(event.getId(), event.getName(), event.getSportId(), event.getDate().format(dateFormatter),
                        event.getTime().format(timeFormatter), event.getLocation(), event.getMaxParticipants()))
                .toList();
    }

    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(event -> new EventDto(event.getId(), event.getName(), event.getSportId(), event.getDate().format(dateFormatter),
                        event.getTime().format(timeFormatter), event.getLocation(), event.getMaxParticipants()))
                .toList();
    }

    public Event createEvent(Long userId, Event event) {
        User organizer = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        event.setOrganizerId(organizer.getId());
        return eventRepository.save(event);
    }

//    public List<EventDto> listEventsByUser(Long userId) {
//        return eventRepository.findAllByOrganizerId(userId)
//                .stream()
//                .map(event -> new EventDto(event.getName(), event.getDescription(), event.))
//                .collect(Collectors.toList());
//    }

    public EventDto getEventById(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));
        return new EventDto(event.getId(), event.getName(), event.getSportId(), event.getDate().format(dateFormatter),
                event.getTime().format(timeFormatter), event.getLocation(), event.getMaxParticipants(), event.getSkillLevel());
    }

    public void updateEvent(Long eventId, EventDto eventDto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));
        event.setName(eventDto.getName());
        eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public List<DashboardEventView> getUpcomingEvents() {
        return eventRepository.findAllBiggerThanNow(LocalDate.now()).stream()
                .map(row -> new DashboardEventView((String) row[0], row[1].toString(), (Integer) row[2], (String) row[3])).toList();
    }

    public List<DashboardEventView> getPastEvents(Integer userId) {
        return eventRepository.findAllSmallerThanNow(LocalDate.now(), userId).stream()
            .map(row -> new DashboardEventView((String) row[0], row[1].toString(), (Integer) row[2], (String) row[3])).toList();

    }

    public List<DashboardEventView> getSubscribedEvents(Integer userId) {
        return eventRepository.findAllNowSubscribed(userId).stream()
                .map(row -> new DashboardEventView((String) row[0], row[1].toString(), (Integer) row[2], (String) row[3])).toList();

    }
}
