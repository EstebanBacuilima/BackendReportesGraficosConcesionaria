package com.edu.proyect.Facturacion.Payloads;

import javax.persistence.Column;

public interface MarcasDto {

    @Column(name = "nombre_marca")
    String getNombre_marca();
}
