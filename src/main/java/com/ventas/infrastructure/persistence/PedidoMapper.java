package com.ventas.infrastructure.persistence;

import com.ventas.domain.Pedido;
import com.ventas.domain.ItemPedido;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoEntity toEntity(Pedido pedido) {
        if (pedido == null) return null;
        PedidoEntity entity = new PedidoEntity(pedido.getId(), pedido.getEstado(), pedido.getNombreAdministrador());
        
        entity.setItems(pedido.getItems().stream().map(i -> new ItemPedidoEntity(
            i.getNombrePersona(), i.getModeloCamiseta(), i.getTalla(), i.getTipoCamiseta(),
            i.isTieneNombreNumero(), i.isTieneParches(), i.getUrlFoto()
        )).collect(Collectors.toList()));
        
        return entity;
    }

    public static Pedido toDomain(PedidoEntity entity) {
        if (entity == null) return null;
        Pedido pedido = new Pedido(entity.getId(), entity.getEstado(), entity.getNombreAdministrador());
        
        if (entity.getItems() != null) {
            entity.getItems().forEach(i -> pedido.agregarItem(new ItemPedido(
                i.getNombrePersona(), i.getModeloCamiseta(), i.getTalla(), i.getTipoCamiseta(),
                i.isTieneNombreNumero(), i.isTieneParches(), i.getUrlFoto()
            )));
        }
        return pedido;
    }
}