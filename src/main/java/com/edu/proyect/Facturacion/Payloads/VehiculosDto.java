package com.edu.proyect.Facturacion.Payloads;

import javax.persistence.Column;

public interface VehiculosDto {
    @Column(name = "nombre_vehiculo")
    String getNombre_vehiculo();
}
