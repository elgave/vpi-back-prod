package com.VPI.VPI.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Agregados")
public class AgregadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Double costo;

    public AgregadoEntity() {
    }

    public AgregadoEntity(Integer id, String nombre, Double costo, Set<MenuEntity> menus, RestauranteEntity restaurante) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.menus = menus;
        this.restaurante = restaurante;
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

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public RestauranteEntity getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteEntity restaurante) {
        this.restaurante = restaurante;
    }

    public Set<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(Set<MenuEntity> menus) {
        this.menus = menus;
    }

    /*Relaciones*/
    /*@ManyToMany
    @JoinTable(
            name = "menu_agregados",
            joinColumns = { @JoinColumn(name = "agregado_id") },
            inverseJoinColumns = { @JoinColumn(name = "menu_id") })
    private Set<MenuEntity> menu = new HashSet<>();*/

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "menu_agregados",
            joinColumns = { @JoinColumn(name = "agregado_id") },
            inverseJoinColumns = { @JoinColumn(name = "menu_id") })
    private Set<MenuEntity> menus = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "Restaurante_id", referencedColumnName = "email")
    private RestauranteEntity restaurante;
}
