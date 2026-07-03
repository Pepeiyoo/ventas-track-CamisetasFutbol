package com.ventas.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPedidoRepository extends JpaRepository<PedidoEntity, String> {
    // ¡Listo! Al heredar de JpaRepository, ya tenemos gratis: save(), findById(), delete(), etc.
}