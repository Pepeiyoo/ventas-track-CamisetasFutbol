package com.ventas.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_items")
public class ItemPedidoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID funcional del dominio usado en rutas de la vista.
    private String itemId;
    
    private String nombrePersona;
    private String modeloCamiseta;
    private String talla;
    private String tipoCamiseta;
    private boolean tieneNombreNumero;
    private boolean tieneParches;
    private String urlFoto;
    private boolean pagado;

    public ItemPedidoEntity() {}

    public ItemPedidoEntity(String itemId, String nombrePersona, String modeloCamiseta, String talla,
                            String tipoCamiseta, boolean tieneNombreNumero, boolean tieneParches,
                            String urlFoto, boolean pagado) {
        this.itemId = itemId;
        this.nombrePersona = nombrePersona;
        this.modeloCamiseta = modeloCamiseta;
        this.talla = talla;
        this.tipoCamiseta = tipoCamiseta;
        this.tieneNombreNumero = tieneNombreNumero;
        this.tieneParches = tieneParches;
        this.urlFoto = urlFoto;
        this.pagado = pagado;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }
    public String getNombrePersona() { return nombrePersona; }
    public void setNombrePersona(String nombrePersona) { this.nombrePersona = nombrePersona; }
    public String getModeloCamiseta() { return modeloCamiseta; }
    public void setModeloCamiseta(String modeloCamiseta) { this.modeloCamiseta = modeloCamiseta; }
    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }
    public String getTipoCamiseta() { return tipoCamiseta; }
    public void setTipoCamiseta(String tipoCamiseta) { this.tipoCamiseta = tipoCamiseta; }
    public boolean isTieneNombreNumero() { return tieneNombreNumero; }
    public void setTieneNombreNumero(boolean tieneNombreNumero) { this.tieneNombreNumero = tieneNombreNumero; }
    public boolean isTieneParches() { return tieneParches; }
    public void setTieneParches(boolean tieneParches) { this.tieneParches = tieneParches; }
    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }
    public boolean isPagado() { return pagado; }
    public void setPagado(boolean pagado) { this.pagado = pagado; }
}