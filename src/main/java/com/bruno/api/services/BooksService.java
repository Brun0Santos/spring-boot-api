package com.bruno.api.services;


import com.bruno.api.model.Books;
import com.bruno.api.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BooksService {
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());
    @Autowired
    BooksRepository repository;

    public List<Books> getAll() {
        logger.info("Finding all book");
        return repository.findAll();
    }
}
