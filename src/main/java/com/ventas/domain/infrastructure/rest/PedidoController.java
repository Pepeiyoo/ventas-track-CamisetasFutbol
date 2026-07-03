package com.ventas.domain.infrastructure.rest;

import com.ventas.domain.Pedido;
import com.ventas.domain.PedidoRepository; // O tu PedidoService / Caso de uso si lo prefieres
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    // Spring inyectará automáticamente nuestro JpaPedidoRepository real gracias al @Primary
    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // 1. Endpoint para CREAR un Pedido
    // Recibe un JSON en el cuerpo de la petición con la estructura del Pedido
    @PostMapping
    public ResponseEntity<String> crearPedido(@RequestBody Pedido pedido) {
        if (pedido == null || pedido.getId() == null) {
            return ResponseEntity.badRequest().body("El pedido o su ID no pueden ser nulos.");
        }
        
        pedidoRepository.guardar(pedido);
        return ResponseEntity.ok("Pedido creado correctamente con ID: " + pedido.getId());
    }

    // 2. Endpoint para CONSULTAR un Pedido por ID
    // Se invoca mediante una petición GET tipo: /api/pedidos/12345
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable String id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.buscarPorId(id);
        
        // Si el pedido existe, devolvemos un 200 OK con el objeto. Si no, un 404 Not Found.
        return pedidoOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}