package com.test.clientescrud.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;


@Getter
@Setter
@Document(value = "Cliente")

public class Clientes {

    @Id
	private String _id;
     
    @Indexed(unique = true)
    private String rut;    
    @NonNull
    private String nombre;

    @NonNull
    private String apellido;

    @Email
    @Indexed(unique = true) 
    private String email;
    
    private String telefono;
    private String direccion;
   
    public Clientes() {

    }

    public Clientes(String rut, String nombre,String apellido,String email,String telefono, String direccion) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;        
    }

}
