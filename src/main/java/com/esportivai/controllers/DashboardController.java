package com.esportivai.controllers;

import com.esportivai.application.usecase.EventService;
import com.esportivai.domain.dtos.DashboardEventView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DashboardController {

    private final EventService eventService;

    public DashboardController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/upcoming-events")
    public ResponseEntity<List<DashboardEventView>> getUpcomingEvents() {
        List<DashboardEventView> upcomingEvents = eventService.getUpcomingEvents();
        return ResponseEntity.status(HttpStatus.OK).body(upcomingEvents);
    }

    @GetMapping("{userId}/past-events")
    public ResponseEntity<List<DashboardEventView>> getPastEvents(@PathVariable Integer userId) {
        List<DashboardEventView> pastEvents = eventService.getPastEvents(userId);
        return ResponseEntity.status(HttpStatus.OK).body(pastEvents);
    }

    @GetMapping("{userId}/subscribed-events")
    public ResponseEntity<List<DashboardEventView>> getSubscribedEvents(@PathVariable Integer userId) {
        List<DashboardEventView> subscribedEvents = eventService.getSubscribedEvents(userId);
        return ResponseEntity.status(HttpStatus.OK).body(subscribedEvents);
    }


}
