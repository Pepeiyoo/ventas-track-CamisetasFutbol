package com.ventas.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CamisetaNegocioTest {

    @Test
    public void testCalculoCamisetaFanSinExtras() {
        // Camiseta FAN (8€ coste base), sin parches, sin nombre/numero
        Pedido pedido = new Pedido("T1", "ABIERTO", "Admin", "Real Madrid", "FAN", false, false);
        
        assertEquals(8.0, pedido.calcularCoste(), 0.01);
        assertEquals(20.0, pedido.calcularPrecioVenta(), 0.01);
        assertEquals(12.0, pedido.calcularBeneficio(), 0.01);
    }

    @Test
    public void testCalculoCamisetaPlayerConTodosLosExtras() {
        // Camiseta PLAYER (11€ coste base) + Parches (1€) + Nombre/Numero (2€) = 14€ coste
        // Precio venta con nombre/numero = 22€
        Pedido pedido = new Pedido("T2", "ABIERTO", "Admin", "Barca", "PLAYER", true, true);
        
        assertEquals(14.0, pedido.calcularCoste(), 0.01);
        assertEquals(22.0, pedido.calcularPrecioVenta(), 0.01);
        assertEquals(8.0, pedido.calcularBeneficio(), 0.01);
    }

    @Test
    public void testCalculoCamisetaFanSoloConParches() {
        // FAN (8€) + Parches (1€) = 9€ coste
        // Sin nombre/numero -> Precio venta = 20€
        Pedido pedido = new Pedido("T3", "ABIERTO", "Admin", "Betis", "FAN", false, true);
        
        assertEquals(9.0, pedido.calcularCoste(), 0.01);
        assertEquals(20.0, pedido.calcularPrecioVenta(), 0.01);
        assertEquals(11.0, pedido.calcularBeneficio(), 0.01);
    }

    @Test
    public void testCalculoCamisetaPlayerSoloConNombreNumero() {
        // PLAYER (11€) + Nombre/Numero (2€) = 13€ coste
        // Con nombre/numero -> Precio venta = 22€
        Pedido pedido = new Pedido("T4", "ABIERTO", "Admin", "Atleti", "PLAYER", true, false);
        
        assertEquals(13.0, pedido.calcularCoste(), 0.01);
        assertEquals(22.0, pedido.calcularPrecioVenta(), 0.01);
        assertEquals(9.0, pedido.calcularBeneficio(), 0.01);
    }
}