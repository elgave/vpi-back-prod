package com.VPI.VPI.Dtos;

import javax.validation.constraints.NotBlank;

public class AltaItemDto {

    @NotBlank
    private String pedido;
    @NotBlank
    private Integer cantidad;
    @NotBlank
    private Double precio;
    @NotBlank
    private String nombre;

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
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
}
