package com.test.clientescrud.controller;

import com.test.clientescrud.models.Clientes;
import com.test.clientescrud.repository.ICliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

@RequestMapping("/api/clients")
@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
@RestController
public class ClienteController {

    @Autowired
    private ICliente repository;
    
	@PostMapping("/client")
	public Clientes create(@Validated @RequestBody Clientes p) {
		return repository.insert(p);
	}

	@GetMapping("/")
	public List<Clientes> readAll() {
		return repository.findAll();
	}

	@PutMapping("/client/{id}")
	public Clientes update(@PathVariable String id, @Validated @RequestBody Clientes p) {
		return repository.save(p);
	}

	@DeleteMapping("/client/{id}")
	public void delete(@PathVariable String id) {
		repository.deleteById(id);
	}
}
