package org.example.kkumdoriland.user.application;

import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.user.domain.User;
import org.example.kkumdoriland.user.dto.UserJoinDTO;
import org.example.kkumdoriland.user.exception.UserErrorCode;
import org.example.kkumdoriland.user.exception.UserException;
import org.example.kkumdoriland.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public long join(UserJoinDTO dto) {
        // validation logic
        if (userRepository.findUserByEmail(dto.getEmail()).isPresent()) {
            throw new UserException(UserErrorCode.USER_EMAIL_DUPLICATION, dto.getEmail());
        }

        User user = userRepository.save(dto.toUser());

        return user.getId();
    }



}
