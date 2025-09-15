package com.realschool.school.controller;

import com.realschool.school.controller.exception.NoSuchEntityException;
import com.realschool.school.db.model.Task;
import com.realschool.school.dto.requestBody.taskRequestBody.TaskRequestBody;
import com.realschool.school.dto.requestBody.taskRequestBody.TaskRequestListBody;
import com.realschool.school.dto.service.taskService.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class can throw NoSuchEntityException, NullPointerException, IllegalArgumentException

@RestController
@RequestMapping("task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping({"add"})
    public ResponseEntity<TaskRequestBody> addTask(@RequestBody TaskRequestBody task) throws NullPointerException, IllegalArgumentException, NoSuchEntityException {
        return new ResponseEntity<>(taskService.addTaskWithSolver(task), HttpStatus.CREATED);
    }

    @PostMapping({"addList"})
    public ResponseEntity<List<TaskRequestBody>> addTaskListWithSolvers(@RequestBody TaskRequestListBody taskRequestListBody) throws NullPointerException, NoSuchEntityException, IllegalArgumentException {
        return new ResponseEntity<>(taskService.addTaskListWithSolvers(taskRequestListBody), HttpStatus.CREATED);
    }

    @GetMapping("list")
    public List<Task> taskList(){
        return taskService.getAllTask();
    }

    @GetMapping
    public ResponseEntity<Task> findTask(@RequestBody Long id) throws NullPointerException, NoSuchEntityException{
        return new ResponseEntity<>(taskService.findTask(id), HttpStatus.FOUND);
    }

    @PutMapping("update")
    public ResponseEntity<Task> updateTask(@RequestBody TaskRequestBody taskRequestBody) throws IllegalArgumentException, NullPointerException, NoSuchEntityException{
        return new ResponseEntity<>(taskService.updateTask(taskRequestBody), HttpStatus.ACCEPTED);
    }

    @DeleteMapping({"delete", "remove", "rm"})
    public ResponseEntity<HttpStatus> deleteTask(@RequestBody Long id) throws NullPointerException, NoSuchEntityException{
            taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }



}

