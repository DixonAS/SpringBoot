package com.test.clientescrud.controller;

import com.test.clientescrud.exceptions.ResourceNotFoundException;
import com.test.clientescrud.models.Clientes;
import com.test.clientescrud.repository.ICliente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity < Clientes > update(@PathVariable(value = "id") String id,
        @Valid @RequestBody Clientes clientDetails) 
            throws ResourceNotFoundException {
                Clientes client = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Cliente no encontrado en id :: " + id));
        
            client.setNombre(clientDetails.getNombre());
            client.setApellido(clientDetails.getApellido());
            client.setTelefono(clientDetails.getTelefono());
            client.setDireccion(clientDetails.getDireccion());
        final Clientes updatedEmployee = repository.save(client);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/client/{id}")
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") String id)
    throws ResourceNotFoundException {
        Clientes client = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado en id  :: " + id));

            repository.delete(client);
        Map < String, Boolean > response = new HashMap <>();
        response.put("Eliminado", Boolean.TRUE);
        return response;
    }

}
