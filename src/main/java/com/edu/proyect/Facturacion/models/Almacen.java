package com.edu.proyect.Facturacion.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "almacenes")
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_almacen")
    private int idAlmacen;
    private String nombreAlmacen;
    private String direccionAlmacen;
    private Boolean estado;

    // RELACIONES
    @ManyToOne
    @JoinColumn(name="id_empresa",referencedColumnName ="id_empresa")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name="id_ciudad",referencedColumnName ="id_ciudad")
    private Ciudad ciudad;

}
