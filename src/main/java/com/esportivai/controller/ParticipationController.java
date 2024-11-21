package com.esportivai.controller;

import com.esportivai.application.usecase.ParticipationService;
import com.esportivai.domain.entity.Participation;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class ParticipationController {
    private final ParticipationService participantService;

    public ParticipationController(ParticipationService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/{id}/participants")
    public Optional<Participation> getAllParticipants(@PathVariable Long id) {
        return participantService.getAllParticipants(id);
    }

    @PostMapping("/{id}/join")
    public Participation join(@PathVariable String id, @RequestBody String userId) {
        return participantService.join(id, userId);
    }

    @PutMapping("/{id}")
    public Participation updateParticipation(@PathVariable Long id, @RequestBody Participation participant) {
        return participantService.updateParticipation(id, participant);
    }

    @DeleteMapping("/{id}/leave")
    public void leave(@PathVariable Long id, @RequestBody String userId) {
        participantService.leave(id, userId);
    }
}
