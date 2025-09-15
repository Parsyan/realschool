package com.realschool.school.dto.service.solverService;

import com.realschool.school.controller.exception.NoSuchEntityException;
import com.realschool.school.db.model.relationship.Solver;
import com.realschool.school.db.repo.PersonRepository;
import com.realschool.school.db.repo.SolverRepository;
import com.realschool.school.db.repo.TaskRepository;
import com.realschool.school.dto.requestBody.solverRequestBody.SolverRequestBody;
import com.realschool.school.dto.requestBody.solverRequestBody.SolverRequestListBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SolverServiceImpl implements SolverService {

    private final SolverRepository solverRepository;
    private final PersonRepository personRepository;
    private final TaskRepository taskRepository;

    @Override
    public SolverRequestBody addSolver(SolverRequestBody solverRequestBody) throws NoSuchEntityException, NullPointerException, IllegalArgumentException {

//        Checking are user entered data empty.

        if (solverRequestBody.getSolver_id() == null || solverRequestBody.getSolver_id() == 0){
            throw new NullPointerException("solver_id cannot be null");
        }
        if (solverRequestBody.getTask_id() == null || solverRequestBody.getTask_id() == 0){
            throw new NullPointerException("task_id cannot be null");
        }

//        Checking exists task and solver in db or not.

        if (!taskRepository.existsById(solverRequestBody.getTask_id())){
            throw new NoSuchEntityException("Solver isn't exist ");
        }
        if (!personRepository.existsById(solverRequestBody.getSolver_id())){
            throw new NoSuchEntityException("Task isn't exist ");
        }

//        Checking is this Solver-Task relation exist
        List<Solver> solversList = solverRepository.findBySolver_Id(solverRequestBody.getSolver_id());

        for (Solver solver : solversList){
            if (solver.getTask().getId().equals(solverRequestBody.getSolver_id())){
                throw new IllegalArgumentException("Solver already must solve it : (This relation already exist)");
            }
        }

        solverRepository.save(Solver.builder()
                .task(taskRepository.findById(solverRequestBody.getTask_id()).get())
                .solver(personRepository.findById(solverRequestBody.getSolver_id()).get())
                .build());


        return solverRequestBody;
    }

    @Override
    public List<SolverRequestBody> addSolverList(SolverRequestListBody solverRequestListBody) throws NoSuchEntityException, NullPointerException, IllegalArgumentException {
        if (solverRequestListBody == null || solverRequestListBody.getSolverRequestBodyList() == null || solverRequestListBody.getSolverRequestBodyList().isEmpty()) {
            throw new NullPointerException("solverRequestListBody cannot be null ");
        }

        for (SolverRequestBody solverRequestBody : solverRequestListBody.getSolverRequestBodyList()) {
            addSolver(solverRequestBody);
        }

        return solverRequestListBody.getSolverRequestBodyList();
    }

    @Override
    public List<Solver> getAllSolver() {
        return solverRepository.findAll();
    }

    @Override
    public Solver findSolver(Long id) throws NullPointerException, NoSuchEntityException {
        if (id == null || id == 0){
            throw new NullPointerException("Id must not be null");
        }
        if (!solverRepository.existsById(id)){
            throw new NoSuchEntityException("This Solver-Task relation isn't exists");
        }

        return solverRepository.findById(id).get();
    }

    @Override
    public void deleteSolver(Long id) throws NullPointerException, NoSuchEntityException {
        if (id == null || id == 0){
            throw new NullPointerException("Id must not be null");
        }
        if (!solverRepository.existsById(id)){
            throw new NoSuchEntityException("This Solver-Task relation isn't exists");
        }

        solverRepository.deleteById(id);
    }
}
