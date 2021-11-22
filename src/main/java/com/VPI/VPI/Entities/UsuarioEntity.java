package com.VPI.VPI.Entities;


import javax.persistence.*;
import java.util.Date;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    private String email;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private String passWord;
    private String passwordTemporal;
    private Date fecha;


    public enum Rol {
        Cliente,Admin, Restaurante;
    }

    public UsuarioEntity() {

    }

    public UsuarioEntity(String email, Rol rol, String passWord, String passwordTemporal, Date fecha) {
        this.email = email;
        this.rol = rol;
        this.passWord = passWord;
        this.passwordTemporal = passwordTemporal;
        this.fecha = fecha;
    }


    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email; }

    @Column(name = "rol")
    public Rol getRol() {return rol;}
    public void setRol(Rol rol) {
        this.rol = rol; }

    @Column(name = "password", nullable = false)
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Column(name = "passwordTemporal")
    public String getPasswordTemporal() {
        return passwordTemporal;
    }
    public void setPasswordTemporal(String passwordTemporal) {
        this.passwordTemporal = passwordTemporal;
    }

    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {
        this.fecha = fecha;}


    
 
}