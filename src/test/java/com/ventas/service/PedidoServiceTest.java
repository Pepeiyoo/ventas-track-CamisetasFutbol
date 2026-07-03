package com.ventas.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.ventas.domain.Pedido;
import com.ventas.domain.ItemPedido;
import com.ventas.infrastructure.persistence.FakePedidoRepository;

public class PedidoServiceTest {

    @Test
    public void testGuardarYBuscarPedidoEnRepositorio() {
        FakePedidoRepository repository = new FakePedidoRepository();
        Pedido pedido = new Pedido("PED-MOCK", "ABIERTO", "Organizador");
        pedido.agregarItem(new ItemPedido("Asis", "Arsenal", "L", "PLAYER", false, false, ""));
        
        repository.guardar(pedido);
        
        java.util.Optional<Pedido> encontrado = repository.buscarPorId("PED-MOCK");
        assertTrue(encontrado.isPresent());
        assertEquals("Organizador", encontrado.get().getNombreAdministrador());
        assertEquals(1, encontrado.get().getItems().size());
    }
}