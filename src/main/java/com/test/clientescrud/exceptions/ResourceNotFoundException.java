package com.test.clientescrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String nombreDelRecurso;
	private String nombreDelCampo;
	private long valorDelCampo;

	public ResourceNotFoundException(String nombreDelRecurso, String nombreDelCampo, long valorDelCampo) {
		super(String.format("%s no encontrada con : %s : '%s'", nombreDelRecurso, nombreDelCampo, valorDelCampo));
		this.nombreDelRecurso = nombreDelRecurso;
		this.nombreDelCampo = nombreDelCampo;
		this.valorDelCampo = valorDelCampo;
	}
    
}
