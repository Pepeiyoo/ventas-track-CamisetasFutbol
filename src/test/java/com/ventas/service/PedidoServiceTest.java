package com.ventas.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ventas.domain.EstadoPedido;
import com.ventas.domain.Pedido;
import com.ventas.domain.PedidoRepository;

public class PedidoServiceTest {

    @Test
    public void testProcesarYAsignarPedidoConExito() {
        // 1. Creamos el Mock (simulacro) del repositorio
        PedidoRepository repoMock = mock(PedidoRepository.class);
        
        // 2. Preparamos un pedido ficticio
        Pedido pedidoSimulado = new Pedido("PED-200", EstadoPedido.ABIERTO);
        
        // 3. Programamos el comportamiento del Mock (When / Then)
        // "Cuando el servicio busque el ID 'PED-200', devuelve nuestro pedido ficticio"
        when(repoMock.findById("PED-200")).thenReturn(Optional.of(pedidoSimulado));
        
        // 4. Instanciamos el servicio pasándole el repositorio simulado
        PedidoService pedidoService = new PedidoService(repoMock);
        
        // 5. Ejecutamos la acción del servicio
        pedidoService.procesarYAsignar(String.valueOf("PED-200"), "AdministradorPepe");
        
        // 6. Verificaciones
        assertEquals("AdministradorPepe", pedidoSimulado.getNombreAdministrador());
        // Verificamos que el servicio realmente llamó al método .save() para guardar los cambios
        verify(repoMock, times(1)).save(pedidoSimulado);
    }
    @Test
    public void testProcesarPedidoInexistenteLanzaExcepcion() {
        // 1. Creamos el Mock del repositorio
        PedidoRepository repoMock = mock(PedidoRepository.class);
        
        // 2. Simulamos que al buscar el ID "PED-999", el repositorio devuelve un Optional vacío (no existe)
        when(repoMock.findById("PED-999")).thenReturn(Optional.empty());
        
        // 3. Instanciamos el servicio
        PedidoService pedidoService = new PedidoService(repoMock);
        
        // 4. Verificamos que al llamar al método se lance la excepción esperada
        assertThrows(IllegalArgumentException.class, () -> {
            pedidoService.procesarYAsignar("PED-999", "AdministradorPepe");
        });
        
        
        
        // 5. Verificamos que NUNCA se llegó a llamar al método .save() porque el proceso se cortó antes
        verify(repoMock, never()).save(any(Pedido.class));
    }
}
