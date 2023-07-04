package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.models.Venta;
import com.edu.proyect.Facturacion.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class VentaServiceImpl extends GenericServiceImpl<Venta, Integer> implements VentaService {

    @Autowired
    VentaRepository ventaRepository;

    @Override
    public CrudRepository<Venta, Integer> getDao() {
        return ventaRepository;
    }

}
