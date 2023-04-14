package com.bruno.api.services;


import com.bruno.api.model.User;
import com.bruno.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {
    private final Logger logger = Logger.getLogger(UserService.class.getName());
    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one by user");
        User byUSerName = repository.findByUSerName(username);
        if (byUSerName != null) {
            return byUSerName;
        } else {
            throw new UsernameNotFoundException("user " + username + " not found");
        }
    }
}
