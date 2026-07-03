package com.ventas.infrastructure.rest;

import com.ventas.domain.Pedido;
import com.ventas.infrastructure.persistence.JpaPedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class PedidoViewController {

    private final JpaPedidoRepository pedidoRepository;

    public PedidoViewController(JpaPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // 1. Muestra la página principal con el formulario y la tabla de pedidos
    @GetMapping
    public String verPaginaPedidos(Model model) {
        // Pasamos la lista de pedidos calculados a la vista mediante DTOs
        java.util.List<PedidoController.PedidoRespuestaDto> listaDtos = pedidoRepository.listarTodos().stream()
                .map(PedidoController.PedidoRespuestaDto::new)
                .collect(java.util.stream.Collectors.toList());
        
        model.addAttribute("pedidos", listaDtos);
        return "pedidos"; // Esto buscará el archivo pedidos.html en templates
    }

    // 2. Procesa el formulario web para crear un pedido sin escribir JSON
    @PostMapping("/nuevo")
    public String registrarPedidoDesdeForm(@RequestParam String id,
                                           @RequestParam String modeloCamiseta,
                                           @RequestParam String nombreAdministrador,
                                           @RequestParam String tipoCamiseta,
                                           @RequestParam(defaultValue = "false") boolean tieneNombreNumero,
                                           @RequestParam(defaultValue = "false") boolean tieneParches) {
        
        Pedido nuevoPedido = new Pedido(id, "ABIERTO", nombreAdministrador, modeloCamiseta, tipoCamiseta, tieneNombreNumero, tieneParches);
        pedidoRepository.guardar(nuevoPedido);
        
        return "redirect:/pedidos"; // Recarga la página para mostrar el nuevo pedido en la tabla
    }

    // 3. Botón rápido para finalizar o cambiar el estado desde la tabla
    @PostMapping("/{id}/finalizar")
    public String finalizarPedido(@PathVariable String id) {
        java.util.Optional<Pedido> pedidoOpt = pedidoRepository.buscarPorId(id);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setEstado("FINALIZADO");
            pedidoRepository.guardar(pedido);
        }
        return "redirect:/pedidos";
    }
}