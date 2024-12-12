package com.esportivai.domain.repository;

import com.esportivai.domain.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {

    @Query(value = "SELECT su.id, s.name as sport, su.SKILL_LEVEL as skillLevel " +
            "FROM sport s " +
            "JOIN sportuser su ON s.id = su.sport_id " +
            "JOIN users u ON su.user_id = u.id " +
            "WHERE u.id = :userId", nativeQuery = true)
    List<Object[]> findAllSportsByUserId(@Param("userId") Long userId);

}
