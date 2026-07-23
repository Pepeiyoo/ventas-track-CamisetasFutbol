package com.ventas.infrastructure.persistence;

import com.ventas.domain.Pedido;
import com.ventas.domain.PedidoRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Primary
public class JpaPedidoRepository implements PedidoRepository {

    private final SpringDataPedidoRepository springDataRepository;

    // Inyectamos el repositorio nativo de Spring
    public JpaPedidoRepository(SpringDataPedidoRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public void guardar(Pedido pedido) {
        if (pedido == null) return;
        // Convertimos dominio a entidad y guardamos en la BD real
        PedidoEntity entity = PedidoMapper.toEntity(pedido);
        springDataRepository.save(entity);
    }

    @Override
    public Optional<Pedido> buscarPorId(String id) {
        // Buscamos en la BD real y reconvertimos a objeto de dominio puro
        return springDataRepository.findById(id)
                .map(PedidoMapper::toDomain);
    }

    @Override
    public void eliminarPorId(String id) {
        springDataRepository.deleteById(id);
    }

    // 🔥 CORREGIDO: Cambiado de listarTodos() a findAll() y añadido @Override
    @Override
    public List<Pedido> findAll() {
        return springDataRepository.findAll()
                .stream()
                .map(PedidoMapper::toDomain)
                .collect(Collectors.toList());
    }
}