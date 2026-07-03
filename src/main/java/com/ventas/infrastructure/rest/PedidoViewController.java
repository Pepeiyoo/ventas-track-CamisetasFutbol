package com.ventas.infrastructure.rest;

import com.ventas.domain.Pedido;
import com.ventas.domain.ItemPedido;
import com.ventas.infrastructure.persistence.JpaPedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/pedidos")
public class PedidoViewController {

    private final JpaPedidoRepository pedidoRepository;

    public PedidoViewController(JpaPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping
    public String verPaginaPedidos(Model model) {
        // Convertimos los pedidos a DTOs legibles para evitar que den valores nulos en Thymeleaf
        java.util.List<PedidoController.PedidoRespuestaDto> listaDtos = pedidoRepository.listarTodos().stream()
                .map(PedidoController.PedidoRespuestaDto::new)
                .collect(java.util.stream.Collectors.toList());
        
        model.addAttribute("pedidos", listaDtos);
        return "pedidos";
    }

    @PostMapping("/nuevo")
    public String registrarPedidoDesdeForm(@RequestParam String id,
                                           @RequestParam String nombreAdministrador,
                                           @RequestParam String nombrePersona,
                                           @RequestParam String modeloCamiseta,
                                           @RequestParam String talla,
                                           @RequestParam String tipoCamiseta,
                                           @RequestParam(defaultValue = "false") boolean tieneNombreNumero,
                                           @RequestParam(defaultValue = "false") boolean tieneParches,
                                           @RequestParam String urlFoto) {
        
        // Buscamos si ya existe el pedido grupal abierto
        Optional<Pedido> pedidoExistente = pedidoRepository.buscarPorId(id);
        Pedido pedido;
        
        if (pedidoExistente.isPresent()) {
            pedido = pedidoExistente.get();
        } else {
            pedido = new Pedido(id, "ABIERTO", nombreAdministrador);
        }

        // Le añadimos la nueva persona con su camiseta al grupo
        ItemPedido nuevoItem = new ItemPedido(nombrePersona, modeloCamiseta, talla, tipoCamiseta, tieneNombreNumero, tieneParches, urlFoto);
        pedido.agregarItem(nuevoItem);
        
        pedidoRepository.guardar(pedido);
        return "redirect:/pedidos";
    }

    @PostMapping("/{id}/finalizar")
    public String finalizarPedido(@PathVariable String id) {
        pedidoRepository.buscarPorId(id).ifPresent(p -> {
            p.setEstado("FINALIZADO");
            pedidoRepository.guardar(p);
        });
        return "redirect:/pedidos";
    }
}