package com.VPI.VPI.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteEntityTest {

    @Test
    void getSetNombreRestaurante() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setNombreRestaurante("nombre");
        String nombre = restaurante.getNombreRestaurante();
        Assertions.assertEquals("nombre", nombre);
    }

    @Test
    void getSetNroHabilitacion() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setNroHabilitacion("123");
        String nro = restaurante.getNroHabilitacion();
        Assertions.assertEquals("123", nro);
    }
    @Test
    void getSetRazonSocial() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setRazonSocial("razon");
        String razon = restaurante.getRazonSocial();
        Assertions.assertEquals("razon", razon);
    }

    @Test
    void getSetRut() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setRut("rut");
        String rut = restaurante.getRut();
        Assertions.assertEquals("rut", rut);
    }

    @Test
    void getSetAbierto() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setAbierto(false);
        Boolean abierto = restaurante.getAbierto();
        Assertions.assertEquals(false, abierto);
    }

    @Test
    void getSetDireccion() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setDireccion("direccion");
        String direccion = restaurante.getDireccion();
        Assertions.assertEquals("direccion", direccion);
    }

    @Test
    void getSetDescripcionMenues() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setDescripcionMenues("desc");
        String desc = restaurante.getDescripcionMenues();
        Assertions.assertEquals("desc", desc);
    }

    @Test
    void getSetConfirmado() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setConfirmado(true);
        Boolean confrimado = restaurante.getConfirmado();
        Assertions.assertEquals(true, confrimado);
    }

    @Test
    void getBloqueado() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setBloqueado(false);
        Boolean bloqueado = restaurante.getBloqueado();
        Assertions.assertEquals(false, bloqueado);
    }

    @Test
    void getSetCelular() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setCelular("098765434");
        String celular = restaurante.getCelular();
        Assertions.assertEquals("098765434", celular);
    }

    @Test
    void getSetFoto() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setFoto("url");
        String foto = restaurante.getFoto();
        Assertions.assertEquals("url", foto);
    }

    @Test
    void getSetPrecioEnvio() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setPrecioEnvio(200);
        Integer precio = restaurante.getPrecioEnvio();
        Assertions.assertEquals(200, precio);
    }

    @Test
    void getSetHorario() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setHorario("horario");
        String horario = restaurante.getHorario();
        Assertions.assertEquals("horario", horario);
    }

    @Test
    void getSetCalificacionVpi() {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setCalificacionVPI(2.0);
        Double calificacion = restaurante.getCalificacionVPI();
        Assertions.assertEquals(2.0, calificacion);
    }

    @Test
    void getSetMenus() {
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
        Set<MenuEntity> menus = new HashSet<MenuEntity>();
        menus.add(menu);

        RestauranteEntity restaurante2 = new RestauranteEntity();
        restaurante.setMenus(menus);
        Set<MenuEntity> menus2 = restaurante.getMenus();
        Assertions.assertEquals(menus, menus2);
    }

    @Test
    void getSetAgregado() {
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

        RestauranteEntity restaurante2 = new RestauranteEntity();
        restaurante2.setAgregado(agregados);
        Set<AgregadoEntity> agregados2 = restaurante2.getAgregado();
        Assertions.assertEquals(agregados, agregados2);
    }

    @Test
    void getSetPedido() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setEmail("cliente@gmail.com");
        cliente.setRol(UsuarioEntity.Rol.Cliente);
        cliente.setPassWord("123");
        cliente.setBloqueado(false);
        cliente.setNombre("Cliente");
        cliente.setApellido("Apellido");
        cliente.setCelular("09999");
        cliente.setFoto("url");

        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setCliente(cliente);
        direccionEntity.setApto("apto");
        direccionEntity.setBarrio("barrio");
        direccionEntity.setNombre("casa2");
        direccionEntity.setEsquina("esquina");
        direccionEntity.setNumero("111");
        direccionEntity.setCalle("calle");
        direccionEntity.setPrincipal(true);
        direccionEntity.setId(1);

        Set<DireccionEntity> direcciones = new HashSet<DireccionEntity>();
        direcciones.add(direccionEntity);

        cliente.setDireccion(direcciones);

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

        PedidoEntity pedido = new PedidoEntity();
        pedido.setIdPedido(1);
        pedido.setCliente(cliente);
        pedido.setDireccion("casa2");
        pedido.setEstado(PedidoEntity.EstadoPedido.Pendiente);
        pedido.setRestaurante(restaurante);
        pedido.setComentario("comentario");
        Date fecha = new Date();
        pedido.setFecha(fecha);
        pedido.setPagoOnline(true);
        pedido.setPrecioTotal(500.0);
        pedido.setTiempoE(45);
        Set<PedidoEntity> pedidos = new HashSet<PedidoEntity>();
        pedidos.add(pedido);

        RestauranteEntity restaurante2 = new RestauranteEntity();
        restaurante2.setPedido(pedidos);
        Set<PedidoEntity> pedidos2 = restaurante2.getPedido();
        Assertions.assertEquals(pedidos, pedidos2);
    }

}