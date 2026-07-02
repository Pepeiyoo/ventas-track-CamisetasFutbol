package com.ventas.infrastructure.persistence;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.ventas.domain.Pedido;
import com.ventas.infrastructure.persistence.PedidoEntity;
import com.ventas.infrastructure.persistence.PedidoMapper;
import com.ventas.domain.EstadoPedido;

public class PedidoMapperTest {

    @Test
    public void testMapeoDominioAEntity() {
        Pedido pedido = new Pedido("PED-100", EstadoPedido.ABIERTO);
        pedido.asignarAdministrador("CarlosAdmin", true);

        PedidoEntity entity = PedidoMapper.toEntity(pedido);

        assertNotNull(entity);
        assertEquals("PED-100", entity.getId());
        assertEquals("ABIERTO", entity.getEstado());
        assertEquals("CarlosAdmin", entity.getNombreAdministrador());
    }

    @Test
    public void testMapeoEntityADominio() {
        PedidoEntity entity = new PedidoEntity("PED-100", "ABIERTO", "CarlosAdmin");

        Pedido pedido = PedidoMapper.toDomain(entity);

        assertNotNull(pedido);
        assertEquals("PED-100", pedido.getId());
        assertEquals(EstadoPedido.ABIERTO, pedido.getEstado());
        assertEquals("CarlosAdmin", pedido.getNombreAdministrador());
    }
}