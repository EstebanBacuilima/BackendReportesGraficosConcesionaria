package com.edu.proyect.Facturacion.Payloads;

import javax.persistence.Column;

public interface CategoriaDto {

    @Column(name = "nombre_categoria")
    String getNombre_categoria();
}
