package com.edu.proyect.Facturacion.repository;

import com.edu.proyect.Facturacion.models.Categoria;
import com.edu.proyect.Facturacion.models.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {






}
