package com.ventas.infrastructure.rest;



import com.ventas.domain.Pedido;
import com.ventas.infrastructure.persistence.JpaPedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final JpaPedidoRepository pedidoRepository;

    public PedidoController(JpaPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // 1. POST: Crear / Registrar Pedido
    @PostMapping
    public ResponseEntity<String> crearPedido(@RequestBody Pedido pedido) {
        pedidoRepository.guardar(pedido);
        return ResponseEntity.ok("Pedido registrado con éxito. ID: " + pedido.getId());
    }

    // 2. GET: Listar TODOS los pedidos con sus cálculos económicos
    @GetMapping
    public ResponseEntity<List<PedidoRespuestaDto>> listarTodos() {
        List<PedidoRespuestaDto> respuesta = pedidoRepository.listarTodos().stream()
                .map(PedidoRespuestaDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respuesta);
    }

    // 3. GET: Ver un pedido por ID con sus cálculos
    @GetMapping("/{id}")
    public ResponseEntity<PedidoRespuestaDto> obtenerPorId(@PathVariable String id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.buscarPorId(id);
        return pedidoOpt
                .map(p -> ResponseEntity.ok(new PedidoRespuestaDto(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 4. PUT: Gestionar / Actualizar Estado de un Pedido existente
    @PutMapping("/{id}/estado")
    public ResponseEntity<String> actualizarEstado(@PathVariable String id, @RequestParam String nuevoEstado) {
        Optional<Pedido> pedidoOpt = pedidoRepository.buscarPorId(id);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Pedido pedido = pedidoOpt.get();
        pedido.setEstado(nuevoEstado); // Modificamos el estado
        pedidoRepository.guardar(pedido); // Guardamos la actualización en BD
        
        return ResponseEntity.ok("El estado del pedido " + id + " ha sido actualizado a: " + nuevoEstado);
    }

    // --- Clase interna DTO para mostrar los cálculos económicos en Swagger ---
    public static class PedidoRespuestaDto {
        public String id;
        public String estado;
        public String nombreAdministrador;
        public String modeloCamiseta;
        public String tipoCamiseta;
        public boolean tieneNombreNumero;
        public boolean tieneParches;
        
        // Campos económicos auto-calculados
        public double costeTotalFabricacion;
        public double precioVentaCliente;
        public double beneficioNeto;

        public PedidoRespuestaDto(Pedido p) {
            this.id = p.getId();
            this.estado = p.getEstado();
            this.nombreAdministrador = p.getNombreAdministrador();
            this.modeloCamiseta = p.getModeloCamiseta();
            this.tipoCamiseta = p.getTipoCamiseta();
            this.tieneNombreNumero = p.isTieneNombreNumero();
            this.tieneParches = p.isTieneParches();
            this.costeTotalFabricacion = p.calcularCoste();
            this.precioVentaCliente = p.calcularPrecioVenta();
            this.beneficioNeto = p.calcularBeneficio();
        }
    }
}