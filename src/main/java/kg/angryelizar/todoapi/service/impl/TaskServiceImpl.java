package kg.angryelizar.todoapi.service.impl;

import kg.angryelizar.todoapi.dto.TaskCreateDto;
import kg.angryelizar.todoapi.dto.TaskInfoDto;
import kg.angryelizar.todoapi.enums.TaskStatus;
import kg.angryelizar.todoapi.exception.TaskException;
import kg.angryelizar.todoapi.model.Task;
import kg.angryelizar.todoapi.model.User;
import kg.angryelizar.todoapi.repository.TaskRepository;
import kg.angryelizar.todoapi.repository.TaskStatusRepository;
import kg.angryelizar.todoapi.service.TaskService;
import kg.angryelizar.todoapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskStatusRepository taskStatusRepository;

    @Override
    public ResponseEntity<TaskInfoDto> create(TaskCreateDto task, Authentication authentication) {
        User author = userService.getUserFromAuthentication(authentication);
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
        log.info("New task {}", savedTask);
        return ResponseEntity.ok(makeTaskInfoDto(savedTask));
    }

    @Override
    public ResponseEntity<List<TaskInfoDto>> getAllActiveTasksForUser(Authentication authentication) {
        log.info("Get all active tasks for user {}", authentication.getName());
        User author = userService.getUserFromAuthentication(authentication);
        List<Task> tasks = taskRepository.findActiveTaskByAuthor(author.getEmail(), taskStatusRepository.findByStatus(TaskStatus.DELETED.getStatus()).getId());
        log.info("Found {} active tasks for user {}", tasks.size(), author.getEmail());
        return ResponseEntity.ok(tasks.stream().map(this::makeTaskInfoDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<TaskInfoDto> getTaskById(Long id, Authentication authentication) {
        User author = userService.getUserFromAuthentication(authentication);
        log.info("Get task with ID {} by user {}", id, author.getEmail());
        Optional<Task> task = getAndValidateTask(id, author);
        log.info("Task with ID {} found", id);
        log.info("Task with ID {} found", task.get());
        return ResponseEntity.ok(makeTaskInfoDto(task.get()));
    }

    private Optional<Task> getAndValidateTask(Long id, User author) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            log.info("Task with ID {} not found", id);
            throw new TaskException("There is no task with ID " + id);
        }
        if (Boolean.FALSE.equals(isAuthor(author, task.get()))) {
            log.error("Task with ID {} is not author", id);
            throw new TaskException("You are not author of this task");
        }
        return task;
    }

    @Override
    public HttpStatus deleteTaskById(Long id, Authentication authentication) {
        User author = userService.getUserFromAuthentication(authentication);
        Task task = getAndValidateTask(id, author).get();
        if (task.getStatus().getStatus().equals(TaskStatus.DELETED.getStatus())) {
            log.info("Task with ID {} is already deleted", id);
            throw new TaskException("Task with ID " + id + " is already deleted");
        }
        log.info("Deleting (changed status) task with ID {} by user {}", id, author.getEmail());
        task.setStatus(taskStatusRepository.findByStatus(TaskStatus.DELETED.getStatus()));
        task.setUpdateDate(LocalDateTime.now());
        taskRepository.save(task);
        return HttpStatus.ACCEPTED;
    }

    private Boolean isAuthor(User user, Task task) {
        return task.getAuthor().getEmail().equals(user.getEmail());
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
