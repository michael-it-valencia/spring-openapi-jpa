package com.michael.valencia.prototypes.api.template.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michael.valencia.prototypes.api.template.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findById(Integer id);

}
