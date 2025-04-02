package com.pet_love.demo.service;

import com.pet_love.demo.model.Person;
import com.pet_love.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllOwners() {
        return personRepository.findAll();
    }

    public Optional<Person> getOwnerById(Long id) {
        return personRepository.findById(id);
    }

    public Person addOwner(Person person) {
        return personRepository.save(person);
    }

    public void deleteOwner(Long id) {
        personRepository.deleteById(id);
    }

}
