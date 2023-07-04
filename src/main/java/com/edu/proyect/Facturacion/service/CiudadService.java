package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.Payloads.CiudadesDto;
import com.edu.proyect.Facturacion.models.Ciudad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CiudadService extends GenericService<Ciudad, Integer>{
    Page<Ciudad> findAllCiudadesPorAlmacenes(Pageable pageable);

}
