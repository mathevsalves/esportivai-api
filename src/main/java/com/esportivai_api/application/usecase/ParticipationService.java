package com.esportivai_api.application.usecase;

import com.esportivai_api.domain.entity.Participation;
import com.esportivai_api.domain.repository.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public List<Participation> getAllParticipations() {
        return participationRepository.findAll();
    }

    public Participation createParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

    public Participation updateParticipation(Long id, Participation updatedParticipation) {
        Participation participation = participationRepository.findById(id).orElseThrow();
        participation.setEvent(updatedParticipation.getEvent());
        return participationRepository.save(participation);
    }

    public void deleteParticipation(Long id) {
        participationRepository.deleteById(id);
    }
}
