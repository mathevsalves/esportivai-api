package com.esportivai.domain.repository;

import com.esportivai.domain.entity.Participant;
import com.esportivai.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findAllByUserId(User userId);

}