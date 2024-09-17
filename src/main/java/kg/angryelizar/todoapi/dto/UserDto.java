package kg.angryelizar.todoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "User entity")
@Getter
@Setter
@ToString
@Builder
public class UserDto {
    @Schema(description = "User email", example = "conovalov.elizar@gmail.com")
    @Email(message = "Its need to be look like email!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;
    @Schema(description = "Username", example = "angryelizar")
    @Size(min = 3, max = 20, message = "Username need to have more then 3 symbols and not to bigger then 20 symbols!")
    @NotBlank(message = "Username cannot be empty!")
    private String username;
    @Schema(description = "User password", example = "qwerty123")
    @Size(min = 6, max = 20, message = "Password need to have more then 6 symbols and not to bigger then 20 symbols!")
    @NotBlank(message = "Password cannot be empty!")
    private String password;

}
