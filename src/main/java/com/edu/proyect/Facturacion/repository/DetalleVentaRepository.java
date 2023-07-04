package com.edu.proyect.Facturacion.repository;

import com.edu.proyect.Facturacion.models.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
}
