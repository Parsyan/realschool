package com.realschool.school.dto.service.personService;

import com.realschool.school.controller.exception.NoSuchEntityException;
import com.realschool.school.db.model.Person;
import com.realschool.school.dto.requestBody.personRequestBody.PersonRequestBody;
import com.realschool.school.dto.requestBody.personRequestBody.PersonRequestListBody;

import java.util.List;

public interface PersonService {

    PersonRequestBody addPerson(PersonRequestBody personRequestBody) throws NoSuchEntityException, NullPointerException, IllegalArgumentException;

    List<PersonRequestBody> addPersonList(PersonRequestListBody personRequestListBody) throws NoSuchEntityException, NullPointerException, IllegalArgumentException;

    List<Person> getAllPerson();

    Person findPerson(Long id) throws NullPointerException, NoSuchEntityException;

    Person updatePerson(PersonRequestBody personRequestBody) throws IllegalArgumentException, NullPointerException, NoSuchEntityException;


    void deletePerson(Long id) throws NullPointerException, NoSuchEntityException;

}
