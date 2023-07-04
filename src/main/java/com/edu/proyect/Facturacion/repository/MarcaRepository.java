package com.edu.proyect.Facturacion.repository;


import com.edu.proyect.Facturacion.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
}
