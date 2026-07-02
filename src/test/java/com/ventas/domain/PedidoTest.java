package com.ventas.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PedidoTest {

    @Test
    public void testNoSePuedeCerrarPedidoSiNoEstaEnProceso() {
        // 1. Creamos un pedido de camisetas que nace ABIERTO
        Pedido pedido = new Pedido("PED-100", EstadoPedido.ABIERTO);
        
        // 2. Intentamos cerrarlo directamente (debería lanzar excepción porque no está EN_PROCESO)
        assertThrows(IllegalStateException.class, () -> {
            pedido.cerrar();
        });
        
        // 3. Verificamos que el estado NO haya cambiado a CERRADO por seguridad
        assertNotEquals(EstadoPedido.CERRADO, pedido.getEstado());
    }
    @Test
    public void testPermiteCerrarPedidoSiEstaEnProceso() {
        // 1. Creamos un pedido simulando que ya se está fabricando (EN_PROCESO)
        Pedido pedido = new Pedido("PED-101", EstadoPedido.EN_PROCESO);
        
        // 2. Intentamos cerrarlo
        pedido.cerrar();
        
        // 3. Verificamos que ahora sí ha cambiado su estado a CERRADO
        assertEquals(EstadoPedido.CERRADO, pedido.getEstado());
    }
}