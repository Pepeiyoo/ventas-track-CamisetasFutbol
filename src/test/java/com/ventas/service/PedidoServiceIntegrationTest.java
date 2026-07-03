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
        pedidoOriginal.agregarItem(new ItemPedido("Nene", "Barca", "8 años", "NINO", false, false, ""));

        pedidoRepository.guardar(pedidoOriginal);

        java.util.Optional<Pedido> recuperadoOpt = pedidoRepository.buscarPorId("GRUPO-TEST");
        assertTrue(recuperadoOpt.isPresent());
        
        Pedido recuperado = recuperadoOpt.get();
        assertEquals(2, recuperado.getItems().size());
     // Comprobación de finanzas conjuntas actualizadas:
     // Player con todo = 14€ coste, 22€ venta
     // Niño base actualizado = 13€ coste, 25€ venta fija
     assertEquals(27.0, recuperado.getCosteTotalFabricacion(), 0.01); // 14 + 13 = 27€
     assertEquals(47.0, recuperado.getPrecioVentaCliente(), 0.01);    // 22 + 25 = 47€
     assertEquals(20.0, recuperado.getBeneficioNeto(), 0.01);          // 47 - 27 = 20€
    }
}