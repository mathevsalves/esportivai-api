package com.esportivai.application.usecase;

import com.esportivai.domain.dtos.SportDto;
import com.esportivai.domain.dtos.SportUserRequest;
import com.esportivai.domain.entity.Sport;
import com.esportivai.domain.entity.SportUser;
import com.esportivai.domain.entity.User;
import com.esportivai.domain.repository.SportRepository;
import com.esportivai.domain.repository.SportUserRepository;
import com.esportivai.domain.repository.UserRepository;
import com.esportivai.exception.DuplicateRecordException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportService {
    private final SportRepository sportRepository;
    private final SportUserRepository sportUserRepository;
    private final UserRepository userRepository;

    public SportService(SportRepository sportRepository, SportUserRepository sportUserRepository, UserRepository userRepository) {
        this.sportRepository = sportRepository;
        this.sportUserRepository = sportUserRepository;
        this.userRepository = userRepository;
    }

    public List<SportDto> getSportsByUserId(Long userId) {
        return sportRepository.findAllSportsByUserId(userId)
                .stream().map(row -> new SportDto(((Long) row[0]), (String) row[1], (String) row[2]))
                .toList();
    }

    public SportUserRequest add(SportUserRequest sportUserRequest) {
        // Verifica se o usuário e o esporte existem
        Optional<User> user = userRepository.findById(sportUserRequest.getUserId().longValue());
        Optional<Sport> sport = sportRepository.findById(sportUserRequest.getSportId().longValue());

        if (user.isEmpty() || sport.isEmpty()) {
            throw new IllegalArgumentException("User or Sport not found.");
        }

        // Verifica se já existe um registro com a mesma combinação de userId e sportId
        boolean exists = sportUserRepository.existsByUserIdAndSportId(user.get(), sport.get());

        if (exists) {
            throw new DuplicateRecordException("A record with the same User ID and Sport ID already exists.");
        }

        // Cria o registro se não houver duplicação
        SportUser sportUser = new SportUser();
        sportUser.setUserId(user.get());
        sportUser.setSportId(sport.get());
        sportUser.setSkillLevel(sportUserRequest.getSkillLevel());
        sportUserRepository.save(sportUser);

        return sportUserRequest;
    }

    public void delete(Long sportUserId) {
        sportUserRepository.deleteById(sportUserId);
    }
}
