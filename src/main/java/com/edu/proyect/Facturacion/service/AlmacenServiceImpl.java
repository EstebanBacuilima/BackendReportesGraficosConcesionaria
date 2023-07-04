package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.models.Almacen;
import com.edu.proyect.Facturacion.repository.AlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AlmacenServiceImpl extends GenericServiceImpl<Almacen, Integer> implements AlmacenService {

    @Autowired
    AlmacenRepository almacenRepository;

    @Override
    public CrudRepository<Almacen, Integer> getDao() {
        return almacenRepository;
    }

    @Override
    public Page<Almacen> findByEmpresaNombreEmpresa(Pageable pageable, String nombre) {
        return almacenRepository.findByEmpresaNombreEmpresa(pageable,nombre);
    }

    @Override
    public Page<Almacen> findByCiudadNombreCiudad(Pageable pageable, String nombre) {
        return almacenRepository.findByCiudadNombreCiudad(pageable,nombre);
    }
}
