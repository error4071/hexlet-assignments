package exercise.controller;

import java.util.List;
import java.util.stream.Collectors;

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

    // BEGIN
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    Iterable<TaskDTO> index() {
        return taskRepository.findAll().stream().map(x -> taskMapper.map(x)).collect(Collectors.toList());
    }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskDTO show(@PathVariable Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        TaskDTO taskDTO = taskMapper.map(task);
        taskDTO.setAssigneeId(task.getAssignee().getId());
        return taskDTO;
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    TaskDTO create(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        var task = taskMapper.map(taskCreateDTO);
        task.setAssignee(userRepository.findById(taskCreateDTO.getAssigneeId()).get());
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @PutMapping("/tasks/{id}")
    TaskDTO update(@PathVariable Long id, @RequestBody TaskUpdateDTO taskUpdateDTO) {
        Task taskFromBase = taskRepository.findById(id).orElseThrow();

        taskMapper.update(taskUpdateDTO, taskFromBase);
        taskFromBase.setAssignee(userRepository.findById(taskUpdateDTO.getAssigneeId()).get());

        taskRepository.save(taskFromBase);

        return taskMapper.map(taskFromBase);
    }

    @DeleteMapping("/tasks/{id}")
    public void destroy(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
    // END
}
