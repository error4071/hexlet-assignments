package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.dto.UserDTO;
import exercise.mapper.TaskMapper;
import exercise.model.Task;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private List<Task> tasks;
    // BEGIN
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    TaskMapper taskMapper;

    @GetMapping("/tasks")
    ResponseEntity<List<TaskDTO>> index() {
        var tasks = taskRepository.findAll();
        var result = tasks.stream()
                .map(taskMapper::map)
                .toList();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(tasks.size()))
                .body(result);
    }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskDTO show(@PathVariable Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return taskMapper.map(task);
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    TaskDTO create(@Valid @RequestBody TaskCreateDTO taskData) {
        var task = taskMapper.map(taskData);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskDTO update(@RequestBody TaskUpdateDTO taskData, @PathVariable Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        taskMapper.update(taskData, task);
        taskRepository.save(task);
        var taskDTO = taskMapper.map(task);
        return taskDTO;
    }

    @DeleteMapping("/tasks/{id}")
    public void destroy(@PathVariable Long id) {
        tasks.removeIf(p -> p.getId() == id);
    }
    // END
}
