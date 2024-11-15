package com.esportivai_api.domain.repository;

import com.esportivai_api.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
