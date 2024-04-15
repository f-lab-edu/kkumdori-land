package org.example.kkumdoriland.user.application;

import lombok.RequiredArgsConstructor;
import org.example.kkumdoriland.user.domain.User;
import org.example.kkumdoriland.user.dto.UserJoinDTO;
import org.example.kkumdoriland.user.dto.UserResponse;
import org.example.kkumdoriland.user.exception.UserErrorCode;
import org.example.kkumdoriland.user.exception.UserException;
import org.example.kkumdoriland.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse join(UserJoinDTO dto) {
        final User userToCreate = toUser(dto.getName(), dto.getEmail(), dto.getPassword());

        // validation logic
        validateDuplicatedEmail(userToCreate);

        return UserResponse.of(userRepository.save(userToCreate));
    }

    private void validateDuplicatedEmail(User user) {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new UserException(UserErrorCode.USER_EMAIL_DUPLICATION, "이미 존재하는 이메일입니다.");
        }
    }

    private User toUser(String name, String email, String password) {
        return new User(name, email, passwordEncoder.encode(password));
    }
}
