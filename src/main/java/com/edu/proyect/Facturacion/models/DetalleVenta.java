package com.edu.proyect.Facturacion.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "detalles_ventas")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_venta")
    private int idDetalleVenta;
    private Integer cantidad;
    private String detalles;
    private Boolean estado;

    // RELACIONES
    @ManyToOne
    @JoinColumn(name="id_vehiculo",referencedColumnName ="id_vehiculo")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name="id_venta",referencedColumnName ="id_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name="id_tipo_venta",referencedColumnName ="id_tipo_venta")
    private TipoVenta tipoVenta;
}
