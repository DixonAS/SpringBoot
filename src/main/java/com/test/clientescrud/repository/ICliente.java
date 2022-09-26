package com.test.clientescrud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.test.clientescrud.models.Clientes;
import java.util.Optional;

public interface ICliente extends MongoRepository<Clientes, String> {
    Optional<Clientes> findByRut(String rut); 
    Optional<Clientes> findByEmail(String email);   
}
