package com.realschool.school.dto.service.taskService;

import com.realschool.school.controller.exception.NoSuchEntityException;
import com.realschool.school.db.model.Task;
import com.realschool.school.db.model.relationship.Solver;
import com.realschool.school.db.repo.PersonRepository;
import com.realschool.school.db.repo.SolverRepository;
import com.realschool.school.db.repo.TaskRepository;
import com.realschool.school.dto.requestBody.taskRequestBody.TaskRequestBody;
import com.realschool.school.dto.requestBody.taskRequestBody.TaskRequestListBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final PersonRepository personRepository;
    private final SolverRepository solverRepository;

    @Override
    public TaskRequestBody addTaskWithSolver(TaskRequestBody taskRequestBody) throws NoSuchEntityException, NullPointerException, IllegalArgumentException {

//        Chacking entered data from client

        if (taskRequestBody.getTitle() == null || taskRequestBody.getTitle().isEmpty()){
            throw new NullPointerException("Task's title cannot be empty!");
        }

        if (taskRequestBody.getQuery() == null || taskRequestBody.getQuery().isEmpty()) {
            throw new NullPointerException("Task's query cannot be empty!");
        }

        if (taskRequestBody.getCreator_id() == null || taskRequestBody.getCreator_id() == 0) {
            throw new NullPointerException("Task's creator_id cannot be empty!");
        }

        if (taskRequestBody.getConfirmer_id() == null || taskRequestBody.getConfirmer_id() == 0) {
            throw new NullPointerException("Task's confirmer_id cannot be empty!");
        }

//      Checking on is creator, confirmer, solver exist in db

        if (!personRepository.existsById(taskRequestBody.getConfirmer_id())){
            throw new NoSuchEntityException("confirmer : " + taskRequestBody.getConfirmer_id() + " isn't exist");
        }
        if (!personRepository.existsById(taskRequestBody.getCreator_id())){
            throw new NoSuchEntityException("creator : " + taskRequestBody.getCreator_id() + "isn't exist");
        }
        if (!personRepository.existsById(taskRequestBody.getSolver_id())){
            throw new NoSuchEntityException("solver : " + taskRequestBody.getSolver_id() + "isn't exist");
        }

//      Chacking if Task with this title already exist or not.

        if (taskRepository.existsByTitle(taskRequestBody.getTitle())){
            throw new IllegalArgumentException("Task with this title : " + taskRequestBody.getTitle() + "already exist");
        }
//        Saving Task

        Task task = Task.builder()
                .title(taskRequestBody.getTitle())
                .query(taskRequestBody.getQuery())
                .solution(taskRequestBody.getSolution())
                .solved(taskRequestBody.getSolved())
                .confirmer(personRepository.findById(taskRequestBody.getConfirmer_id()).get())

                .creator(personRepository.findById(taskRequestBody.getCreator_id()).get())
                .build();

        task = taskRepository.save(task);

//      Saving who must solve it : Solver

        Solver solver = Solver.builder()
                .solver(personRepository.findById(taskRequestBody.getSolver_id()).get())// TODO Think what to do with this.
                .task(taskRepository.findById(task.getId()).get())
                .build();

        solverRepository.save(solver);

        return taskRequestBody;
    }

    @Override
    public List<TaskRequestBody> addTaskListWithSolvers(TaskRequestListBody taskRequestListBody) throws NoSuchEntityException, NullPointerException, IllegalArgumentException{

        if (taskRequestListBody == null || taskRequestListBody.getTaskRequestList() == null || taskRequestListBody.getTaskRequestList().isEmpty()) {
            throw new NullPointerException("taskRequestListBody cannot be null ");
        }

        for (TaskRequestBody taskRequestBody : taskRequestListBody.getTaskRequestList()) {
                addTaskWithSolver(taskRequestBody);
        }
        return taskRequestListBody.getTaskRequestList();
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task findTask(Long id) throws NullPointerException, NoSuchEntityException {

        if (id == null || id == 0){
            throw new NullPointerException("Id must not be null");
        }
        if (!taskRepository.existsById(id)){
            throw new NoSuchEntityException("Task isn't exists");
        }

        return taskRepository.findById(id).get();
    }

    @Override
    public void deleteTask(Long id) throws NullPointerException, NoSuchEntityException {
        if (id == null || id == 0){
            throw new NullPointerException("Id must not be null");
        }
        if (taskRepository.existsById(id)){
            throw new NoSuchEntityException("Task isn't exist");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Task updateTask(TaskRequestBody taskRequestBody) throws IllegalArgumentException, NullPointerException, NoSuchEntityException {
//      TODO Be careful this code can have problems with title of task (This can be solved I didn't remember)

        if (taskRequestBody.getSolver_id() != null){
            throw new IllegalArgumentException ("You can't add or update solver in TaskController. Only in Task creating time ");
        }

        if ((taskRequestBody.getQuery() == null || taskRequestBody.getQuery().isEmpty()) &&
                (taskRequestBody.getSolution() == null || taskRequestBody.getSolution().isEmpty()) &&
                (taskRequestBody.getSolved() == null) &&
                (taskRequestBody.getConfirmer_id() == null || taskRequestBody.getConfirmer_id() == 0)) {

            throw new NullPointerException("Nothing to update");
        }


        if (!taskRepository.existsByTitle(taskRequestBody.getTitle())){
            throw new NoSuchEntityException("Task isn't exist");
        }


        Task taskFromDB = taskRepository.findById(taskRequestBody.getTask_id()).get();

        
        if (taskRequestBody.getQuery() != null && !taskRequestBody.getQuery().isEmpty()){
            taskFromDB.setQuery(taskRequestBody.getQuery());    
        }
        if (taskRequestBody.getSolution() != null && !taskRequestBody.getSolution().isEmpty()) {
            taskFromDB.setSolution(taskRequestBody.getSolution());
        }
        if (taskRequestBody.getSolved() != null) {
            taskFromDB.setSolved(taskRequestBody.getSolved());
        }
        if (taskRequestBody.getConfirmer_id() != null && taskRequestBody.getConfirmer_id() != 0) {
            taskFromDB.setConfirmer(personRepository.findById(taskRequestBody.getConfirmer_id()).get());
        }

        return taskFromDB;
    }
}
