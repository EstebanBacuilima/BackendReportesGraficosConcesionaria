package com.edu.proyect.Facturacion.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private int idVenta;
    private String numeroVenta;
    private LocalDate fechaVenta;
    private Boolean estado;

    // RELACION
    @ManyToOne
    @JoinColumn(name="id_cliente",referencedColumnName ="id_cliente")
    private Cliente cliente;

}
