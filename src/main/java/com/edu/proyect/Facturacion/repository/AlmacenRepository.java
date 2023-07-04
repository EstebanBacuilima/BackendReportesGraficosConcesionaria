package com.edu.proyect.Facturacion.repository;

import com.edu.proyect.Facturacion.models.Almacen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer>{

    public Page<Almacen> findByEmpresaNombreEmpresa(Pageable pageable,String nombre);

    public Page<Almacen> findByCiudadNombreCiudad(Pageable pageable,String nombre);


}
