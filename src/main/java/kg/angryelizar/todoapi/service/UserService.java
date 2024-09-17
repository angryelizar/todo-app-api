package kg.angryelizar.todoapi.service;

import kg.angryelizar.todoapi.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    HttpStatus registerUser(UserDto userDto);
}
