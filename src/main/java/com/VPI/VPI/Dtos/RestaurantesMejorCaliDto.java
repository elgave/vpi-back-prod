package com.VPI.VPI.Dtos;

public class RestaurantesMejorCaliDto implements Comparable<RestaurantesMejorCaliDto>{

    private String nombreRestaurante;
    private Double calificacion;

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public int compareTo(RestaurantesMejorCaliDto r) {
        return this.getCalificacion().compareTo(r.getCalificacion());
    }


}
