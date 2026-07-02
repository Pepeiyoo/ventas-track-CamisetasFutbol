package com.ventas.domain;

public class Pedido {
    private String id;
    private EstadoPedido estado;
    private String nombreAdministrador;

    public Pedido(String id, EstadoPedido estado) {
        this.id = id;
        this.estado = estado;
    }

    // Regla de Negocio 1: Cierre de Pedidos
    public void cerrar() {
        if (this.estado != EstadoPedido.EN_PROCESO) {
            throw new IllegalStateException("No se puede cerrar un pedido que no esté EN_PROCESO");
        }
        this.estado = EstadoPedido.CERRADO;
    }

    // Regla de Negocio 2: Validación de Administrador Activo (Fase GREEN)
    public void asignarAdministrador(String nombreAdmin, boolean activo) {
        if (!activo) {
            throw new IllegalArgumentException("El administrador debe estar activo para gestionar el pedido");
        }
        this.nombreAdministrador = nombreAdmin;
    }

    // ➔ ¡AÑADE ESTE MÉTODO AQUÍ QUE FALTABA!
    public String getId() {
        return this.id;
    }

    
    public EstadoPedido getEstado() { 
        return this.estado;
    }

    public String getNombreAdministrador() {
        return this.nombreAdministrador;
    }
}