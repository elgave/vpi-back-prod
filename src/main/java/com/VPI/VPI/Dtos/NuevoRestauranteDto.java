package com.VPI.VPI.Dtos;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NuevoRestauranteDto {
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String nombreRestaurante;
    @NotBlank
    private String nroHabilitacion;
    @NotBlank
    private String razonSocial;
    @NotBlank
    private String rut;
    @NotBlank
    private String direccion;
    @NotBlank
    private String descripcionMenues;
    @NotBlank
    private String celular;

    private Integer precioEnvio;
    private String horario;
    private String urlFoto;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getNroHabilitacion() {
        return nroHabilitacion;
    }

    public void setNroHabilitacion(String nroHabilitacion) {
        this.nroHabilitacion = nroHabilitacion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcionMenues() {
        return descripcionMenues;
    }

    public void setDescripcionMenues(String descripcionMenues) {
        this.descripcionMenues = descripcionMenues;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getPrecioEnvio() {
        return precioEnvio;
    }

    public void setPrecioEnvio(Integer precioEnvio) {
        this.precioEnvio = precioEnvio;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
