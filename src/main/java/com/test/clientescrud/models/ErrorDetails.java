package com.test.clientescrud.models;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {

    private Date marcaDeTiempo;
    private String mensaje;
    private String detalles;

    public ErrorDetails(Date marcaDeTiempo, String mensaje, String detalles) {
        super();
        this.marcaDeTiempo = marcaDeTiempo;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

}
