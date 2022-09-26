package com.test.clientescrud.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "Roles")
@Getter
@Setter
public class Role {
    @Id
    private String _id;
  
    private String name;
  
}
