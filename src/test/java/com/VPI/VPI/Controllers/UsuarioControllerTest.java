package com.VPI.VPI.Controllers;

import com.VPI.VPI.Entities.UsuarioEntity;
import com.VPI.VPI.Services.UsuarioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.*;

@RunWith(MockitoJUnitRunner.class)
class UsuarioControllerTest {

    @InjectMocks
    UsuarioController usuarioController;

    @Mock
    UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void clientePage() {
        String resultado = "<h1> Welcome Cliente</h1>";
        String resultado2 = usuarioController.ClientePage();
        Assertions.assertEquals(resultado, resultado2);
    }

    @Test
    void adminPage() {
        String resultado = "<h1> Welcome Admin</h1>";
        String resultado2 = usuarioController.AdminPage();
        Assertions.assertEquals(resultado, resultado2);
    }

    @Test
    void homePage() {
        String resultado = "<h1> Home</h1>";
        String resultado2 = usuarioController.HomePage();
        Assertions.assertEquals(resultado, resultado2);
    }

    @Test
    void getAllUsuarios() {
        List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setEmail("email@gmail.com");
        usuarioEntity.setRol(UsuarioEntity.Rol.Cliente);
        usuarioEntity.setPasswordTemporal("1234");
        Date fecha = new Date();
        usuarioEntity.setFecha(fecha);

        UsuarioEntity usuarioEntity2 = new UsuarioEntity();
        usuarioEntity2.setEmail("email2@gmail.com");
        usuarioEntity2.setRol(UsuarioEntity.Rol.Cliente);
        usuarioEntity2.setPasswordTemporal("1234");
        Date fecha2 = new Date();
        usuarioEntity2.setFecha(fecha2);

        lista.add(usuarioEntity);
        lista.add(usuarioEntity2);

        Mockito.when(usuarioService.getAllUsuarios()).thenReturn(lista);

        List<UsuarioEntity> lista2 = usuarioController.getAllUsuarios();

        Assertions.assertEquals(lista, lista2);
    }
}