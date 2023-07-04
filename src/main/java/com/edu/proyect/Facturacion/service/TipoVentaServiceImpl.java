package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.models.TipoVenta;
import com.edu.proyect.Facturacion.repository.TipoVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoVentaServiceImpl extends GenericServiceImpl<TipoVenta, Integer> implements TipoVentaService {

    @Autowired
    TipoVentaRepository tipoVentaRepository;

    @Override
    public CrudRepository<TipoVenta, Integer> getDao() {
        return tipoVentaRepository;
    }

    @Override
    public Page<TipoVenta> findByAll(Pageable pageable) {
        return tipoVentaRepository.findAll(pageable);
    }
}
