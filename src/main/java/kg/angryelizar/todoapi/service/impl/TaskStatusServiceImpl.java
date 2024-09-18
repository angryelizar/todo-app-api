package kg.angryelizar.todoapi.service.impl;

import kg.angryelizar.todoapi.dto.TaskStatusDto;
import kg.angryelizar.todoapi.model.TaskStatus;
import kg.angryelizar.todoapi.repository.TaskStatusRepository;
import kg.angryelizar.todoapi.service.TaskStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskStatusServiceImpl implements TaskStatusService {
    private final TaskStatusRepository taskStatusRepository;

    @Override
    public ResponseEntity<List<TaskStatusDto>> getAll() {
        log.info("Getting all task statuses.");
        return ResponseEntity.ok(taskStatusRepository.findAll().stream().map(this::toDto).collect(Collectors.toList()));
    }



    private TaskStatusDto toDto(TaskStatus taskStatus) {
        return TaskStatusDto.builder()
                .status(taskStatus.getStatus())
                .build();
    }
}
