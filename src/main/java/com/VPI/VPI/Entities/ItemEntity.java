package com.VPI.VPI.Entities;

import javax.persistence.*;

@Entity
@Table(name="Items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;
    private Integer cantidad;
    private Double precio;
    private String nombre;

    public ItemEntity() {
    }

    public ItemEntity(Integer idItem, Integer cantidad, Double precio, String nombre, PedidoEntity pedido) {
        this.idItem = idItem;
        this.cantidad = cantidad;
        this.precio = precio;
        this.nombre = nombre;
        this.pedido = pedido;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    /*Relaciones*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPedido",referencedColumnName = "idPedido")
    private PedidoEntity pedido;
   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemEntity)) return false;
        return idItem != null && idItem.equals(((ItemEntity) o).getIdItem());
    }*/
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
