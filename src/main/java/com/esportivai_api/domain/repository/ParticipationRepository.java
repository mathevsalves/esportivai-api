package com.esportivai_api.domain.repository;

import com.esportivai_api.domain.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
}