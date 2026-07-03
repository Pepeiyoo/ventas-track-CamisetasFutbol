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

    @GetMapping
    public List<PedidoRespuestaDto> listarTodos() {
        return pedidoRepository.listarTodos().stream()
                .map(PedidoRespuestaDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoRespuestaDto> buscarPorId(@PathVariable String id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.buscarPorId(id);
        return pedidoOpt.map(pedido -> ResponseEntity.ok(new PedidoRespuestaDto(pedido)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<String> actualizarEstado(@PathVariable String id, @RequestParam String nuevoEstado) {
        Optional<Pedido> pedidoOpt = pedidoRepository.buscarPorId(id);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Pedido pedido = pedidoOpt.get();
        pedido.setEstado(nuevoEstado);
        pedidoRepository.guardar(pedido);
        return ResponseEntity.ok("El estado del pedido " + id + " ha sido actualizado a: " + nuevoEstado);
    }

    // --- Clase interna DTO adaptada al nuevo modelo grupal financiero ---
    public static class PedidoRespuestaDto {
        public String id;
        public String estado;
        public String nombreAdministrador;
        public List<ItemDto> items;
        public double costeTotalFabricacion;
        public double precioVentaCliente;
        public double beneficioNeto;

        public PedidoRespuestaDto(Pedido p) {
            this.id = p.getId();
            this.estado = p.getEstado();
            this.nombreAdministrador = p.getNombreAdministrador();
            this.costeTotalFabricacion = p.getCosteTotalFabricacion();
            this.precioVentaCliente = p.getPrecioVentaCliente();
            this.beneficioNeto = p.getBeneficioNeto();
            this.items = p.getItems().stream().map(ItemDto::new).collect(Collectors.toList());
        }
    }

    public static class ItemDto {
        public String nombrePersona;
        public String modeloCamiseta;
        public String talla;
        public String tipoCamiseta;
        public boolean tieneNombreNumero;
        public boolean tieneParches;
        public String urlFoto;
        public double costeIndividual;
        public double precioVentaIndividual;
        public double beneficioIndividual;

        public ItemDto(com.ventas.domain.ItemPedido item) {
            this.nombrePersona = item.getNombrePersona();
            this.modeloCamiseta = item.getModeloCamiseta();
            this.talla = item.getTalla();
            this.tipoCamiseta = item.getTipoCamiseta();
            this.tieneNombreNumero = item.isTieneNombreNumero();
            this.tieneParches = item.isTieneParches();
            this.urlFoto = item.getUrlFoto();
            this.costeIndividual = item.calcularCoste();
            this.precioVentaIndividual = item.calcularPrecioVenta();
            this.beneficioIndividual = item.calcularBeneficio();
        }
    }
}