package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.models.TipoVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoVentaService extends GenericService<TipoVenta, Integer>{

    public Page<TipoVenta> findByAll(Pageable pageable);

}
