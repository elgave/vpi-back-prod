package com.VPI.VPI.Entities;

import javax.persistence.*;

@Entity
@Table(name="Direcciones")
public class DireccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Boolean principal;
    private String calle;
    private String esquina;
    private String numero;
    private String apto;
    private String Barrio;

    public DireccionEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getEsquina() {
        return esquina;
    }

    public void setEsquina(String esquina) {
        this.esquina = esquina;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getApto() {
        return apto;
    }

    public void setApto(String apto) {
        this.apto = apto;
    }

    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String barrio) {
        Barrio = barrio;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public DireccionEntity(Integer id, String nombre, Boolean principal, String calle, String esquina, String numero, String apto, String barrio, ClienteEntity cliente) {
        this.id = id;
        this.nombre = nombre;
        this.principal = principal;
        this.calle = calle;
        this.esquina = esquina;
        this.numero = numero;
        this.apto = apto;
        Barrio = barrio;
        this.cliente = cliente;
    }

    /*Relaciones*/
    @ManyToOne
    @JoinColumn(name = "idCliente",referencedColumnName = "email")
    private ClienteEntity cliente;
}


