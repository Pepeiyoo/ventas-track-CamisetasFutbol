package com.ventas.infrastructure.persistence;

import com.ventas.domain.EstadoPedido;
import com.ventas.domain.Pedido;

public class PedidoMapper {

    // Convierte del Dominio puro a la entidad de Base de Datos
    public static PedidoEntity toEntity(Pedido pedido) {
        if (pedido == null) return null;
        return new PedidoEntity(
            pedido.getId(), // ➔ Cambiado a getId() que es el estándar
            pedido.getEstado().name(), 
            pedido.getNombreAdministrador()
        );
    }

    // Convierte de la entidad de Base de Datos al Dominio puro
    public static Pedido toDomain(PedidoEntity entity) {
        if (entity == null) return null;
        
        EstadoPedido estado = EstadoPedido.valueOf(entity.getEstado());
        
        // Reconstruimos el objeto de dominio pasando el id y el estado
        Pedido pedido = new Pedido(entity.getId(), estado); 
        
        if (entity.getNombreAdministrador() != null) {
            pedido.asignarAdministrador(entity.getNombreAdministrador(), true);
        }
        
        return pedido;
    }
}