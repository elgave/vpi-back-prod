package com.VPI.VPI.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioEntityTest {
    Date fecha = new Date(System.currentTimeMillis());
    @Test
    void getSetEmail() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail("usuario@gmail.com");
        String email = usuario.getEmail();
        Assertions.assertEquals("usuario@gmail.com", email);
    }

    @Test
    void getSetRol() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setRol(UsuarioEntity.Rol.Cliente);
        UsuarioEntity.Rol rol = usuario.getRol();
        Assertions.assertEquals(UsuarioEntity.Rol.Cliente, rol);
    }

    @Test
    void getSetPassWord() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setPassWord("123");
        String passWord = usuario.getPassWord();
        Assertions.assertEquals("123", passWord);
    }

    @Test
    void getSetPasswordTemporal() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setPasswordTemporal("123");
        String passWord = usuario.getPasswordTemporal();
        Assertions.assertEquals("123", passWord);
    }

    @Test
    void getSetFecha() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setFecha(fecha);
        Date fecha2 = usuario.getFecha();
        Assertions.assertEquals(fecha, fecha2);
    }
}