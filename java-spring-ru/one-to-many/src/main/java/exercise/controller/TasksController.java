package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    public TaskRepository taskRepository;

    @Autowired
    public TaskMapper taskMapper;

    @Autowired
    public UserRepository userRepository;

    @PostMapping(path="")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        var task = taskMapper.map(taskCreateDTO);
        taskRepository.save(task);

        return taskMapper.map(task);
    }

    @GetMapping(path="/{id}")
    public TaskDTO show(@PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        return taskMapper.map(task);
    }

    @GetMapping(path="")
    public List<TaskDTO> index() {
        return taskRepository.findAll().stream()
                .map((t) -> taskMapper.map(t))
                .toList();
    }

    @PutMapping(path="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable long id, @Valid @RequestBody TaskUpdateDTO taskUpdateDTO) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        taskMapper.update(taskUpdateDTO, task);
        var user = userRepository.findById(taskUpdateDTO.getAssigneeId())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        task.setAssignee(user);
        taskRepository.save(task);
    }

    @DeleteMapping(path="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        taskRepository.delete(task);
    }
    // END
}
