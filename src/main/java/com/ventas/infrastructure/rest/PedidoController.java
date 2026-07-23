package com.ventas.infrastructure.rest;

import com.ventas.domain.Pedido;
import com.ventas.domain.ItemPedido;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoController {

    // --- Clase interna DTO para mostrar los cálculos económicos coordinada con tu Pedido.java ---
    public static class PedidoRespuestaDto {
        public String id;
        public String estado;
        public String nombreAdministrador;
        
        public double costeTotalFabricacion;
        public double precioVentaCliente;
        public double beneficioNeto;
        public List<ItemRespuestaDto> items;

        public PedidoRespuestaDto(Pedido p) {
            this.id = p.getId();
            this.estado = p.getEstado();
            this.nombreAdministrador = p.getNombreAdministrador();
            
            // Corregido: Usamos los nombres exactos de tus métodos get de Pedido.java
            this.costeTotalFabricacion = p.getCosteTotalFabricacion();
            this.precioVentaCliente = p.getPrecioVentaCliente();
            this.beneficioNeto = p.getBeneficioNeto();
            
            this.items = p.getItems().stream()
                    .map(ItemRespuestaDto::new)
                    .collect(Collectors.toList());
        }
    }

    // Estructura individual de la camiseta para la vista y modales
    public static class ItemRespuestaDto {
        public String id;
        public String nombrePersona;
        public String modeloCamiseta;
        public String talla;
        public String tipoCamiseta;
        public boolean tieneNombreNumero;
        public boolean tieneParches;
        public String nombreDorsal;
        public String numeroDorsal;
        public String tipoParche;
        public String urlFoto;
        public boolean pagado;
        public double costeIndividual;
        public double precioVentaIndividual;
        public double beneficioIndividual;

        public ItemRespuestaDto(ItemPedido item) {
            this.id = item.getId();
            this.nombrePersona = item.getNombrePersona();
            this.modeloCamiseta = item.getModeloCamiseta();
            this.talla = item.getTalla();
            this.tipoCamiseta = item.getTipoCamiseta();
            this.tieneNombreNumero = item.isTieneNombreNumero();
            this.tieneParches = item.isTieneParches();
            this.nombreDorsal = item.getNombreDorsal();
            this.numeroDorsal = item.getNumeroDorsal();
            this.tipoParche = item.getTipoParche();
            this.urlFoto = item.getUrlFoto();
            this.pagado = item.isPagado();
            this.costeIndividual = item.calcularCoste();
            this.precioVentaIndividual = item.calcularPrecioVenta();
            this.beneficioIndividual = item.calcularBeneficio();
        }
    }
}