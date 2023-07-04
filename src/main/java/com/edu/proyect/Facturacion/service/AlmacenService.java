package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.models.Almacen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlmacenService extends GenericService<Almacen, Integer>{
    public Page<Almacen> findByEmpresaNombreEmpresa(Pageable pageable, String nombre);
    public Page<Almacen> findByCiudadNombreCiudad(Pageable pageable,String nombre);

}
