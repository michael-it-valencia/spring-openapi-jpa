package com.michael.valencia.prototypes.api.template.service;

import java.util.List;

import com.michael.valencia.prototypes.api.template.models.User;

public interface UserService {

    List<User> listUsers();

    User createUser(User user);

    User findUserById(Integer id);

    User updateUser(User user);

}
