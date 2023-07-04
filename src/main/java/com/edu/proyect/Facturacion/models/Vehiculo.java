package com.edu.proyect.Facturacion.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private int idVehiculo;
    private String  nombreVehiculo;
    private String year;
    private String chasis;
    private String color;
    private Boolean estado;

    // RELACIONES

    @ManyToOne
    @JoinColumn(name="id_almacen",referencedColumnName ="id_almacen")
    private Almacen almacen;

    @ManyToOne
    @JoinColumn(name="id_marca",referencedColumnName ="id_marca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name="id_categoria",referencedColumnName ="id_categoria")
    private Categoria categoria;
}
