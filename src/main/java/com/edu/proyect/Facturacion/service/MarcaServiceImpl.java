package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.models.Marca;
import com.edu.proyect.Facturacion.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl extends GenericServiceImpl<Marca, Integer> implements MarcaService {

    @Autowired
    MarcaRepository marcaRepository;

    @Override
    public CrudRepository<Marca, Integer> getDao() {
        return marcaRepository;
    }

}
