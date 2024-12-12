package com.esportivai.domain.repository;

import com.esportivai.domain.entity.Sport;
import com.esportivai.domain.entity.SportUser;
import com.esportivai.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportUserRepository extends JpaRepository<SportUser, Long> {

    boolean existsByUserIdAndSportId(User userId, Sport sportId);


}
