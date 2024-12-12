package com.esportivai.controllers;

import com.esportivai.application.usecase.EventService;
import com.esportivai.domain.dtos.EventDto;
import com.esportivai.domain.entity.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/organizer/{userId}")
    public ResponseEntity<List<EventDto>> getAllEventByUserId(@PathVariable Long userId) {
        List<EventDto> events = eventService.getEventByUserId(userId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/byID/{eventId}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long eventId) {
        EventDto eventDetails = eventService.getEventById(eventId);
        return ResponseEntity.ok(eventDetails);
    }

    @PostMapping("/organizer/{userId}")
    public ResponseEntity<Event> create(@PathVariable Long userId, @RequestBody Event event) {
        Event newEvent = eventService.createEvent(userId, event);
        return ResponseEntity.ok(newEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEvent(@PathVariable Long id, @RequestBody EventDto event) {
        eventService.updateEvent(id, event);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

}
