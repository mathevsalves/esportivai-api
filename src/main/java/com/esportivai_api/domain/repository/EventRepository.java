package com.esportivai_api.domain.repository;


import com.esportivai_api.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
