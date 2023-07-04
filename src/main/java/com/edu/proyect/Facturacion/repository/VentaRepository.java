package com.edu.proyect.Facturacion.repository;

import com.edu.proyect.Facturacion.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
