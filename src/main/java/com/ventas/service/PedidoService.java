package com.ventas.service;

import com.ventas.domain.Pedido;
import com.ventas.domain.PedidoRepository;
import java.util.Optional;

public class PedidoService {

    private final PedidoRepository pedidoRepository;

    // Inyección por constructor (Obligatorio en los apuntes, nada de @Autowired)
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void procesarYAsignar(String idPedido, String nombreAdmin) {
        // 1. Buscamos el pedido en el repositorio simulado
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);
        
        if (pedidoOpt.isEmpty()) {
            throw new IllegalArgumentException("El pedido con ID " + idPedido + " no existe.");
        }

        Pedido pedido = pedidoOpt.get();

        // 2. Aplicamos la regla de negocio que programamos en la Entrega 2
        // Como tú eres un administrador activo, le pasamos 'true' a la validación
        pedido.asignarAdministrador(nombreAdmin, true);

        // 3. Guardamos los cambios en el repositorio
        pedidoRepository.save(pedido);
    }
}
