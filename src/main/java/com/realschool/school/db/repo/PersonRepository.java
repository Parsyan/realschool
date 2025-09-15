package com.realschool.school.db.repo;

import com.realschool.school.db.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {



    boolean existsByEmail(String email);

    Person findByEmail(String email);
}
