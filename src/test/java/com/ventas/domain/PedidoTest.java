package com.ventas.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PedidoTest {

    @Test
    public void testAcumulacionFinancieraPedidoGrupal() {
        Pedido pedido = new Pedido("GRUPO-JULIO", "ABIERTO", "Fornell");
        
        // Añadimos una Retro (Coste 11, Venta 25)
        pedido.agregarItem(new ItemPedido("Persona1", "ManU Retro", "XL", "RETRO", false, false, ""));
        // Añadimos una Fan sin extras (Coste 8, Venta 20)
        pedido.agregarItem(new ItemPedido("Persona2", "Barca Fan", "S", "FAN", false, false, ""));
        
        // Totales: Coste = 11 + 8 = 19€ | Venta = 25 + 20 = 45€ | Beneficio = 45 - 19 = 26€
        assertEquals(19.0, pedido.getCosteTotalFabricacion(), 0.01);
        assertEquals(45.0, pedido.getPrecioVentaCliente(), 0.01);
        assertEquals(26.0, pedido.getBeneficioNeto(), 0.01);
    }

    @Test
    public void testCambioEstadoPedido() {
        Pedido pedido = new Pedido("ID-01", "ABIERTO", "Admin");
        assertEquals("ABIERTO", pedido.getEstado());
        
        pedido.setEstado("FINALIZADO");
        assertEquals("FINALIZADO", pedido.getEstado());
    }
}