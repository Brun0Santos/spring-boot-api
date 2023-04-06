package com.bruno.api;

import com.bruno.api.model.Person;
import com.bruno.api.repository.PersonRepository;
import com.bruno.api.services.PersonServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonServicesTest {
    MockPerson input;

    @InjectMocks
    private PersonServices services;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setupMocks() {
        this.input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByIdTest() {
        Person person = input.mockEntity();
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        Person result = services.findById(String.valueOf(1L));
        assertNotNull(result);
        System.out.println(result);
        assertEquals("First Name Test0", result.getFirstName());

    }
}