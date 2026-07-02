package com.ventas.infrastructure.persistence;

//Usamos anotaciones estándar de JPA para cuando metas Hibernate/Base de datos
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

 // Constructor vacío obligatorio para JPA
 public PedidoEntity() {
 }

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
}