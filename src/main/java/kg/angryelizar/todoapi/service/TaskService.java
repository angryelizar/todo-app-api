package kg.angryelizar.todoapi.service;

import kg.angryelizar.todoapi.dto.TaskCreateDto;
import kg.angryelizar.todoapi.dto.TaskInfoDto;
import kg.angryelizar.todoapi.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    ResponseEntity<TaskInfoDto> create(TaskCreateDto task, Authentication authentication);

    ResponseEntity<List<TaskInfoDto>> getAllActiveTasksForUser(Authentication authentication);
}
