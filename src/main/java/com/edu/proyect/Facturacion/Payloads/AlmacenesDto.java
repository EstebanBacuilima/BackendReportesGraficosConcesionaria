package com.edu.proyect.Facturacion.Payloads;

import javax.persistence.Column;

public interface AlmacenesDto {

    @Column(name = "nombre_almacen")
    String getNombre_almacen();
}
