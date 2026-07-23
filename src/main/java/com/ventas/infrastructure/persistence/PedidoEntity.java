package com.ventas.infrastructure.persistence;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    private String id;
    private String estado;
    private String nombreAdministrador;

    // Relación uno a muchos: Un pedido contiene muchas personas/camisetas
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedidoEntity> items = new ArrayList<>();

    public PedidoEntity() {}

    public PedidoEntity(String id, String estado, String nombreAdministrador) {
        this.id = id;
        this.estado = estado;
        this.nombreAdministrador = nombreAdministrador;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getNombreAdministrador() { return nombreAdministrador; }
    public void setNombreAdministrador(String nombreAdministrador) { this.nombreAdministrador = nombreAdministrador; }
    public List<ItemPedidoEntity> getItems() { return items; }
    public void setItems(List<ItemPedidoEntity> items) { this.items = items; }
}