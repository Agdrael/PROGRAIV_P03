package com.ejemplo.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ejemplo.tienda.model.Producto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Spring Data JPA ya nos da métodos como save, findAll, findById, deleteById
}
