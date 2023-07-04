package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.models.Categoria;
import com.edu.proyect.Facturacion.models.Empresa;
import com.edu.proyect.Facturacion.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl extends GenericServiceImpl<Categoria, Integer> implements CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public CrudRepository<Categoria, Integer> getDao() {
        return categoriaRepository;
    }

    @Override
    public Page<Categoria> findByAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }


}
