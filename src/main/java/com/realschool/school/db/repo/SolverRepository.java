package com.realschool.school.db.repo;

import com.realschool.school.db.model.relationship.Solver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SolverRepository extends JpaRepository<Solver, Long> {


    boolean existsBySolver_Id(Long id);

    List<Solver> findBySolver_Id(Long id);
}
