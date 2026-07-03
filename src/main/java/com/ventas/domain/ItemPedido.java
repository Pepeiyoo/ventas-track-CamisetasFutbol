package com.ventas.domain;

public class ItemPedido {
    private String nombrePersona;
    private String modeloCamiseta;
    private String talla;
    private String tipoCamiseta; // "FAN", "PLAYER", "RETRO", "NINO"
    private boolean tieneNombreNumero;
    private boolean tieneParches;
    private String urlFoto;

    public ItemPedido() {}

    public ItemPedido(String nombrePersona, String modeloCamiseta, String talla, String tipoCamiseta, boolean tieneNombreNumero, boolean tieneParches, String urlFoto) {
        this.nombrePersona = nombrePersona;
        this.modeloCamiseta = modeloCamiseta;
        this.talla = talla;
        this.tipoCamiseta = tipoCamiseta;
        this.tieneNombreNumero = tieneNombreNumero;
        this.tieneParches = tieneParches;
        // Si no ponen URL, dejamos el campo vacío para manejarlo con elegancia en el HTML
        this.urlFoto = (urlFoto == null || urlFoto.trim().isEmpty()) ? "" : urlFoto.trim();
    }

    public double calcularCoste() {
        if ("FAN".equalsIgnoreCase(tipoCamiseta)) {
            double coste = 8.0;
            if (tieneParches) coste += 1.0;
            if (tieneNombreNumero) coste += 2.0;
            return coste;
        } else if ("PLAYER".equalsIgnoreCase(tipoCamiseta)) {
            double coste = 11.0;
            if (tieneParches) coste += 1.0;
            if (tieneNombreNumero) coste += 2.0;
            return coste;
        } else if ("RETRO".equalsIgnoreCase(tipoCamiseta)) {
            return 11.0; // Precio de coste fijo sin extras alterando el total
        } else if ("NINO".equalsIgnoreCase(tipoCamiseta)) {
            return 13.0; // ¡Actualizado! Tu nuevo precio de coste base para equipaciones completas
        }
        return 0.0;
    }

    public double calcularPrecioVenta() {
        if ("RETRO".equalsIgnoreCase(tipoCamiseta) || "NINO".equalsIgnoreCase(tipoCamiseta)) {
            return 25.0; // Tarifa plana de venta que tú cobras
        }
        // Para Fan y Player normales
        return tieneNombreNumero ? 22.0 : 20.0;
    }

    public double calcularBeneficio() {
        return calcularPrecioVenta() - calcularCoste();
    }

    // Getters y Setters
    public String getNombrePersona() { return nombrePersona; }
    public String getModeloCamiseta() { return modeloCamiseta; }
    public String getTalla() { return talla; }
    public String getTipoCamiseta() { return tipoCamiseta; }
    public boolean isTieneNombreNumero() { return tieneNombreNumero; }
    public boolean isTieneParches() { return tieneParches; }
    public String getUrlFoto() { return urlFoto; }
}