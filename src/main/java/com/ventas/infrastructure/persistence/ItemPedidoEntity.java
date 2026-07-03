package com.ventas.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_items")
public class ItemPedidoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombrePersona;
    private String modeloCamiseta;
    private String talla;
    private String tipoCamiseta;
    private boolean tieneNombreNumero;
    private boolean tieneParches;
    private String urlFoto;

    public ItemPedidoEntity() {}

    public ItemPedidoEntity(String nombrePersona, String modeloCamiseta, String talla, String tipoCamiseta, boolean tieneNombreNumero, boolean tieneParches, String urlFoto) {
        this.nombrePersona = nombrePersona;
        this.modeloCamiseta = modeloCamiseta;
        this.talla = talla;
        this.tipoCamiseta = tipoCamiseta;
        this.tieneNombreNumero = tieneNombreNumero;
        this.tieneParches = tieneParches;
        this.urlFoto = urlFoto;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public String getNombrePersona() { return nombrePersona; }
    public String getModeloCamiseta() { return modeloCamiseta; }
    public String getTalla() { return talla; }
    public String getTipoCamiseta() { return tipoCamiseta; }
    public boolean isTieneNombreNumero() { return tieneNombreNumero; }
    public boolean isTieneParches() { return tieneParches; }
    public String getUrlFoto() { return urlFoto; }
}