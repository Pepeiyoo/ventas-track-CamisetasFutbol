package com.ventas.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CamisetaNegocioTest {

    @Test
    public void testCalculoCamisetaRetroTarifaPlana() {
        // Las Retro tienen coste base 11€ y precio venta fijo de 25€ (los extras no alteran el PVP)
        ItemPedido itemRetro = new ItemPedido("Carlos", "Milan Retro 90", "L", "RETRO", true, true, "http://foto.com");
        
        assertEquals(11.0, itemRetro.calcularCoste(), 0.01);
        assertEquals(25.0, itemRetro.calcularPrecioVenta(), 0.01);
        assertEquals(14.0, itemRetro.calcularBeneficio(), 0.01);
    }

    @Test
    public void testCalculoKitNinoTarifaPlana() {
        // Los niños tienen coste base de 13€ y precio venta fijo de 25€
        ItemPedido itemNino = new ItemPedido("Lucas", "Real Madrid Niño", "8 años", "NINO", false, false, "");
        
        assertEquals(13.0, itemNino.calcularCoste(), 0.01);
        assertEquals(25.0, itemNino.calcularPrecioVenta(), 0.01);
        assertEquals(12.0, itemNino.calcularBeneficio(), 0.01);
    }

    @Test
    public void testCalculoCamisetaFanConExtras() {
        // Fan base 8€ + 2€ nombre + 1€ parches = 11€ coste. Venta con nombre = 22€
        ItemPedido itemFan = new ItemPedido("Juan", "Betis", "M", "FAN", true, true, null);
        
        assertEquals(11.0, itemFan.calcularCoste(), 0.01);
        assertEquals(22.0, itemFan.calcularPrecioVenta(), 0.01);
        assertEquals(11.0, itemFan.calcularBeneficio(), 0.01);
    }
}