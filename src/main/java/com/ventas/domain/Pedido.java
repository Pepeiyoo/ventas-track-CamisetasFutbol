package com.ventas.domain;

public class Pedido {
    private String id;
    private String estado;
    private String nombreAdministrador;
    
    // Campos para la gestión económica de las camisetas
    private String modeloCamiseta; 
    private String tipoCamiseta;   // "FAN" o "PLAYER"
    private boolean tieneNombreNumero;
    private boolean tieneParches;

    // 1. Constructor Completo (Para la API y Persistencia)
    public Pedido(String id, String estado, String nombreAdministrador, String modeloCamiseta, String tipoCamiseta, boolean tieneNombreNumero, boolean tieneParches) {
        this.id = id;
        this.estado = estado;
        this.nombreAdministrador = nombreAdministrador;
        this.modeloCamiseta = modeloCamiseta;
        this.tipoCamiseta = tipoCamiseta;
        this.tieneNombreNumero = tieneNombreNumero;
        this.tieneParches = tieneParches;
    }

    // 2. Constructor Clave para tus Tests Universitarios (Ver imagen be1b06 y bf738b)
    public Pedido(String id, EstadoPedido estadoEnum) {
        this.id = id;
        this.estado = estadoEnum.name();
        this.modeloCamiseta = "Genérico";
        this.tipoCamiseta = "FAN";
    }

    // --- Métodos de Lógica Económica ---
    public double calcularCoste() {
        double coste = 0.0;
        if ("PLAYER".equalsIgnoreCase(this.tipoCamiseta)) {
            coste += 11.0;
        } else {
            coste += 8.0;
        }
        if (this.tieneParches) coste += 1.0;
        if (this.tieneNombreNumero) coste += 2.0;
        return coste;
    }

    public double calcularPrecioVenta() {
        return this.tieneNombreNumero ? 22.0 : 20.0;
    }

    public double calcularBeneficio() {
        return calcularPrecioVenta() - calcularCoste();
    }

    // --- Métodos de Reglas que piden tus Tests (Ver imagen bf738b) ---
    public void cerrar() {
        if (!"EN_PROCESO".equals(this.estado)) {
            throw new IllegalStateException("No se puede cerrar un pedido si no está en proceso");
        }
        this.estado = "CERRADO";
    }

    public void asignarAdministrador(String nombre, boolean esActivo) {
        if (!esActivo) {
            throw new IllegalArgumentException("No se puede asignar un administrador inactivo");
        }
        this.nombreAdministrador = nombre;
    }

    // --- Getters y Setters Estándar ---
    public String getId() { return id; }
    public String getEstado() { return estado; }
    public String getNombreAdministrador() { return nombreAdministrador; }
    public String getModeloCamiseta() { return modeloCamiseta; }
    public String getTipoCamiseta() { return tipoCamiseta; }
    public boolean isTieneNombreNumero() { return tieneNombreNumero; }
    public boolean isTieneParches() { return tieneParches; }
    public void setEstado(String estado) { this.estado = estado; }
}