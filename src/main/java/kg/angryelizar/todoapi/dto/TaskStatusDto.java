package kg.angryelizar.todoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "Task status entity")
@Getter
@Setter
@ToString
@Builder
public class TaskStatusDto {
    private String status;
}
