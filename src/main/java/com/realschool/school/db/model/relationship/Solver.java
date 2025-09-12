package com.realschool.school.db.model.relationship;

import com.realschool.school.db.model.Person;
import com.realschool.school.db.model.Task;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Solver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solver_id")
    private Person solver;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;



//
    public Solver(Long id, Person solver, Task task) {
        this.id = id;
        this.solver = solver;
        this.task = task;
    }

    public Solver() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getSolver() {
        return solver;
    }

    public void setSolver(Person solver) {
        this.solver = solver;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Solver solver1 = (Solver) o;
        return Objects.equals(id, solver1.id) && Objects.equals(solver, solver1.solver) && Objects.equals(task, solver1.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, solver, task);
    }

    @Override
    public String toString() {
        return "Solver{" +
                "id=" + id +
                ", solver=" + solver +
                ", task=" + task +
                '}';
    }
}
