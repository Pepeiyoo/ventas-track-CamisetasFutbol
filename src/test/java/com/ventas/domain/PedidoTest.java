package com.ventas.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PedidoTest {

    // === TESTS REGLA 1 ===
    @Test
    public void testNoSePuedeCerrarPedidoSiNoEstaEnProceso() {
        Pedido pedido = new Pedido("PED-100", EstadoPedido.ABIERTO);
        assertThrows(IllegalStateException.class, () -> {
            pedido.cerrar();
        });
        assertNotEquals(EstadoPedido.CERRADO, pedido.getEstado());
    }

    @Test
    public void testPermiteCerrarPedidoSiEstaEnProceso() {
        Pedido pedido = new Pedido("PED-101", EstadoPedido.EN_PROCESO);
        pedido.cerrar();
        assertEquals(EstadoPedido.CERRADO, pedido.getEstado());
    }

    // === TESTS REGLA 2 ===
    @Test
    public void testNoSePuedeAsignarAdministradorInactivo() {
        Pedido pedido = new Pedido("PED-102", EstadoPedido.ABIERTO);
        assertThrows(IllegalArgumentException.class, () -> {
            pedido.asignarAdministrador("Pepe", false);
        });
        assertNull(pedido.getNombreAdministrador());
    }

    @Test
    public void testPermiteAsignarAdministradorActivo() {
        Pedido pedido = new Pedido("PED-103", EstadoPedido.ABIERTO);
        pedido.asignarAdministrador("Pepe", true);
        assertEquals("Pepe", pedido.getNombreAdministrador());
    }
}