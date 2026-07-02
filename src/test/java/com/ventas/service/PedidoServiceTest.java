package com.ventas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ventas.domain.EstadoPedido;
import com.ventas.domain.Pedido;
import com.ventas.domain.PedidoRepository;

public class PedidoServiceTest {

    private PedidoRepository pedidoRepositoryMock;
    private PedidoService pedidoService;

    @BeforeEach
    public void setUp() {
        // Creamos el Mock del repositorio
        this.pedidoRepositoryMock = Mockito.mock(PedidoRepository.class);
        // Se lo pasamos al servicio
        this.pedidoService = new PedidoService(pedidoRepositoryMock);
    }

    @Test
    public void testGuardarPedidoLlamaAlRepositorio() {
        Pedido pedido = new Pedido("PED-123", EstadoPedido.ABIERTO);
        
        pedidoService.guardarPedido(pedido);
        
        // Verificamos que el servicio realmente llamó al método guardar del repo
        verify(pedidoRepositoryMock, times(1)).guardar(pedido);
    }

    @Test
    public void testObtenerPedidoRetornaPedidoCorrecto() {
        Pedido pedidoMock = new Pedido("PED-123", EstadoPedido.ABIERTO);
        when(pedidoRepositoryMock.buscarPorId("PED-123")).thenReturn(Optional.of(pedidoMock));

        Optional<Pedido> resultado = pedidoService.obtenerPedido("PED-123");

        assertTrue(resultado.isPresent());
        assertEquals("PED-123", resultado.get().getId());
        verify(pedidoRepositoryMock, times(1)).buscarPorId("PED-123");
    }
}