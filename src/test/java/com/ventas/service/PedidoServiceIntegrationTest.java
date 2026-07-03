package com.ventas.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ventas.domain.Pedido;
import com.ventas.domain.PedidoRepository;

@SpringBootTest
public class PedidoServiceIntegrationTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    public void testGuardarYRecuperarPedidoEnBaseDeDatos() {
        // 1. Creamos un pedido con el constructor completo usando los nuevos campos de gestión
        Pedido pedidoOriginal = new Pedido(
            "PED-INT-999", 
            "ABIERTO", 
            "AdminTest", 
            "Real Madrid 2026", 
            "PLAYER", 
            true, 
            true
        );

        // 2. Guardamos en el repositorio real (H2 por debajo)
        pedidoRepository.guardar(pedidoOriginal);

        // 3. Lo recuperamos de la base de datos
        java.util.Optional<Pedido> pedidoRecuperadoOpt = pedidoRepository.buscarPorId("PED-INT-999");

        // 4. Verificaciones (Asserts)
        assertTrue(pedidoRecuperadoOpt.isPresent(), "El pedido debería existir en la base de datos");
        
        Pedido pedidoRecuperado = pedidoRecuperadoOpt.get();
        assertEquals("PED-INT-999", pedidoRecuperado.getId());
        assertEquals("ABIERTO", pedidoRecuperado.getEstado());
        assertEquals("AdminTest", pedidoRecuperado.getNombreAdministrador());
        assertEquals("Real Madrid 2026", pedidoRecuperado.getModeloCamiseta());
        assertEquals("PLAYER", pedidoRecuperado.getTipoCamiseta());
        assertTrue(pedidoRecuperado.isTieneNombreNumero());
        assertTrue(pedidoRecuperado.isTieneParches());
        
        // Verificamos de paso que la lógica económica se mantiene consistente al persistir
        assertEquals(14.0, pedidoRecuperado.calcularCoste()); // 11 base + 2 nombre + 1 parche
        assertEquals(22.0, pedidoRecuperado.calcularPrecioVenta());
        assertEquals(8.0, pedidoRecuperado.calcularBeneficio());
    }
}