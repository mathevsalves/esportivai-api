package com.esportivai.application.usecase;

import com.esportivai.domain.entity.Event;
import com.esportivai.domain.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event event = eventRepository.findById(id).orElseThrow();
        event.setEventName(updatedEvent.getEventName());
        event.setDescription(updatedEvent.getDescription());
        event.setEventDate(updatedEvent.getEventDate());
        event.setEventTime(updatedEvent.getEventTime());
        event.setLocation(updatedEvent.getLocation());
        event.setMaxParticipants(updatedEvent.getMaxParticipants());
        event.setSkillLevel(updatedEvent.getSkillLevel());
        event.setStatus(updatedEvent.getStatus());
        event.setOrganizerId(updatedEvent.getOrganizerId());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
