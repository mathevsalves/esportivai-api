package com.esportivai.application.usecase;

import com.esportivai.domain.dtos.EventParticipantsDto;
import com.esportivai.domain.entity.Event;
import com.esportivai.domain.entity.Participant;
import com.esportivai.domain.entity.User;
import com.esportivai.domain.repository.EventRepository;
import com.esportivai.domain.repository.ParticipantRepository;
import com.esportivai.domain.repository.UserRepository;
import com.esportivai.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    private ParticipantRepository participantRepository;
    private EventRepository eventRepository;
    private UserRepository userRepository;

    ParticipantService(ParticipantRepository participantRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public void enrollParticipant(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Participant participant = new Participant();
        participant.setEventId(event);
        participant.setUserId(user);
        participantRepository.save(participant);
    }

    public void leaveEvent(Long participantId) {
        Optional<Participant> participant = participantRepository.findById(participantId);

        participantRepository.delete(participant.get());
    }

    public List<EventParticipantsDto> getEventsJoined(Long userId) {
        // Encontrar o usuário pelo ID
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            // Se o usuário não for encontrado, retornar uma lista vazia ou lançar uma exceção
            return Collections.emptyList();
        }
        User user = userOptional.get();

        // Encontrar todas as participações do usuário
        List<Participant> participants = participantRepository.findAllByUserId(user);

        // Para cada participação, encontrar o evento correspondente e convertê-lo para EventDto
        return participants.stream()
                .map(participant -> {
                    Event event = participant.getEventId();
                    return new EventParticipantsDto(
                            participant.getId(),
                            event.getName(),
                            event.getDate().toString(),
                            event.getTime().toString(),
                            event.getLocation(),
                            event.getMaxParticipants()
                    );
                })
                .collect(Collectors.toList());
    }

}
