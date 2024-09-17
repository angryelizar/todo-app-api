package kg.angryelizar.todoapi.service.impl;

import kg.angryelizar.todoapi.dto.UserDto;
import kg.angryelizar.todoapi.exception.UserException;
import kg.angryelizar.todoapi.model.User;
import kg.angryelizar.todoapi.repository.AuthorityRepository;
import kg.angryelizar.todoapi.repository.UserRepository;
import kg.angryelizar.todoapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final String DEFAULT_ROLE = "USER";

    @Override
    public HttpStatus registerUser(UserDto userDto) {
        log.info("Registering user with email and username: {}, {}", userDto.getEmail(), userDto.getUsername());
        if (Boolean.TRUE.equals(userRepository.existsByEmail(userDto.getEmail()))) {
            log.error("User with email {} already exists", userDto.getEmail());
            throw new UserException("Email already exists");
        }
        if (Boolean.TRUE.equals(userRepository.existsByUsername(userDto.getUsername()))) {
            log.error("Username already exists - {}", userDto.getUsername());
            throw new UserException("Username already exists");
        }
        userRepository.save(User.builder()
                        .email(userDto.getEmail())
                        .username(userDto.getUsername())
                        .authority(authorityRepository.findByAuthority(DEFAULT_ROLE))
                        .enabled(true)
                        .password(passwordEncoder.encode(userDto.getPassword()))
                .build());
        log.info("User successfully registered with email: {}", userDto.getEmail());
        return HttpStatus.CREATED;
    }
}
