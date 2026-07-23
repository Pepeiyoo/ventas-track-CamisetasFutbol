package com.ventas.infrastructure.persistence;

import com.ventas.domain.Pedido;
import com.ventas.domain.PedidoRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FakePedidoRepository implements PedidoRepository {

    // Simulamos la tabla de la base de datos con un mapa en memoria
    private final Map<String, PedidoEntity> tablaPedidos = new HashMap<>();

    @Override
    public void guardar(Pedido pedido) {
        if (pedido == null) return;
        
        // Pasamos el Pedido (Dominio) a PedidoEntity (Persistencia)
        PedidoEntity entity = PedidoMapper.toEntity(pedido);
        
        // Lo guardamos en nuestro mapa simulando la base de datos
        tablaPedidos.put(entity.getId(), entity);
    }

    @Override
    public Optional<Pedido> buscarPorId(String id) {
        PedidoEntity entity = tablaPedidos.get(id);
        if (entity == null) {
            return Optional.empty();
        }
        // Convertimos la entidad de la "BD" de vuelta a objeto de Dominio puro
        return Optional.of(PedidoMapper.toDomain(entity));
    }

    @Override
    public void eliminarPorId(String id) {
        tablaPedidos.remove(id);
    }
    
    // 🔥 NUEVO: Método añadido para cumplir con la interfaz PedidoRepository
    @Override
    public List<Pedido> findAll() {
        return tablaPedidos.values().stream()
                .map(PedidoMapper::toDomain)
                .collect(Collectors.toList());
    }
}