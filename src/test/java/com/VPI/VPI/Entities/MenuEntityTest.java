package com.VPI.VPI.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class MenuEntityTest {

    @Test
    void getSetIdMenu() {
        MenuEntity menu = new MenuEntity();
        menu.setIdMenu(1);
        Integer id = menu.getIdMenu();
        Assertions.assertEquals(1, id);
    }

    @Test
    void getSetNombre() {
        MenuEntity menu = new MenuEntity();
        menu.setNombre("nombre");
        String nombre = menu.getNombre();
        Assertions.assertEquals("nombre", nombre);
    }

    @Test
    void getSetCategoria() {
        MenuEntity menu = new MenuEntity();
        menu.setCategoria(MenuEntity.Categoria.Hamburguesas);
        MenuEntity.Categoria categoria = menu.getCategoria();
        Assertions.assertEquals(MenuEntity.Categoria.Hamburguesas,categoria);
    }

    @Test
    void getSetPromocion() {
        MenuEntity menu = new MenuEntity();
        menu.setPromocion(false);
        Boolean promocion = menu.getPromocion();
        Assertions.assertEquals(false, promocion);
    }


    @Test
    void getSetDescripcion() {
        MenuEntity menu = new MenuEntity();
        menu.setDescripcion("descripcion");
        String descripcion = menu.getDescripcion();
        Assertions.assertEquals("descripcion", descripcion);
    }

    @Test
    void getSetCosto() {
        MenuEntity menu = new MenuEntity();
        menu.setCosto(200.0);
        Double costo = menu.getCosto();
        Assertions.assertEquals(200.0, costo);
    }

    @Test
    void getSetDescuento() {
        MenuEntity menu = new MenuEntity();
        menu.setDescuento(30.0);
        Double descuento = menu.getDescuento();
        Assertions.assertEquals(30.0, descuento);
    }

    @Test
    void getSetImagen() {
        MenuEntity menu = new MenuEntity();
        menu.setImagen("url");
        String imagen = menu.getImagen();
        Assertions.assertEquals("url", imagen);
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

        MenuEntity menu = new MenuEntity();
        menu.setRestaurante(restaurante);
        RestauranteEntity restaurante2 = menu.getRestaurante();
        Assertions.assertEquals(restaurante, restaurante2);
    }

    @Test
    void getSetAgregados() {
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
        menu.setNombre("menu");
        menu.setCategoria(MenuEntity.Categoria.Hamburguesas);
        menu.setCosto(456.0);
        menu.setDescripcion("des");
        menu.setImagen("url");
        menu.setPromocion(false);
        menu.setIdMenu(1);
        menu.setRestaurante(restaurante);
        Set<MenuEntity> menues = new HashSet<MenuEntity>();
        menues.add(menu);

        AgregadoEntity agregado = new AgregadoEntity();
        agregado.setRestaurante(restaurante);
        agregado.setMenus(menues);
        agregado.setId(1);
        agregado.setCosto(200.0);
        agregado.setNombre("nombre");
        Set<AgregadoEntity> agregados = new HashSet<AgregadoEntity>();
        agregados.add(agregado);

        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setAgregados(agregados);
        Set<AgregadoEntity> agregados2 = menuEntity.getAgregados();
        Assertions.assertEquals(agregados, agregados2);
    }

    @Test
    void removeAgregado() {
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
        menu.setNombre("menu");
        menu.setCategoria(MenuEntity.Categoria.Hamburguesas);
        menu.setCosto(456.0);
        menu.setDescripcion("des");
        menu.setImagen("url");
        menu.setPromocion(false);
        menu.setIdMenu(1);
        menu.setRestaurante(restaurante);
        Set<MenuEntity> menues = new HashSet<MenuEntity>();
        menues.add(menu);

        AgregadoEntity agregado = new AgregadoEntity();
        agregado.setRestaurante(restaurante);
        agregado.setMenus(menues);
        agregado.setId(1);
        agregado.setCosto(200.0);
        agregado.setNombre("nombre");
        Set<AgregadoEntity> agregados = new HashSet<AgregadoEntity>();
        agregados.add(agregado);

        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setAgregados(agregados);
        menuEntity.removeAgregado(agregado);
        Set<AgregadoEntity> agregadosVacio = new HashSet<AgregadoEntity>();
        Set<AgregadoEntity> agregados2 = menuEntity.getAgregados();
        Assertions.assertEquals(agregadosVacio, agregados2);

    }

}