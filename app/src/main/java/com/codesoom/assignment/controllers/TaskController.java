package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> list() {return tasks;}

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    @PatchMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task body) {
        Task task = taskFindId(id);
        task.setTitle(body.getTitle());

        return task;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tasks.remove(taskFindId(id));
    }

    private Long generateId() {
        newId +=1;
        return newId;
    }

    private Task taskFindId(Long id) {
        return tasks.stream().filter(i -> Objects.equals(i.getId(), id))
                .findFirst()
                .orElse(null);
    }

}
