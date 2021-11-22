package com.VPI.VPI.Entities;

import javax.persistence.*;

@Entity
@Table(name="favoritos")
public class FavoritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cliente_id;
    private String restaurante_id;

    public FavoritoEntity(){}

    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "cliente_id", nullable = false)
    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    @Column(name = "restaurante_id", nullable = false)
    public String getRestaurante_id() {
        return restaurante_id;
    }

    public void setRestaurante_id(String restaurante_id) {
        this.restaurante_id = restaurante_id;
    }
}
