package com.realschool.school.dto.service.solverService;

import com.realschool.school.controller.exception.NoSuchEntityException;
import com.realschool.school.db.model.relationship.Solver;
import com.realschool.school.dto.requestBody.solverRequestBody.SolverRequestBody;
import com.realschool.school.dto.requestBody.solverRequestBody.SolverRequestListBody;

import java.util.List;

public interface SolverService {
    SolverRequestBody addSolver(SolverRequestBody solverRequestBody) throws NoSuchEntityException, NullPointerException, IllegalArgumentException;

    List<SolverRequestBody> addSolverList(SolverRequestListBody personRequestListBody) throws NoSuchEntityException, NullPointerException, IllegalArgumentException;

    List<Solver> getAllSolver();

    Solver findSolver(Long id) throws NullPointerException, NoSuchEntityException;


    void deleteSolver(Long id) throws NullPointerException, NoSuchEntityException;
}
