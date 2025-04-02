package com.pet_love.demo.controller;

import com.pet_love.demo.model.Person;
import com.pet_love.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/people")
    public List<Person> getAllOwners() {
        return personService.getAllOwners();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> getOwnerById(@PathVariable Long id) {
        return personService.getOwnerById(id);
    }

    @PostMapping("/person")
    public Person addOwner(@RequestBody Person person) {
        return personService.addOwner(person);
    }

    @DeleteMapping("person/{id}")
    public void deleteOwner(@PathVariable Long id) {
        personService.deleteOwner(id);
    }

}
