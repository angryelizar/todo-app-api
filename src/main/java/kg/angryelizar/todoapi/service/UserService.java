package kg.angryelizar.todoapi.service;

import kg.angryelizar.todoapi.dto.UserDto;
import kg.angryelizar.todoapi.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    HttpStatus registerUser(UserDto userDto);
    User getUserFromAuthentication(Authentication authentication);
}
