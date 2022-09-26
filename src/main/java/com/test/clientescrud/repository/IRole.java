package com.test.clientescrud.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.clientescrud.models.ERole;
import com.test.clientescrud.models.Role;

public interface IRole extends MongoRepository<Role, String> {
   Optional<Role> findByName(ERole name);
  }
  
  