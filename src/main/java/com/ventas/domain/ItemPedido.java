package com.ventas.domain;

import java.util.UUID;

public class ItemPedido {
    private String id;
    private String nombrePersona;
    private String modeloCamiseta;
    private String talla;
    private String tipoCamiseta; // "FAN", "PLAYER", "RETRO"
    private boolean tieneNombreNumero;
    private boolean tieneParches;
    private String urlFoto;
    private boolean pagado; // Controla si está cobrado o pendiente

    // Constructor completo
    public ItemPedido(String nombrePersona, String modeloCamiseta, String talla, 
                      String tipoCamiseta, boolean tieneNombreNumero, boolean tieneParches, String urlFoto) {
        this.id = UUID.randomUUID().toString().substring(0, 8); // Genera un ID único corto para la camiseta
        this.nombrePersona = nombrePersona;
        this.modeloCamiseta = modeloCamiseta;
        this.talla = talla;
        this.tipoCamiseta = tipoCamiseta != null ? tipoCamiseta.toUpperCase() : "FAN";
        this.tieneNombreNumero = tieneNombreNumero;
        this.tieneParches = tieneParches;
        this.urlFoto = urlFoto;
        this.pagado = false; // Por defecto nace sin pagar ("Cobro Pendiente")
    }

    // Constructor vacío requerido por persistencia/mapeadores
    public ItemPedido() {}

    // 🟢 1. CORRECCIÓN DE PRECIOS (Regla de negocio: Base 20€ + 2€ Nombre/Número)
    public double calcularPrecioVenta() {
        String version = this.tipoCamiseta != null ? this.tipoCamiseta.toUpperCase() : "FAN";

        // RETRO y NINO tienen precio de venta fijo: los extras no alteran el PVP
        if ("RETRO".equals(version) || "NINO".equals(version)) {
            return 25.00;
        }

        // FAN y PLAYER: base 20€ + 2€ si lleva nombre/número
        double precioBase = 20.00;
        if (this.tieneNombreNumero) {
            precioBase += 2.00; // +2.00 € Serigrafía
        }
        return precioBase;
    }

    public double calcularCoste() {
        double costeBase = 8.00; // Coste base de una FAN
        
        String version = this.tipoCamiseta != null ? this.tipoCamiseta.toUpperCase() : "FAN";
        
        // RETRO y NINO tienen coste fijo: los extras no alteran el coste de fábrica
        if ("RETRO".equals(version)) {
            return 11.00;
        } else if ("NINO".equals(version)) {
            return 13.00;
        } else if ("PLAYER".equals(version)) {
            costeBase = 11.00; // Coste de fábrica PLAYER
        }
        
        if (this.tieneNombreNumero) {
            costeBase += 2.00; // +2.00 € de coste por estampar nombre
        }
        if (this.tieneParches) {
            costeBase += 1.00; // +1.00 € al coste de fábrica por los parches
        }
        return costeBase;
    }

    public double calcularBeneficio() {
        return calcularPrecioVenta() - calcularCoste();
    }

    // 🟢 2. ACCIÓN PARA MARCAR COMO PAGADO
    public void alternarPago() {
        this.pagado = !this.pagado; // Si era falso pasa a verdadero, y viceversa
    }

    // --- GETTERS Y SETTERS (Imprescindibles para que la foto y la vista funcionen) ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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

    // El getter de la foto debe llamarse exactamente así para que Thymeleaf/HTML lo pinte
    public String getUrlFoto() { return urlFoto; } 
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }

    public boolean isPagado() { return pagado; }
    public void setPagado(boolean pagado) { this.pagado = pagado; }
}