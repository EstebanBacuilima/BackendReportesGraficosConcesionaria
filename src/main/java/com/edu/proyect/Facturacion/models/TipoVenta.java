package com.edu.proyect.Facturacion.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "tipos_ventas")
public class TipoVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_venta")
    private int idTipoVenta;
    private String tipoVenta;
    private Boolean estado;
}
