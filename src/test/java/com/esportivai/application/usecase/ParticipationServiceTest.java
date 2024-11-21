package com.esportivai.application.usecase;


import com.esportivai.domain.entity.Participation;
import com.esportivai.domain.entity.User;
import com.esportivai.domain.entity.Event;
import com.esportivai.domain.repository.ParticipationRepository;
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

public class ParticipationServiceTest {

    @Mock
    private ParticipationRepository participationRepository;

    @InjectMocks
    private ParticipationService participationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateParticipation() {
//        Participation participation = new Participation();
//        participation.setUser(new User());
//        participation.setEvent(new Event());
//
//        when(participationRepository.save(participation)).thenReturn(participation);
//
//        Participation createdParticipation = participationService.join(participation);
//
//        assertNotNull(createdParticipation);
//        verify(participationRepository, times(1)).save(participation);
    }

    @Test
    void shouldGetAllParticipations() {
        Participation participation1 = new Participation();
        Participation participation2 = new Participation();
        when(participationRepository.findAll()).thenReturn(Arrays.asList(participation1, participation2));

//        List<Participation> participations = participationService.getAllParticipations();

//        assertEquals(2, participations.size());
        verify(participationRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateParticipation() {
        Participation participation = new Participation();
        participation.setId(1L);
        participation.setUser(new User());
        participation.setEvent(new Event());

        when(participationRepository.findById(1L)).thenReturn(Optional.of(participation));
        when(participationRepository.save(participation)).thenReturn(participation);

        Participation updatedParticipation = new Participation();
        updatedParticipation.setUser(new User());
        updatedParticipation.setEvent(new Event());

        Participation result = participationService.updateParticipation(1L, updatedParticipation);

        assertNotNull(result);
        verify(participationRepository, times(1)).save(participation);
    }

    @Test
    void shouldDeleteParticipation() {
        Long participationId = 1L;

//        participationService.deleteParticipation(participationId);

        verify(participationRepository, times(1)).deleteById(participationId);
    }
}

