package com.VPI.VPI.Entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clientes")
public class ClienteEntity extends UsuarioEntity{

    private String nombre;
    private String apellido;
    private Boolean bloqueado;
    private String celular;
    private String foto;
    private String token;

    public ClienteEntity() {
    }

    public ClienteEntity(String nombre, String apellido, Boolean bloqueado, String celular, String foto, Set<PedidoEntity> pedido, Set<DireccionEntity> direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.bloqueado = bloqueado;
        this.celular = celular;
        this.foto = foto;
        this.pedido = pedido;
        this.direccion = direccion;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    @Column(name = "apellido", nullable = false)
    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}

    @Column(name = "bloqueado", nullable = false)
    public Boolean getBloqueado() {return bloqueado;}
    public void setBloqueado(Boolean bloqueado) {this.bloqueado = bloqueado;}

    @Column(name = "celular", nullable = false)
    public String getCelular() {return celular;}
    public void setCelular(String celular) {this.celular = celular;}

    @Column(name = "foto", nullable = false)
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Column(name = "token", nullable = true) // rafa
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public Set<PedidoEntity> getPedido() {
        return pedido;
    }

    public void setPedido(Set<PedidoEntity> pedido) {
        this.pedido = pedido;
    }

    public Set<DireccionEntity> getDireccion() {
        return direccion;
    }

    public void setDireccion(Set<DireccionEntity> direccion) {
        this.direccion = direccion;
    }


    @OneToMany(mappedBy = "cliente")
    private Set<PedidoEntity> pedido;

    @OneToMany(cascade=CascadeType.REMOVE, mappedBy = "cliente")
    private Set<DireccionEntity> direccion;
}
