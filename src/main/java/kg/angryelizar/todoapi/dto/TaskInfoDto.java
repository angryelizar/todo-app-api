package kg.angryelizar.todoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "Task info entity")
@Getter
@Setter
@ToString
@Builder
public class TaskInfoDto {
    @NotBlank(message = "ID of task cannot be empty!")
    @Schema(description = "ID of task", example = "1")
    private Long id;
    @NotBlank
    @Size(min = 5, max = 100, message = "Title need to have more then 5 symbols and not to be bigger then 100!")
    @Schema(description = "Task title", example = "Write a REST API for Mega")
    private String title;
    @Size(max = 255, message = "Description need not to be bigger then 255 symbols!")
    @Schema(description = "Task title", example = "I need finish test task")
    private String description;
    @NotBlank(message = "Status of task cannot be empty!")
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
