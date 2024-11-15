package com.esportivai_api.controller;

import com.esportivai_api.application.usecase.ParticipationService;
import com.esportivai_api.domain.entity.Participation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipationController {
    private final ParticipationService participantService;

    public ParticipationController(ParticipationService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public List<Participation> getAllParticipations() {
        return participantService.getAllParticipations();
    }

    @PostMapping
    public Participation createParticipation(@RequestBody Participation participant) {
        return participantService.createParticipation(participant);
    }

    @PutMapping("/{id}")
    public Participation updateParticipation(@PathVariable Long id, @RequestBody Participation participant) {
        return participantService.updateParticipation(id, participant);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipation(@PathVariable Long id) {
        participantService.deleteParticipation(id);
    }
}
