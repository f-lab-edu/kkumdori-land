package org.example.kkumdoriland.user.application;

import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.user.domain.User;
import org.example.kkumdoriland.user.dto.UserJoinDTO;
import org.example.kkumdoriland.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean join(UserJoinDTO dto) {
        // validation logic

        userRepository.save(dto.toUser());

        return true;
    }

}
