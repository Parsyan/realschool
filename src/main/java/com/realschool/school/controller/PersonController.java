package com.realschool.school.controller;

import com.realschool.school.controller.exception.NoSuchEntityException;
import com.realschool.school.db.model.Person;
import com.realschool.school.dto.requestBody.personRequestBody.PersonRequestBody;
import com.realschool.school.dto.requestBody.personRequestBody.PersonRequestListBody;
import com.realschool.school.dto.service.personService.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;



    @PostMapping(value = {"add"})
    public ResponseEntity<PersonRequestBody> addPerson(@RequestBody PersonRequestBody person){
        return new ResponseEntity<>(personService.addPerson(person), HttpStatus.CREATED);
    }

    @PostMapping({"addList"})
    public ResponseEntity<List<PersonRequestBody>> addPersonList(@RequestBody PersonRequestListBody personRequestListBody) throws NullPointerException, NoSuchEntityException, IllegalArgumentException {
        return new ResponseEntity<>(personService.addPersonList(personRequestListBody), HttpStatus.CREATED);
    }


    @GetMapping("list")
    public List<Person> personList(){
        return personService.getAllPerson();
    }


    @GetMapping
    public ResponseEntity<Person> findPerson(@RequestBody Long id) throws NullPointerException, NoSuchEntityException{
        return new ResponseEntity<>(personService.findPerson(id), HttpStatus.FOUND);
    }

    @PutMapping("update")
    public ResponseEntity<Person> updateTask(@RequestBody PersonRequestBody personRequestBody) throws IllegalArgumentException, NullPointerException, NoSuchEntityException{
        return new ResponseEntity<>(personService.updatePerson(personRequestBody), HttpStatus.ACCEPTED);
    }

    @DeleteMapping({"delete", "remove", "rm"})
    public ResponseEntity<HttpStatus> deleteTask(@RequestBody Long id) throws NullPointerException, NoSuchEntityException{
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    @GetMapping("solver")
//    public List<Solver> solverList(){
//        return (List<Solver>) solverRepository.findAll();
//    }


//  Move into new SolverController class
//    @PostMapping(value = {"solver/add"})
//    public Solver addSolver(@RequestBody Solver solver){
//        Person solverr = personRepository.findById(solver.getSolver().getId()).get();
//        Task task = taskRepository.findById(solver.getTask().getId()).get();
//
//        solver.setSolver(solverr);
//        solver.setTask(task);
//        return solverRepository.save(solver);
//    }


    
}
