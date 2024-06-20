package com.michael.valencia.prototypes.api.template.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.michael.valencia.prototypes.api.template.models.User;
import com.michael.valencia.prototypes.api.template.repositories.UserRepository;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> listUsers() {
       return userRepository.findAll();    }

    @Override
    public User createUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User id does not exist.");
        }
        return existingUser.get();
    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (!existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User id does not exist.");
        }
        return userRepository.save(user);    }

}
