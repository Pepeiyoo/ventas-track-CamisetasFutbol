package com.ventas.domain;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    void guardar(Pedido pedido);
    Optional<Pedido> buscarPorId(String id);
    List<Pedido> findAll(); // 👈 Añadimos esto para que el controlador lo pueda usar
}