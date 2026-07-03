package com.ventas.domain;

public class ItemPedido {
    private String nombrePersona;
    private String modeloCamiseta;
    private String talla;
    private String tipoCamiseta; // "FAN", "PLAYER", "RETRO", "NINO"
    private boolean tieneNombreNumero;
    private boolean tieneParches;
    private String urlFoto; // Para identificar el diseño visual

    public ItemPedido() {}

    public ItemPedido(String nombrePersona, String modeloCamiseta, String talla, String tipoCamiseta, boolean tieneNombreNumero, boolean tieneParches, String urlFoto) {
        this.nombrePersona = nombrePersona;
        this.modeloCamiseta = modeloCamiseta;
        this.talla = talla;
        this.tipoCamiseta = tipoCamiseta;
        this.tieneNombreNumero = tieneNombreNumero;
        this.tieneParches = tieneParches;
        this.urlFoto = (urlFoto == null || urlFoto.trim().isEmpty()) ? "https://placehold.co/60x60?text=Camiseta" : urlFoto;
    }

    // Lógica de costes según tus nuevas reglas
    public double calcularCoste() {
        double costeBase = "FAN".equalsIgnoreCase(tipoCamiseta) ? 8.0 : 11.0; // Player, Retro y Niño cuestan 11€
        if (tieneParches && !"RETRO".equalsIgnoreCase(tipoCamiseta) && !"NINO".equalsIgnoreCase(tipoCamiseta)) {
            costeBase += 1.0;
        }
        if (tieneNombreNumero && !"RETRO".equalsIgnoreCase(tipoCamiseta) && !"NINO".equalsIgnoreCase(tipoCamiseta)) {
            costeBase += 2.0;
        }
        return costeBase;
    }

    // Lógica de precios de venta fijos y variables
    public double calcularPrecioVenta() {
        if ("RETRO".equalsIgnoreCase(tipoCamiseta) || "NINO".equalsIgnoreCase(tipoCamiseta)) {
            return 25.0; // Tus tarifas planas fijas
        }
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