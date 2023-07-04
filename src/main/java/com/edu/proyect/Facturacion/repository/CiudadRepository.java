package com.edu.proyect.Facturacion.repository;

import com.edu.proyect.Facturacion.Payloads.CiudadesDto;
import com.edu.proyect.Facturacion.models.Ciudad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {

    @Query(value = "SELECT c.* FROM ciudades c JOIN almacenes a ON c.id_ciudad = a.id_ciudad", nativeQuery = true)
    Page<Ciudad> findAllCiudadesPorAlmacenes(Pageable pageable);

}
