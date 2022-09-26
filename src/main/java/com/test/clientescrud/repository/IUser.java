package com.test.clientescrud.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.clientescrud.models.Usuarios;

public interface IUser extends MongoRepository<Usuarios, String> {
  Optional<Usuarios> findByUsername(String username);
  Optional<Usuarios> findByUsernameOrEmail(String username, String email);

   Boolean existsByUsername(String username); 

   Boolean existsByEmail(String email) ;
  }