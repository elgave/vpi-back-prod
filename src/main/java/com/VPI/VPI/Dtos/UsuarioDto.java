package com.VPI.VPI.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UsuarioDto {

    private String email;
    private String nombre;
    private String direccion;
    private String celular;
    private Boolean bloqueado;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date fechaCreacion;
    private Double calificacionGlobal;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Double getCalificacionGlobal() {
        return calificacionGlobal;
    }

    public void setCalificacionGlobal(Double calificacionGlobal) {
        this.calificacionGlobal = calificacionGlobal;
    }
}
