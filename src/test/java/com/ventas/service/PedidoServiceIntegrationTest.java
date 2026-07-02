package com.ventas.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ventas.domain.Pedido;
import com.ventas.domain.EstadoPedido;
import com.ventas.infrastructure.persistence.FakePedidoRepository;
import java.util.Optional;

public class PedidoServiceIntegrationTest {

    private PedidoService pedidoService;
    private FakePedidoRepository fakeRepository;

    @BeforeEach
    public void setUp() {
        // Instanciamos los componentes reales simulados (Sin Mocks)
        this.fakeRepository = new FakePedidoRepository();
        this.pedidoService = new PedidoService(fakeRepository);
    }

    @Test
    public void testGuardarYRecuperarPedidoEnMemoria() {
        // 1. Creamos un pedido real de dominio
        Pedido pedidoOriginal = new Pedido("PED-999", EstadoPedido.ABIERTO);
        pedidoOriginal.asignarAdministrador("AdminPepe", true);

        // 2. Lo guardamos mediante el servicio
        pedidoService.guardarPedido(pedidoOriginal);

        // 3. Lo recuperamos de la base de datos simulada
        Optional<Pedido> guardadoOpt = pedidoService.obtenerPedido("PED-999");

        // 4. Comprobamos que el Mapper y el mapa han retenido toda la información
        assertTrue(guardadoOpt.isPresent());
        Pedido pedidoRecuperado = guardadoOpt.get();
        assertEquals("PED-999", pedidoRecuperado.getId());
        assertEquals(EstadoPedido.ABIERTO, pedidoRecuperado.getEstado());
        assertEquals("AdminPepe", pedidoRecuperado.getNombreAdministrador());
    }
}