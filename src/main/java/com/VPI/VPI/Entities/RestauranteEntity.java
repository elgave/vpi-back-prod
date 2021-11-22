package com.VPI.VPI.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurantes")
public class RestauranteEntity extends UsuarioEntity{

    private String nombreRestaurante;
    private String nroHabilitacion;
    private String razonSocial;
    private String rut;
    private Boolean abierto;
    private String direccion;
    private String descripcionMenues;
    private Boolean confirmado;
    private Boolean bloqueado;
    private String celular;
    private String foto;
    private Integer precioEnvio;
    private String horario;
    private Double calificacionVPI;

    public RestauranteEntity() {
    }

    public RestauranteEntity(String nombreRestaurante, String nroHabilitacion, String razonSocial, String rut, Boolean abierto, String direccion, String descripcionMenues, Boolean confirmado, Boolean bloqueado, String celular, String foto, Integer precioEnvio, String horario, Double calificacionVPI, Set<MenuEntity> menus, Set<AgregadoEntity> agregado, Set<PedidoEntity> pedido) {
        this.nombreRestaurante = nombreRestaurante;
        this.nroHabilitacion = nroHabilitacion;
        this.razonSocial = razonSocial;
        this.rut = rut;
        this.abierto = abierto;
        this.direccion = direccion;
        this.descripcionMenues = descripcionMenues;
        this.confirmado = confirmado;
        this.bloqueado = bloqueado;
        this.celular = celular;
        this.foto = foto;
        this.precioEnvio = precioEnvio;
        this.horario = horario;
        this.calificacionVPI = calificacionVPI;
        this.menus = menus;
        this.agregado = agregado;
        this.pedido = pedido;
    }

    @Column(name = "nombre", nullable = false, unique=true)
    public String getNombreRestaurante() {return nombreRestaurante;}
    public void setNombreRestaurante(String nombreRestaurante) {this.nombreRestaurante = nombreRestaurante;
    }

    @Column(name = "nroHabilitacion", nullable = false, unique=true)
    public String getNroHabilitacion() {return nroHabilitacion;}
    public void setNroHabilitacion(String nroHabilitacion) {this.nroHabilitacion = nroHabilitacion;}

    @Column(name = "razonSocial", nullable = false, unique=true)
    public String getRazonSocial() {return razonSocial;}
    public void setRazonSocial(String razonSocial) {this.razonSocial = razonSocial;}

    @Column(name = "rut", nullable = false, unique=true)
    public String getRut() {return rut;}
    public void setRut(String rut) {this.rut = rut;}

    @Column(name = "abierto")
    public Boolean getAbierto() {return abierto;}
    public void setAbierto(Boolean abierto) {this.abierto = abierto;}

    @Column(name = "direccion", nullable = false)
    public String getDireccion() {return direccion;    }
    public void setDireccion(String direccion) {this.direccion = direccion;}

    @Column(name = "descripMenues")
    public String getDescripcionMenues() {return descripcionMenues;}
    public void setDescripcionMenues(String descripcionMenues) {this.descripcionMenues = descripcionMenues;}

    @Column(name = "confirmado")
    public Boolean getConfirmado() {return confirmado;}
    public void setConfirmado(Boolean confirmado) {this.confirmado = confirmado;}

    @Column(name = "bloqueado")
    public Boolean getBloqueado() {return bloqueado;}
    public void setBloqueado(Boolean bloqueado) {this.bloqueado = bloqueado;}

    @Column(name = "celular")
    public String getCelular() {return celular;}
    public void setCelular(String celular) {this.celular = celular;}

    @Column(name = "foto")
    public String getFoto() {return foto;}
    public void setFoto(String foto) {this.foto = foto;}

    @Column(name = "precioEnvio")
    public Integer getPrecioEnvio() {return precioEnvio;}
    public void setPrecioEnvio(Integer precioEnvio) {this.precioEnvio = precioEnvio;}

    @Column(name = "horario")
    public String getHorario() {return horario;}
    public void setHorario(String horario) {this.horario = horario;
    }

    public Double getCalificacionVPI() {
        return calificacionVPI;
    }

    public void setCalificacionVPI(Double calificacionVPI) {
        this.calificacionVPI = calificacionVPI;
    }

    public Set<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(Set<MenuEntity> menus) {
        this.menus = menus;
    }

    public Set<AgregadoEntity> getAgregado() {
        return agregado;
    }

    public void setAgregado(Set<AgregadoEntity> agregado) {
        this.agregado = agregado;
    }

    public Set<PedidoEntity> getPedido() {
        return pedido;
    }

    public void setPedido(Set<PedidoEntity> pedido) {
        this.pedido = pedido;
    }


    @OneToMany(mappedBy = "restaurante",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MenuEntity> menus = new HashSet<>();

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private Set<AgregadoEntity> agregado = new HashSet<>();

    @OneToMany(mappedBy = "restaurante")
    private Set<PedidoEntity> pedido;
}
