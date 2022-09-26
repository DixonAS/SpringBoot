package com.test.clientescrud.exceptions;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

	private HttpStatus estado;
	private String mensaje;

    public ClientException(HttpStatus estado, String mensaje) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
	}

	public ClientException(HttpStatus estado, String mensaje, String mensaje1) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
		this.mensaje = mensaje1;
	}
}
