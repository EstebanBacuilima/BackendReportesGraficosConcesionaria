package com.edu.proyect.Facturacion.repository;

import com.edu.proyect.Facturacion.models.TipoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TipoVentaRepository extends JpaRepository<TipoVenta, Integer> {
}
