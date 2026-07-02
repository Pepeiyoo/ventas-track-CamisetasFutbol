package com.ventas.service;

import com.ventas.domain.Pedido;
import com.ventas.domain.PedidoRepository;
import java.util.Optional;

public class PedidoService {

    private final PedidoRepository pedidoRepository;

    // Constructor que recibe el repositorio (esencial para los Mocks y el Fake)
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void guardarPedido(Pedido pedido) {
        pedidoRepository.guardar(pedido);
    }

    public Optional<Pedido> obtenerPedido(String id) {
        return pedidoRepository.buscarPorId(id);
    }
}