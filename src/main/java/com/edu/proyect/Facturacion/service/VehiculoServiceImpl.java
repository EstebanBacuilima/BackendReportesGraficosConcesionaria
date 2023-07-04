package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.models.Categoria;
import com.edu.proyect.Facturacion.models.Vehiculo;
import com.edu.proyect.Facturacion.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class VehiculoServiceImpl extends GenericServiceImpl<Vehiculo, Integer> implements VehiculoService {

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Override
    public CrudRepository<Vehiculo, Integer> getDao() {
        return vehiculoRepository;
    }

    @Override
    public Page<Vehiculo> findByAll(Pageable pageable) {
        return vehiculoRepository.findAll(pageable);
    }

}
