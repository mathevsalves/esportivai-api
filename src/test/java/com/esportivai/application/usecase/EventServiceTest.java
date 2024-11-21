package com.esportivai.application.usecase;

import com.esportivai.domain.entity.Event;
import com.esportivai.domain.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateEvent() {
        Event event = new Event();
        event.setEventName("Sample Event");
        event.setDescription("Description of Sample Event");

        when(eventRepository.save(event)).thenReturn(event);

        Event createdEvent = eventService.createEvent(event);

        assertEquals("Sample Event", createdEvent.getEventName());
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void shouldGetAllEvents() {
        Event event1 = new Event();
        Event event2 = new Event();
        when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2));

        List<Event> events = eventService.getAllEvents();

        assertEquals(2, events.size());
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateEvent() {
        Event event = new Event();
        event.setEventId(1L);
        event.setEventName("Initial Event");

        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        when(eventRepository.save(event)).thenReturn(event);

        Event updatedEvent = new Event();
        updatedEvent.setEventName("Updated Event");

        Event result = eventService.updateEvent(1L, updatedEvent);

        assertEquals("Updated Event", result.getEventName());
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void shouldDeleteEvent() {
        Long eventId = 1L;

        eventService.deleteEvent(eventId);

        verify(eventRepository, times(1)).deleteById(eventId);
    }
}