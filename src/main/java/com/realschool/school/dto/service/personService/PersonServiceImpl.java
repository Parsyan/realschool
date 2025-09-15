package com.realschool.school.dto.service.personService;

import com.realschool.school.controller.exception.NoSuchEntityException;
import com.realschool.school.db.model.Person;
import com.realschool.school.db.repo.PersonRepository;
import com.realschool.school.dto.requestBody.personRequestBody.PersonRequestBody;
import com.realschool.school.dto.requestBody.personRequestBody.PersonRequestListBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;

    @Override
    public PersonRequestBody addPerson(PersonRequestBody personRequestBody) throws NoSuchEntityException, NullPointerException, IllegalArgumentException {

        if (personRequestBody.getEmail() == null || personRequestBody.getEmail().isEmpty()){
            throw new NoSuchEntityException("Person's email cannot be empty!");
        }

        if (personRepository.existsByEmail(personRequestBody.getEmail())){
            throw new IllegalArgumentException("Person with this title : " + personRequestBody.getEmail() + "already exist");
        }

        personRepository.save(Person.builder()
                .email(personRequestBody.getEmail())
                .name(personRequestBody.getName())
                .surname(personRequestBody.getSurname())
                .age(personRequestBody.getAge())
                .build());

        return personRequestBody;
    }

    @Override
    public List<PersonRequestBody> addPersonList(PersonRequestListBody personRequestListBody) throws NoSuchEntityException, NullPointerException, IllegalArgumentException {

        if (personRequestListBody == null || personRequestListBody.getPersonRequestBodyList() == null || personRequestListBody.getPersonRequestBodyList().isEmpty()) {
            throw new NullPointerException("personRequestListBody cannot be null ");
        }

        for (PersonRequestBody personRequestBody : personRequestListBody.getPersonRequestBodyList()) {
            addPerson(personRequestBody);
        }

        return personRequestListBody.getPersonRequestBodyList();
    }

    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person findPerson(Long id) throws NullPointerException, NoSuchEntityException {
        if (id == null || id == 0){
            throw new NullPointerException("Id must not be null");
        }
        if (!personRepository.existsById(id)){
            throw new NoSuchEntityException("Person isn't exists");
        }

        return personRepository.findById(id).get();
    }

    @Override
    public Person updatePerson(PersonRequestBody personRequestBody) throws IllegalArgumentException, NullPointerException, NoSuchEntityException {

        if ((personRequestBody.getEmail() == null || personRequestBody.getEmail().isEmpty()) &&
                (personRequestBody.getName() == null || personRequestBody.getName().isEmpty()) &&
                personRequestBody.getSurname() == null || personRequestBody.getSurname().isEmpty() &&
                (personRequestBody.getAge() == 0)){

            throw new NullPointerException("Nothing to update");
        }

        if (!personRepository.existsByEmail(personRequestBody.getEmail())){
            throw new NoSuchEntityException("Person isn't exist");
        }

        Person personFromDB = personRepository.findByEmail(personRequestBody.getEmail());

        if (personRequestBody.getName() != null || !personRequestBody.getName().isEmpty()){
            personFromDB.setName(personRequestBody.getName());
        }
        if (personRequestBody.getSurname() != null || !personRequestBody.getSurname().isEmpty()){
            personFromDB.setSurname(personRequestBody.getSurname());
        }
        if (personRequestBody.getAge() != 0){
            personFromDB.setAge(personRequestBody.getAge());
        }


        return personFromDB;
    }

    @Override
    public void deletePerson(Long id) throws NullPointerException, NoSuchEntityException {
        if (id == null || id == 0){
            throw new NullPointerException("Id must not be null");
        }
        if (personRepository.existsById(id)){
            throw new NoSuchEntityException("Person isn't exist");
        }
        personRepository.deleteById(id);
    }
}
