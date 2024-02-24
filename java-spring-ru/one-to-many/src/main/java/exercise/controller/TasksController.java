package exercise.controller;

import java.util.List;
import java.util.stream.Collectors;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.model.Task;
import exercise.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Iterable<TaskDTO> index() {
        return taskRepository.findAll().stream().map(x -> taskMapper.map(x)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TaskDTO create(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        Task task = taskMapper.map(taskCreateDTO);

        task.setAssignee(userRepository.findById(taskCreateDTO.getAssigneeId()).get());

        return taskMapper.map(taskRepository.save(task));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskDTO show(@PathVariable Long id) {
        Task task = taskRepository
                .findById(id).orElseThrow();
        TaskDTO taskDTO = taskMapper.map(task);
        taskDTO.setAssigneeId(task.getAssignee().getId());

        return taskDTO;
    }

    @PutMapping("/{id}")
    TaskDTO update(@PathVariable Long id, @RequestBody TaskUpdateDTO taskUpdateDTO) {
        Task taskFromBase = taskRepository.findById(id).orElseThrow();

        taskMapper.update(taskUpdateDTO, taskFromBase);
        taskFromBase.setAssignee(userRepository.findById(taskUpdateDTO.getAssigneeId()).get());

        taskRepository.save(taskFromBase);

        return taskMapper.map(taskFromBase);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
    // END
}