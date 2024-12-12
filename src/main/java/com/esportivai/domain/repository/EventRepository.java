package com.esportivai.domain.repository;


import com.esportivai.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByOrganizerId(Long userId);

    @Query(value = "SELECT distinct(e.name), e.date, e.sport_id, e.skill_level " +
            "FROM event e " +
            "JOIN participant p ON e.id = p.event_id " +
            "WHERE e.date > :today ", nativeQuery = true)
    List<Object[]> findAllBiggerThanNow(@Param("today") LocalDate today);

    @Query(value = "SELECT e.name, e.date, e.sport_id, e.skill_level " +
            "FROM event e " +
            "JOIN participant p ON e.id = p.event_id " +
            "WHERE e.date < :today " +
            "AND p.user_id = :userId", nativeQuery = true)
    List<Object[]> findAllSmallerThanNow(@Param("today") LocalDate today, @Param("userId") Integer userId);

    @Query(value = "SELECT e.name, e.date, e.sport_id, e.skill_level " +
            "FROM event e " +
            "JOIN participant p ON e.id = p.event_id " +
            "JOIN sport s ON e.sport_id = s.id " +
            "JOIN users u ON p.user_id = u.id " +
            "WHERE p.user_id = :userId", nativeQuery = true)
    List<Object[]> findAllNowSubscribed(@Param("userId") Integer userId);
}
