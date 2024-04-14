package org.example.kkumdoriland.user.application;

import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.user.dto.UserJoinDTO;
import org.example.kkumdoriland.user.dto.UserResponse;
import org.example.kkumdoriland.user.exception.UserErrorCode;
import org.example.kkumdoriland.user.exception.UserException;
import org.example.kkumdoriland.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse join(UserJoinDTO dto) {
        // validation logic
        System.out.println("dto.email() = " + dto.getEmail() + " dto.getName() = " + dto.getName() + " dto.getPassword() = " + dto.getPassword());
        validateDuplicatedEmail(dto);


        return UserResponse.of(userRepository.save(dto.toUser()));
    }

    private void validateDuplicatedEmail(UserJoinDTO dto) {
        if (userRepository.findUserByEmail(dto.getEmail()).isPresent()) {
            throw new UserException(UserErrorCode.USER_EMAIL_DUPLICATION, dto.getEmail());
        }
    }
}
