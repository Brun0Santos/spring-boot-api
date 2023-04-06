package com.bruno.api.controller;


import com.bruno.api.dto.PersonDto;
import com.bruno.api.model.Person;
import com.bruno.api.services.PersonServices;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonServices services;

    @Autowired
    ModelMapper mapper;

    @GetMapping
    public List<PersonDto> findAll() {
        return services.getAll().stream().map(people ->
                mapper.map(people, PersonDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDto findById(@PathVariable("id") String id) {
        return mapper.map(services.findById(id), PersonDto.class);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PersonDto> createPeople(@RequestBody Person people) {
        PersonDto personDto = services.create(people);
        return ResponseEntity.status(HttpStatus.CREATED).body(personDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletePeople(@PathVariable("id") Long id) {
        services.deletePeople(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @Transactional
    public void alterPeople(@RequestBody Person person) {
        services.changePersonData(person);
    }
}
