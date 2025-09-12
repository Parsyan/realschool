package com.realschool.school.db.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String query;
    private String solution;
    private boolean solved;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Person creator;

    @ManyToOne
    @JoinColumn(name = "confirmer_id", nullable = false)
    private Person confirmer;


//
    public Task(Long id, String query, String solution, boolean solved, Person creator, Person confirmer) {
        this.id = id;
        this.query = query;
        this.solution = solution;
        this.solved = solved;
        this.creator = creator;
        this.confirmer = confirmer;
    }

    public Task() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public Person getConfirmer() {
        return confirmer;
    }

    public void setConfirmer(Person confirmer) {
        this.confirmer = confirmer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return solved == task.solved && Objects.equals(id, task.id) && Objects.equals(query, task.query) && Objects.equals(solution, task.solution) && Objects.equals(creator, task.creator) && Objects.equals(confirmer, task.confirmer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, query, solution, solved, creator, confirmer);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", query='" + query + '\'' +
                ", solution='" + solution + '\'' +
                ", solved=" + solved +
                '}';
    }

}
