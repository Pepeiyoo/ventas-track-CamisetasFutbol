package com.ventas.infrastructure.rest;

import com.ventas.domain.Pedido;
import com.ventas.domain.ItemPedido;
import com.ventas.domain.PedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pedidos")
public class PedidoViewController {

    private final PedidoRepository pedidoRepository;

    public PedidoViewController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping
    public String verPaginaPedidos(Model model) {
        List<PedidoController.PedidoRespuestaDto> listaDtos = pedidoRepository.findAll().stream()
                .map(PedidoController.PedidoRespuestaDto::new)
                .collect(Collectors.toList());
        model.addAttribute("pedidos", listaDtos);
        return "pedidos";
    }

    @PostMapping("/nuevo")
    public String registrarCamiseta(@RequestParam String id, @RequestParam String nombreAdministrador,
                                    @RequestParam String nombrePersona, @RequestParam String modeloCamiseta,
                                    @RequestParam String talla, @RequestParam String tipoCamiseta,
                                    @RequestParam(required = false) String urlFoto,
                                    @RequestParam(value = "tieneNombreNumero", required = false) Boolean tieneNombreNumero,
                                    @RequestParam(value = "tieneParches", required = false) Boolean tieneParches) {
        
        Pedido pedido = pedidoRepository.buscarPorId(id).orElseGet(() -> {
            Pedido nuevo = new Pedido();
            nuevo.setId(id);
            nuevo.setNombreAdministrador(nombreAdministrador);
            nuevo.setEstado("ABIERTO");
            return nuevo;
        });

        ItemPedido nuevoItem = new ItemPedido(nombrePersona, modeloCamiseta, talla, tipoCamiseta, 
                                            (tieneNombreNumero != null && tieneNombreNumero), 
                                            (tieneParches != null && tieneParches), urlFoto);
        pedido.agregarItem(nuevoItem);
        pedidoRepository.guardar(pedido);
        return "redirect:/pedidos";
    }

    // Ruta fija: /{pedidoId:.+}/items/{itemId}/alternar-pago
    @PostMapping("/{pedidoId:.+}/items/{itemId}/alternar-pago")
    public String alternarPagoItem(@PathVariable String pedidoId, @PathVariable String itemId) {
        Pedido pedido = pedidoRepository.buscarPorId(pedidoId).orElseThrow();
        pedido.getItems().stream().filter(i -> i.getId().equals(itemId)).findFirst().ifPresent(ItemPedido::alternarPago);
        pedidoRepository.guardar(pedido);
        return "redirect:/pedidos";
    }

    // Ruta fija: /{pedidoId:.+}/items/{itemId}/editar
    @PostMapping("/{pedidoId:.+}/items/{itemId}/editar")
    public String editarItem(@PathVariable String pedidoId, @PathVariable String itemId,
                             @RequestParam String nombrePersona, @RequestParam String modeloCamiseta,
                             @RequestParam String talla, @RequestParam String tipoCamiseta,
                             @RequestParam(required = false) String urlFoto,
                             @RequestParam(value = "tieneNombreNumero", required = false) Boolean tieneNombreNumero,
                             @RequestParam(value = "tieneParches", required = false) Boolean tieneParches) {
                             
        Pedido pedido = pedidoRepository.buscarPorId(pedidoId).orElseThrow();
        pedido.getItems().stream().filter(i -> i.getId().equals(itemId)).findFirst().ifPresent(item -> {
            item.setNombrePersona(nombrePersona);
            item.setModeloCamiseta(modeloCamiseta);
            item.setTalla(talla);
            item.setTipoCamiseta(tipoCamiseta);
            item.setUrlFoto(urlFoto);
            item.setTieneNombreNumero(tieneNombreNumero != null && tieneNombreNumero);
            item.setTieneParches(tieneParches != null && tieneParches);
        });
        pedidoRepository.guardar(pedido);
        return "redirect:/pedidos";
    }
}