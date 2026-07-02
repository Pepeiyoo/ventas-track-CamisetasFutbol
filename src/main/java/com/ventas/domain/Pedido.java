package com.ventas.domain;

public class Pedido {
    private String id;
    private EstadoPedido estado;

    public Pedido(String id, EstadoPedido estado) {
        this.id = id;
        this.estado = estado;
    }

    public void cerrar() {
        if (this.estado != EstadoPedido.EN_PROCESO) {
            throw new IllegalStateException("No se puede cerrar un pedido que no esté EN_PROCESO");
        }
        this.estado = EstadoPedido.CERRADO;
    }

    // Asegúrate de que se llame exactamente así, sin espacios:
    public EstadoPedido getEstado() { 
        return this.estado;
    }
}