package com.VPI.VPI.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Menues")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMenu;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Boolean promocion;
    private String descripcion;
    private Double costo;
    private Double descuento;
    private String imagen;

    public enum Categoria {Pizza, Sushi, Empanadas, Saludable, Hamburguesas,
        Milanesas, Helados, Postres, Vegetariana, Italiana, Pastas,
        Chivito, Panadería, Parrilla, Vegano, Celiaco, Otros;}

    public MenuEntity() {
    }

    public MenuEntity(Integer idMenu, String nombre, Categoria categoria, Boolean promocion,
                      String descripcion, Double costo, Double descuento, String imagen, RestauranteEntity restaurante,
                      Set<AgregadoEntity> agregados) {
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.categoria = categoria;
        this.promocion = promocion;
        this.descripcion = descripcion;
        this.costo = costo;
        this.descuento = descuento;
        this.imagen = imagen;
        this.restaurante = restaurante;
        this.agregados = agregados;
    }

    @Column(name = "id")
    public Integer getIdMenu() {return idMenu;}
    public void setIdMenu(Integer idMenu) {this.idMenu = idMenu;
    }

    @Column(name = "nombre", unique=true)
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    @Column(name = "categoria")
    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}

    @Column(name = "promocion")
    public Boolean getPromocion() {return promocion;}
    public void setPromocion(Boolean promocion) {this.promocion = promocion;}

    @Column(name = "descripcion")
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    @Column(name = "costo")
    public Double getCosto() {return costo;}
    public void setCosto(Double costo) {this.costo = costo;}

    @Column(name = "descuento")
    public Double getDescuento() {return descuento;}
    public void setDescuento(Double descuento) {this.descuento = descuento;}

    @Column(name = "imagen")
    public String getImagen() {return imagen;}
    public void setImagen(String imagen) {this.imagen = imagen;}

    @Column(name = "restaurante")
    public RestauranteEntity getRestaurante() {return restaurante;}
    public void setRestaurante(RestauranteEntity restaurante) {
        this.restaurante = restaurante;}


    public Set<AgregadoEntity> getAgregados() {
        return agregados;
    }

    public  void removeAgregado(AgregadoEntity a){
        this.agregados.remove(a);
        a.getMenus().remove(this);
    }
    public void addAgregado(AgregadoEntity a){
        agregados.add(a);
        a.getMenus().add(this);
    }

    public void setAgregados(Set<AgregadoEntity> agregados) {
        this.agregados = agregados;
    }

    /*Relaciones*/
    @ManyToOne()//orderDetail
    @JoinColumn(name = "restaurante_id", referencedColumnName = "email")
    private RestauranteEntity restaurante;

    /*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "menu_agregados",
            joinColumns = { @JoinColumn(name = "menu_id") },
            inverseJoinColumns = { @JoinColumn(name = "agregado_id") })
    private Set<AgregadoEntity> agregados = new HashSet<>();*/

    @ManyToMany(mappedBy = "menus", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<AgregadoEntity> agregados = new HashSet<>();



}


