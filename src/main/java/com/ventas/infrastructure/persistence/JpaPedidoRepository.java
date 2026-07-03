package com.ventas.infrastructure.persistence;

import com.ventas.domain.Pedido;
import com.ventas.domain.PedidoRepository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.util.Optional;

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
}