package com.ventas.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.ventas.domain.Pedido;
import com.ventas.domain.ItemPedido;

public class PedidoMapperTest {

    @Test
    public void testMapeoCompletoDominioAEntityYViceversa() {
        Pedido pedido = new Pedido("PED-OK", "ABIERTO", "CarlosAdmin");
        pedido.agregarItem(new ItemPedido("Pepe", "Madrid Retro", "L", "RETRO", true, false, "http://foto.com"));

        // Dominio -> Entidad
        PedidoEntity entity = PedidoMapper.toEntity(pedido);
        assertNotNull(entity);
        assertEquals("PED-OK", entity.getId());
        assertEquals(1, entity.getItems().size());
        assertEquals("Pepe", entity.getItems().get(0).getNombrePersona());

        // Entidad -> Dominio
        Pedido dominioRecuperado = PedidoMapper.toDomain(entity);
        assertNotNull(dominioRecuperado);
        assertEquals("CarlosAdmin", dominioRecuperado.getNombreAdministrador());
        assertEquals(25.0, dominioRecuperado.getPrecioVentaCliente()); // Tarjeta Retro = 25€
    }
}