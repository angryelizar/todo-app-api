package kg.angryelizar.todoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.angryelizar.todoapi.dto.UserDto;
import kg.angryelizar.todoapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User contoller", description = "Endpoints for register and editing user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(
            summary = "Registration of user",
            description = "Allows you to register a user"
    )
    public HttpStatus register(@Valid @RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }
}
