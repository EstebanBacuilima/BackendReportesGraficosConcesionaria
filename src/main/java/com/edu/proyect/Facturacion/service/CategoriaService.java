package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.models.Categoria;
import com.edu.proyect.Facturacion.models.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService extends GenericService<Categoria, Integer>{

    public Page<Categoria> findByAll(Pageable pageable);

}
