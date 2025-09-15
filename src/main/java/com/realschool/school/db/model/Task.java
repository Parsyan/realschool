package com.realschool.school.db.model;

import com.realschool.school.db.model.relationship.Solver;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@ToString
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "query", nullable = false)
    private String query;

    private String solution;
    private boolean solved;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    @ToString.Exclude
    private Person creator;

    @ManyToOne
    @JoinColumn(name = "confirmer_id", nullable = false)
    @ToString.Exclude
    private Person confirmer;

    @OneToMany
    @MapsId("task")
    @ToString.Exclude
    private List<Solver> solverList;

//
//    public Task(Long id, String title, String query, String solution, boolean solved, Person creator, Person confirmer) {
//        this.id = id;
//        this.title = title;
//        this.query = query;
//        this.solution = solution;
//        this.solved = solved;
//        this.creator = creator;
//        this.confirmer = confirmer;
//    }

//    public Task(){}

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Task task = (Task) o;
        return getId() != null && Objects.equals(getId(), task.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
