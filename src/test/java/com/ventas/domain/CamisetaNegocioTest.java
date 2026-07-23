package com.ventas.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CamisetaNegocioTest {

    @Test
    public void testCalculoCamisetaRetroTarifaPlana() {
        // Las Retro tienen precio fijo de 25€ y los extras no alteran el PVP
        ItemPedido itemRetro = new ItemPedido("Carlos", "Milan Retro 90", "L", "RETRO", true, true, "http://foto.com");
        
        assertEquals(14.0, itemRetro.calcularCoste(), 0.01);
        assertEquals(25.0, itemRetro.calcularPrecioVenta(), 0.01);
        assertEquals(11.0, itemRetro.calcularBeneficio(), 0.01);
    }

    @Test
    public void testCalculoPlayerTarifaPlana() {
        // Player tiene precio fijo de 22€ y el nombre/número es opcional pero no afecta al PVP
        ItemPedido itemPlayer = new ItemPedido("Lucas", "Real Madrid Player", "M", "PLAYER", true, false, "");
        
        assertEquals(13.0, itemPlayer.calcularCoste(), 0.01);
        assertEquals(22.0, itemPlayer.calcularPrecioVenta(), 0.01);
        assertEquals(9.0, itemPlayer.calcularBeneficio(), 0.01);
    }

    @Test
    public void testCalculoCamisetaFanConExtras() {
        // Fan base 8€ + 2€ nombre + 1€ parches = 11€ coste. Venta fija de 22€
        ItemPedido itemFan = new ItemPedido("Juan", "Betis", "M", "FAN", true, true, null);
        
        assertEquals(11.0, itemFan.calcularCoste(), 0.01);
        assertEquals(22.0, itemFan.calcularPrecioVenta(), 0.01);
        assertEquals(11.0, itemFan.calcularBeneficio(), 0.01);
    }
}