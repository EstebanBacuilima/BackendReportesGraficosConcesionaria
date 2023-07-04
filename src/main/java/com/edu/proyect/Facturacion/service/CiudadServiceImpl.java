package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.Payloads.CiudadesDto;
import com.edu.proyect.Facturacion.models.Ciudad;
import com.edu.proyect.Facturacion.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CiudadServiceImpl extends GenericServiceImpl<Ciudad, Integer> implements CiudadService {

    @Autowired
    CiudadRepository ciudadRepository;

    @Override
    public CrudRepository<Ciudad, Integer> getDao() {
        return ciudadRepository;
    }

    @Override
    public Page<Ciudad> findAllCiudadesPorAlmacenes(Pageable pageable) {
        return ciudadRepository.findAllCiudadesPorAlmacenes(pageable);
    }
}
