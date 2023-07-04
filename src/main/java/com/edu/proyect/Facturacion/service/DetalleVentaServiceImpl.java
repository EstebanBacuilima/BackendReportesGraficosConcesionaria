package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.models.DetalleVenta;
import com.edu.proyect.Facturacion.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class DetalleVentaServiceImpl extends GenericServiceImpl<DetalleVenta, Integer> implements DetalleVentaService {

    @Autowired
    DetalleVentaRepository detalleVentaRepository;

    @Override
    public CrudRepository<DetalleVenta, Integer> getDao() {
        return detalleVentaRepository;
    }

}
