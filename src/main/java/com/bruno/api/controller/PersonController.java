package com.bruno.api.controller;


import com.bruno.api.dto.PersonDto;
import com.bruno.api.model.Person;
import com.bruno.api.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for managing people")
public class PersonController {

    @Autowired
    PersonServices services;

    @Autowired
    ModelMapper mapper;

    @GetMapping
    @Operation(summary = "Finds all people", description = "Finds all people from database", tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PersonDto.class))
                            )
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public List<PersonDto> findAll() {
        return services.getAll().stream().map(people ->
                mapper.map(people, PersonDto.class)).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/{id}")
    @Operation(summary = "Finds all people from ID", description = "Find specific people from database",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public PersonDto findById(@PathVariable("id") String id) {
        return mapper.map(services.findById(id), PersonDto.class);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Register people", description = "Register people in the database",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<PersonDto> createPeople(@RequestBody Person people) {
        PersonDto personDto = services.create(people);
        return ResponseEntity.status(HttpStatus.CREATED).body(personDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Delete a person", description = "Delete specific person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Bad Request", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> deletePeople(@PathVariable("id") Long id) {
        services.deletePeople(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Change people data", description = "Change data for specific people",
            tags = {"People"})
    public void alterPeople(@RequestBody Person person) {
        services.changePersonData(person);
    }
}
