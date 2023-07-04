package com.edu.proyect.Facturacion.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "empreas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private int idEmpresa;
    private String  nombreEmpresa;
    private String objetivo;
    private Boolean estado;


}
