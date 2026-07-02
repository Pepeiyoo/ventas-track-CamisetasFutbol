package com.ventas.domain;

import java.util.Optional;

public interface PedidoRepository {
    void guardar(Pedido pedido);
    Optional<Pedido> buscarPorId(String id);
}