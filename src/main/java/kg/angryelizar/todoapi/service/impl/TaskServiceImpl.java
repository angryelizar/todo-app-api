package kg.angryelizar.todoapi.service.impl;

import kg.angryelizar.todoapi.dto.TaskCreateDto;
import kg.angryelizar.todoapi.dto.TaskInfoDto;
import kg.angryelizar.todoapi.enums.TaskStatus;
import kg.angryelizar.todoapi.exception.UserException;
import kg.angryelizar.todoapi.model.Task;
import kg.angryelizar.todoapi.model.User;
import kg.angryelizar.todoapi.repository.TaskRepository;
import kg.angryelizar.todoapi.repository.TaskStatusRepository;
import kg.angryelizar.todoapi.repository.UserRepository;
import kg.angryelizar.todoapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;

    @Override
    public ResponseEntity<TaskInfoDto> create(TaskCreateDto task, Authentication authentication) {
        Optional<User> user = userRepository.getByEmail(authentication.getName());
        if (user.isEmpty()) {
            log.error("User with email {} not found", authentication.getName());
            throw new UserException("User not found!");
        }
        User author = user.get();
        Task savedTask = taskRepository.save(
                Task.builder()
                        .author(author)
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .creationDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .status(taskStatusRepository.findByStatus(TaskStatus.CREATED.getStatus()))
                        .build());
        log.info("Created task with ID {} by user {}", savedTask.getId(), savedTask.getAuthor().getEmail());
        return ResponseEntity.ok(makeTaskInfoDto(savedTask));
    }

    private TaskInfoDto makeTaskInfoDto(Task task) {
        return TaskInfoDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus().getStatus())
                .createdAt(task.getCreationDate())
                .updatedAt(task.getUpdateDate())
                .build();
    }
}
