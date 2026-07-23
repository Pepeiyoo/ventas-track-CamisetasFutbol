package com.ventas.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ventas.domain.Pedido;
import com.ventas.domain.ItemPedido;
import com.ventas.domain.PedidoRepository;

@SpringBootTest
public class PedidoServiceIntegrationTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    public void testGuardarYRecuperarPedidoGrupal() {
        Pedido pedidoOriginal = new Pedido("GRUPO-TEST", "ABIERTO", "Fornell");
        pedidoOriginal.agregarItem(new ItemPedido("Alex", "Milan", "M", "PLAYER", true, true, ""));
        pedidoOriginal.agregarItem(new ItemPedido("Nene", "Barca Retro", "8 años", "RETRO", false, false, ""));

        pedidoRepository.guardar(pedidoOriginal);

        java.util.Optional<Pedido> recuperadoOpt = pedidoRepository.buscarPorId("GRUPO-TEST");
        assertTrue(recuperadoOpt.isPresent());
        
        Pedido recuperado = recuperadoOpt.get();
        assertEquals(2, recuperado.getItems().size());
     // Comprobación de finanzas conjuntas actualizadas:
     // Player con todo = 14€ coste, 22€ venta
     // Retro base = 11€ coste, 25€ venta fija
     assertEquals(25.0, recuperado.getCosteTotalFabricacion(), 0.01); // 14 + 11 = 25€
     assertEquals(47.0, recuperado.getPrecioVentaCliente(), 0.01);    // 22 + 25 = 47€
     assertEquals(22.0, recuperado.getBeneficioNeto(), 0.01);          // 47 - 25 = 22€
    }

    @Test
    public void testEliminarPedidoPorId() {
        Pedido pedido = new Pedido("GRUPO-BORRAR", "CERRADO", "Fornell");
        pedido.agregarItem(new ItemPedido("Alex", "Milan", "M", "PLAYER", false, false, ""));
        pedidoRepository.guardar(pedido);

        assertTrue(pedidoRepository.buscarPorId("GRUPO-BORRAR").isPresent());

        pedidoRepository.eliminarPorId("GRUPO-BORRAR");

        assertFalse(pedidoRepository.buscarPorId("GRUPO-BORRAR").isPresent());
    }
}