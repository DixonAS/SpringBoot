package com.test.clientescrud.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Document(value = "Cliente")
@Getter
@Setter
@NoArgsConstructor

public class Clientes {

    @Id
	private String _id;

    @Indexed(unique = true)
    private String rut;    
    @NonNull
    private String nombre;

    @NonNull
    private String apellido;

    @NotBlank
    @Size(max = 50)
    @Email
    @Indexed(unique = true) 
    private String email;
    
    private String telefono;
    private String direccion;
    
}
