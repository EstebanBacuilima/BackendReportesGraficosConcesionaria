package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.models.Categoria;
import com.edu.proyect.Facturacion.models.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehiculoService extends GenericService<Vehiculo, Integer>{

    public Page<Vehiculo> findByAll(Pageable pageable);



}
