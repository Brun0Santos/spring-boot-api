package com.bruno.api.services;

import com.bruno.api.dto.PersonDto;
import com.bruno.api.exceptions.NotFoundResponseException;
import com.bruno.api.model.Person;
import com.bruno.api.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    @Autowired
    PersonRepository repository;

    @Autowired
    ModelMapper mapper;
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id) {
        logger.info("Finding one person!");
        return repository.findById(Long.parseLong(id)).orElseThrow(() ->
                new NotFoundResponseException("Person not found "));
    }

    public List<Person> getAll() {
        logger.info("Finding all person!");
        return repository.findAll();
    }

    public PersonDto create(Person person) {
        logger.info("Creating one person!");
        repository.save(person);
        return mapper.map(person, PersonDto.class);
    }

    public void deletePeople(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundResponseException("Not found people from base");
        }
        repository.deleteById(id);
    }

    public void changePersonData(Person person) {
        if (!repository.existsById(person.getId())) {
            throw new NotFoundResponseException("Not found people from base");
        }
        repository.findById(person.getId()).ifPresent(el -> {
            el.setFirstName(person.getFirstName());
            el.setLastName(person.getLastName());
            el.setAddress(person.getAddress());
            el.setGender(person.getGender());
        });
        repository.save(person);
    }
}
