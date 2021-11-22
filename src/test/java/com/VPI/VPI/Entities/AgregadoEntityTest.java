package com.VPI.VPI.Entities;

import com.VPI.VPI.Controllers.ClienteController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class AgregadoEntityTest {

    @InjectMocks
    AgregadoEntity agregadoEntity;


    @Test
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSetId() {
        AgregadoEntity agregado= new AgregadoEntity();
        agregado.setId(1);
        Integer id = agregado.getId();
        Assertions.assertEquals(1, id);
    }
    @Test
    void getSetNombre() {
         AgregadoEntity agregado= new AgregadoEntity();
         agregado.setNombre("prueba");
         String nombre = agregado.getNombre();
         Assertions.assertEquals("prueba", nombre);
    }

    @Test
    void getSetCosto() {
        AgregadoEntity agregado= new AgregadoEntity();
        agregado.setCosto(100.0);
        Double costo = agregado.getCosto();
        Assertions.assertEquals(100.0, costo);
    }

    @Test
    void getSetMenu() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setNombreRestaurante("resturante");
        restaurante.setConfirmado(true);
        restaurante.setCelular("0999");
        restaurante.setBloqueado(false);
        restaurante.setDireccion("casa2");
        restaurante.setFoto("url");
        restaurante.setAbierto(true);
        restaurante.setDescripcionMenues("descripciones");
        restaurante.setHorario("horario");
        restaurante.setNroHabilitacion("3434");
        restaurante.setRazonSocial("razon social");
        restaurante.setPrecioEnvio(45);
        restaurante.setEmail("restaurante@gmail.com");

        MenuEntity menu = new MenuEntity();
        menu.setIdMenu(1);
        menu.setPromocion(false);
        menu.setDescripcion("nada");
        menu.setCategoria(MenuEntity.Categoria.Hamburguesas);
        menu.setRestaurante(restaurante);
        menu.setNombre("Cuarto de libra");
        Set<MenuEntity> menus = new HashSet<>();
        menus.add(menu);

        AgregadoEntity agregado= new AgregadoEntity();
        agregado.setMenus(menus);
        Set<MenuEntity> menus2 = new HashSet<>();
        menus2 = agregado.getMenus();
        Assertions.assertEquals(menus, menus2);
    }

    @Test
    void getSetRestaurante() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setNombreRestaurante("resturante");
        restaurante.setConfirmado(true);
        restaurante.setCelular("0999");
        restaurante.setBloqueado(false);
        restaurante.setDireccion("casa2");
        restaurante.setFoto("url");
        restaurante.setAbierto(true);
        restaurante.setDescripcionMenues("descripciones");
        restaurante.setHorario("horario");
        restaurante.setNroHabilitacion("3434");
        restaurante.setRazonSocial("razon social");
        restaurante.setPrecioEnvio(45);
        restaurante.setEmail("restaurante@gmail.com");

        AgregadoEntity agregado= new AgregadoEntity();
        agregado.setRestaurante(restaurante);
        RestauranteEntity resturante2 = agregado.getRestaurante();
        Assertions.assertEquals(restaurante, resturante2);
    }
}