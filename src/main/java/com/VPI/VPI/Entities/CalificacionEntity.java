package com.VPI.VPI.Entities;

import javax.persistence.*;

@Entity
@Table(name = "calificaciones")
public class CalificacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer puntuacion;
    private Boolean isCliente;
    private String cliente_id;
    private String restaurante_id;

    public CalificacionEntity() {
    }

    public CalificacionEntity(Integer id, Integer puntuacion, Boolean isCliente, String cliente_id, String restaurante_id) {
        this.id = id;
        this.puntuacion = puntuacion;
        this.isCliente = isCliente;
        this.cliente_id = cliente_id;
        this.restaurante_id = restaurante_id;
    }

    @Column(name = "id", nullable = false)
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    @Column(name = "puntuacion", nullable = false)
    public Integer getPuntuacion() {return puntuacion;}
    public void setPuntuacion(Integer puntuacion) {this.puntuacion = puntuacion;}

    @Column(name = "isCliente", nullable = false)
    public boolean isCliente() {return isCliente;}
    public void setCliente(Boolean cliente) {isCliente = cliente;}

    @Column(name = "cliente_id", nullable = false)
    public String getCliente_id() {return cliente_id;}
    public void setCliente_id(String cliente_id) {this.cliente_id = cliente_id;}

    @Column(name = "restaurante_id", nullable = false)
    public String getRestaurante_id() {return restaurante_id;}
    public void setRestaurante_id(String restaurante_id) {this.restaurante_id = restaurante_id;}

}

