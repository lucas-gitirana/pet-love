package com.pet_love.demo.controller;

import com.pet_love.demo.model.Owner;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OwnerController{

    @GetMapping("/owners")
    public List<Owner> getAllOwners() {
        return List.of(
            new Owner("Lucas", "47 9999-888", "lucas@email.com"),
            new Owner("Erick", "47 7777-555", "erick@email.com")
        );
    }

    @PostMapping("/owner")
    public Owner addOwner(@RequestBody Owner owner) {
        return owner;
    }

}
