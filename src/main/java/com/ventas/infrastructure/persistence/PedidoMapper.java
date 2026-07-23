package com.ventas.infrastructure.persistence;

import com.ventas.domain.Pedido;
import com.ventas.domain.ItemPedido;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoEntity toEntity(Pedido pedido) {
        if (pedido == null) return null;
        PedidoEntity entity = new PedidoEntity(pedido.getId(), pedido.getEstado(), pedido.getNombreAdministrador());
        
        entity.setItems(pedido.getItems().stream().map(i -> new ItemPedidoEntity(
            i.getId(), i.getNombrePersona(), i.getModeloCamiseta(), i.getTalla(), i.getTipoCamiseta(),
            i.isTieneNombreNumero(), i.isTieneParches(), i.getUrlFoto(), i.isPagado()
        )).collect(Collectors.toList()));
        
        return entity;
    }

    public static Pedido toDomain(PedidoEntity entity) {
        if (entity == null) return null;
        Pedido pedido = new Pedido(entity.getId(), entity.getEstado(), entity.getNombreAdministrador());
        
        // Si la entidad de la BD tiene los ítems como null, evitamos que rompa el flujo
        if (entity.getItems() != null) {
            entity.getItems().forEach(i -> {
                ItemPedido item = new ItemPedido();
                // Compatibilidad: si no existe itemId en filas antiguas, usamos la PK numérica.
                item.setId(i.getItemId() != null ? i.getItemId() : String.valueOf(i.getId()));
                item.setNombrePersona(i.getNombrePersona());
                item.setModeloCamiseta(i.getModeloCamiseta());
                item.setTalla(i.getTalla());
                item.setTipoCamiseta(i.getTipoCamiseta());
                item.setTieneNombreNumero(i.isTieneNombreNumero());
                item.setTieneParches(i.isTieneParches());
                item.setUrlFoto(i.getUrlFoto());
                item.setPagado(i.isPagado());
                pedido.agregarItem(item);
            });
        }
        return pedido;
    }
}