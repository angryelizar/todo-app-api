package kg.angryelizar.todoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.angryelizar.todoapi.dto.TaskCreateDto;
import kg.angryelizar.todoapi.dto.TaskInfoDto;
import kg.angryelizar.todoapi.model.Task;
import kg.angryelizar.todoapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Task contoller", description = "Endpoints for creating and editing tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping()
    @Operation(
            summary = "Get all tasks",
            description = "Getting a list of all yours active (non-deleted) tasks"
    )
    public ResponseEntity<List<TaskInfoDto>> getAllTasks(Authentication authentication) {
        return taskService.getAllActiveTasksForUser(authentication);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieving a task by ID",
            description = "Obtaining individual information on a task by ID"
    )
    public ResponseEntity<TaskInfoDto> getTaskById(@PathVariable Long id, Authentication authentication) {
        return taskService.getTaskById(id, authentication);
    }

    @GetMapping("/statuses")
    @Operation(
            summary = "Getting a list of task statuses",
            description = "Useful when creating a task or changing its status"
    )
    public ResponseEntity<?> getTaskStatuses() {
        return null;
    }

    @PostMapping
    @Operation(
            summary = "Create a task",
            description = "Here you can create a new task with your description and title"
    )
    public ResponseEntity<TaskInfoDto> createTask(@RequestBody @Valid TaskCreateDto task, Authentication authentication) {
        return taskService.create(task, authentication);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Edit a task",
            description = "Here you can edit a task description, title and task status"
    )
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return null;
    }

    @DeleteMapping("/id")
    @Operation(
            summary = "Delete a task",
            description = "Here you can delete a task"
    )
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        return null;
    }
}
