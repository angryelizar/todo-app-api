package kg.angryelizar.todoapi.service;

import kg.angryelizar.todoapi.dto.TaskStatusDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskStatusService {
    ResponseEntity<List<TaskStatusDto>> getAll();
}
