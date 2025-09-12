package com.realschool.school.controller;

import com.realschool.school.db.model.Person;
import com.realschool.school.db.model.Task;
import com.realschool.school.db.model.relationship.Solver;
import com.realschool.school.db.repo.PersonRepository;
import com.realschool.school.db.repo.SolverRepository;
import com.realschool.school.db.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {
    private PersonRepository personRepository;
    private TaskRepository taskRepository;
    private SolverRepository solverRepository;

    @Autowired
    public MainController(PersonRepository personRepository, TaskRepository taskRepository, SolverRepository solverRepository) {
        this.personRepository = personRepository;
        this.taskRepository = taskRepository;
        this.solverRepository = solverRepository;
    }






    @GetMapping("person")
    public List<Person> personList(){
        return (List<Person>) personRepository.findAll();
    }

    @GetMapping("task")
    public List<Task> taskList(){
        return (List<Task>) taskRepository.findAll();
    }

    @GetMapping("solver")
    public List<Solver> solverList(){
        return (List<Solver>) solverRepository.findAll();
    }

    @PostMapping(value = {"person/add"})
    public Person addPerson(@RequestBody Person person){
        return personRepository.save(person);
    }

    @PostMapping(value = {"task/add"})
    public Task addTask(@RequestBody Task task){
        Person creator = personRepository.findById(task.getCreator().getId()).get();
        Person confirmer = personRepository.findById(task.getConfirmer().getId()).get();

        task.setCreator(creator);
        task.setConfirmer(confirmer);
        return taskRepository.save(task);
    }

    @PostMapping(value = {"solver/add"})
    public Solver addSolver(@RequestBody Solver solver){
        Person solverr = personRepository.findById(solver.getSolver().getId()).get();
        Task task = taskRepository.findById(solver.getTask().getId()).get();

        solver.setSolver(solverr);
        solver.setTask(task);
        return solverRepository.save(solver);
    }


    
}
