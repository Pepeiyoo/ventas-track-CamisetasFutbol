package com.ventas.domain;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String id;
    private String estado;
    private String nombreAdministrador; // El que organiza el pedido grupal
    private List<ItemPedido> items;

    public Pedido() {
        this.items = new ArrayList<>();
    }

    public Pedido(String id, String estado, String nombreAdministrador) {
        this.id = id;
        this.estado = estado;
        this.nombreAdministrador = nombreAdministrador;
        this.items = new ArrayList<>();
    }

    public void agregarItem(ItemPedido item) {
        this.items.add(item);
    }

    // --- Cálculos Financieros Grupales (Suman lo de cada persona) ---
    public double getCosteTotalFabricacion() {
        return items.stream().mapToDouble(ItemPedido::calcularCoste).sum();
    }

    public double getPrecioVentaCliente() {
        return items.stream().mapToDouble(ItemPedido::calcularPrecioVenta).sum();
    }

    public double getBeneficioNeto() {
        return getPrecioVentaCliente() - getCosteTotalFabricacion();
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getNombreAdministrador() { return nombreAdministrador; }
    public void setNombreAdministrador(String nombreAdministrador) { this.nombreAdministrador = nombreAdministrador; }
 // Cambia el getter de los items en tu Pedido.java por este protegido:
    public List<ItemPedido> getItems() {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        return this.items;
    }
    public void setItems(List<ItemPedido> items) { this.items = items; }
}