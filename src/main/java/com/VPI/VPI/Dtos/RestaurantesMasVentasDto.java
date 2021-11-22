package com.VPI.VPI.Dtos;

public class RestaurantesMasVentasDto implements Comparable<RestaurantesMasVentasDto> {

    private String nombreRestaurante;
    private Integer cantidadVentas;
    private String foto;
    private Boolean abierto;

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public Integer getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(Integer cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getAbierto() {
        return abierto;
    }

    public void setAbierto(Boolean abierto) {
        this.abierto = abierto;
    }

    @Override
    public int compareTo(RestaurantesMasVentasDto r) {
        return this.getCantidadVentas().compareTo(r.getCantidadVentas());
    }
}
