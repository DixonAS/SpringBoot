package com.test.clientescrud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.test.clientescrud.models.Clientes;

public interface ICliente extends MongoRepository<Clientes, String> {

}
