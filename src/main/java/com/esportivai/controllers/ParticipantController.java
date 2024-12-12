package com.esportivai.controllers;

import com.esportivai.application.usecase.ParticipantService;
import com.esportivai.domain.dtos.EventParticipantsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enroll")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<EventParticipantsDto>> getAllParticipants(@PathVariable Long userId) {
        List<EventParticipantsDto> participants = participantService.getEventsJoined(userId);
        return ResponseEntity.ok(participants);
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<Void> enrollParticipant(@PathVariable Long eventId, @RequestBody Map<String, Integer> payload) {
        Long userId = Long.valueOf(payload.get("userId"));
        participantService.enrollParticipant(eventId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{participantId}/leave")
    public ResponseEntity<Void> leave(@PathVariable Long participantId) {
        participantService.leaveEvent(participantId);
        return ResponseEntity.noContent().build();
    }

}
