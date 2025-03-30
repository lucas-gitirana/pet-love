package com.pet_love.demo.controller;

import com.pet_love.demo.model.Owner;
import com.pet_love.demo.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OwnerController{

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/owners")
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("owner/{id}")
    public Optional<Owner> getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

    @PostMapping("/owner")
    public Owner addOwner(@RequestBody Owner owner) {
        return ownerService.addOwner(owner);
    }

    @DeleteMapping("owner/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }

}
