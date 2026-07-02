package com.ventas.domain;

import java.util.Optional;

public interface PedidoRepository {
    // Guarda o actualiza un pedido de camisetas
    Pedido save(Pedido pedido);
    
    // Busca un pedido por su identificador único
    Optional<Pedido> findById(String id);
}
