package com.ventas.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    private String id;
    private String estado;
    private String nombreAdministrador;
    private String modeloCamiseta;
    private String tipoCamiseta;
    private boolean tieneNombreNumero;
    private boolean tieneParches;

    public PedidoEntity() {}

    // Constructor completo para la base de datos
    public PedidoEntity(String id, String estado, String nombreAdministrador, String modeloCamiseta, String tipoCamiseta, boolean tieneNombreNumero, boolean tieneParches) {
        this.id = id;
        this.estado = estado;
        this.nombreAdministrador = nombreAdministrador;
        this.modeloCamiseta = modeloCamiseta;
        this.tipoCamiseta = tipoCamiseta;
        this.tieneNombreNumero = tieneNombreNumero;
        this.tieneParches = tieneParches;
    }

    // Constructor alternativo rápido para solucionar el test antiguo (Ver imagen bf73c6)
    public PedidoEntity(String id, String estado, String nombreAdministrador) {
        this.id = id;
        this.estado = estado;
        this.nombreAdministrador = nombreAdministrador;
        this.modeloCamiseta = "Genérico";
        this.tipoCamiseta = "FAN";
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getNombreAdministrador() { return nombreAdministrador; }
    public void setNombreAdministrador(String nombreAdministrador) { this.nombreAdministrador = nombreAdministrador; }
    public String getModeloCamiseta() { return modeloCamiseta; }
    public void setModeloCamiseta(String modeloCamiseta) { this.modeloCamiseta = modeloCamiseta; }
    public String getTipoCamiseta() { return tipoCamiseta; }
    public void setTipoCamiseta(String tipoCamiseta) { this.tipoCamiseta = tipoCamiseta; }
    public boolean isTieneNombreNumero() { return tieneNombreNumero; }
    public void setTieneNombreNumero(boolean tieneNombreNumero) { this.tieneNombreNumero = tieneNombreNumero; }
    public boolean isTieneParches() { return tieneParches; }
    public void setTieneParches(boolean tieneParches) { this.tieneParches = tieneParches; }
}