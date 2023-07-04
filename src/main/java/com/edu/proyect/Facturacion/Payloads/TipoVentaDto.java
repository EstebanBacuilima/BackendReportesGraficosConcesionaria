package com.edu.proyect.Facturacion.Payloads;

import javax.persistence.Column;

public interface TipoVentaDto {

    @Column(name = "tipo_venta")
    String getTipo_venta();
}
