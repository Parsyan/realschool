package com.realschool.school.db.repo;

import com.realschool.school.db.model.relationship.Solver;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SolverRepository extends CrudRepository<Solver, Long> {
    @Override
    Optional<Solver> findById(Long aLong);
}
