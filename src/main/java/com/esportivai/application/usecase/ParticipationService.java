package com.esportivai.application.usecase;

import com.esportivai.domain.entity.Event;
import com.esportivai.domain.entity.Participation;
import com.esportivai.domain.entity.User;
import com.esportivai.domain.repository.EventRepository;
import com.esportivai.domain.repository.ParticipationRepository;
import com.esportivai.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipationService {

    private final ParticipationRepository participationRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    public ParticipationService(ParticipationRepository participationRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.participationRepository = participationRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public Optional<Participation> getAllParticipants(Long id) {
        return participationRepository.findById(id);
    }

    public Participation join(String eventId, String userId) {
        Optional<User> user = userRepository.findById(Long.valueOf(userId));
        Optional<Event> event = eventRepository.findById(Long.valueOf(eventId));

        if (user.isPresent() && event.isPresent()) {
            Participation participation = new Participation();
            participation.setUser(user.get());
            participation.setEvent(event.get());
            return participationRepository.save(participation);
        }
        return null;

    }

    public Participation updateParticipation(Long id, Participation updatedParticipation) {
        Participation participation = participationRepository.findById(id).orElseThrow();
        participation.setEvent(updatedParticipation.getEvent());
        return participationRepository.save(participation);
    }

    public void leave(Long id, String userId) {
        participationRepository.deleteById(id);
    }
}
