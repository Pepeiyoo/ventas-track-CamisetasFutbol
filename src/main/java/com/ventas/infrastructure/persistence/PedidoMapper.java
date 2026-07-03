package com.ventas.infrastructure.persistence;

import com.ventas.domain.EstadoPedido;
import com.ventas.domain.Pedido;

public class PedidoMapper {

    public static PedidoEntity toEntity(Pedido pedido) {
        if (pedido == null) return null;
        return new PedidoEntity(
            pedido.getId(),
            pedido.getEstado(), // Arreglado: ya es String, no lleva .name()
            pedido.getNombreAdministrador(),
            pedido.getModeloCamiseta(),
            pedido.getTipoCamiseta(),
            pedido.isTieneNombreNumero(),
            pedido.isTieneParches()
        );
    }

    public static Pedido toDomain(PedidoEntity entity) {
        if (entity == null) return null;
        
        return new Pedido(
            entity.getId(),
            entity.getEstado(),
            entity.getNombreAdministrador(),
            entity.getModeloCamiseta(),
            entity.getTipoCamiseta(),
            entity.isTieneNombreNumero(),
            entity.isTieneParches()
        );
    }
}