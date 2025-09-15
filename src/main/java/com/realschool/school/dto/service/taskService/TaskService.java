package com.realschool.school.dto.service.taskService;

import com.realschool.school.controller.exception.NoSuchEntityException;
import com.realschool.school.db.model.Task;
import com.realschool.school.dto.requestBody.taskRequestBody.TaskRequestBody;
import com.realschool.school.dto.requestBody.taskRequestBody.TaskRequestListBody;

import java.util.List;

public interface TaskService {

    TaskRequestBody addTaskWithSolver(TaskRequestBody taskRequestBody) throws NoSuchEntityException, NullPointerException, IllegalArgumentException;

    List<TaskRequestBody> addTaskListWithSolvers(TaskRequestListBody taskRequestListBody) throws NoSuchEntityException, NullPointerException, IllegalArgumentException;

    List<Task> getAllTask();
    Task findTask(Long id) throws NullPointerException, NoSuchEntityException;

    Task updateTask(TaskRequestBody taskRequestBody) throws IllegalArgumentException, NullPointerException, NoSuchEntityException;


    void deleteTask(Long id) throws NullPointerException, NoSuchEntityException;

//    TaskRequestBody taskAdd(TaskRequestBody taskRequestBody) throws NonUniqueObjectException;
}
