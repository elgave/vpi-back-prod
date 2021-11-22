package com.VPI.VPI.Entities;

import com.VPI.VPI.Dtos.ReclamoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PedidoEntityTest {
    Date fecha = new Date(System.currentTimeMillis());
    @Test
    void getSetIdPedido() {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setIdPedido(1);
        Integer id = pedido.getIdPedido();
        Assertions.assertEquals(1, id);
    }

    @Test
    void getSetPagoOnline() {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setPagoOnline(true);
        Boolean pago = pedido.getPagoOnline();
        Assertions.assertEquals(true, pago);
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

        PedidoEntity pedido = new PedidoEntity();
        pedido.setRestaurante(restaurante);
        RestauranteEntity restaurante2 = pedido.getRestaurante();
        Assertions.assertEquals(restaurante, restaurante2);
    }

    @Test
    void getSetEstado() {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setEstado(PedidoEntity.EstadoPedido.Pendiente);
        PedidoEntity.EstadoPedido estado = pedido.getEstado();
        Assertions.assertEquals(PedidoEntity.EstadoPedido.Pendiente, estado);
    }

    @Test
    void getSetPagoAcreditado() {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setPagoAcreditado(true);
        Boolean pago = pedido.getPagoAcreditado();
        Assertions.assertEquals(true, pago);
    }

    @Test
    void getSetComentario() {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setComentario("comentario");
        String comentario = pedido.getComentario();
        Assertions.assertEquals("comentario", comentario);
    }

    @Test
    void getSetComentarioEstado() {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setComentarioEstado("comentario");
        String comentario = pedido.getComentarioEstado();
        Assertions.assertEquals("comentario", comentario);
    }

    @Test
    void getSetPrecioTotal() {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setPrecioTotal(100.0);
        Double precio = pedido.getPrecioTotal();
        Assertions.assertEquals(100.0, precio);
    }

    @Test
    void getFecha() {
        PedidoEntity pedido = new PedidoEntity();
        Date fecha = new Date();
        pedido.setFecha(fecha);
        Date fecha2 = pedido.getFecha();
        Assertions.assertEquals(fecha, fecha2);
    }

    @Test
    void getSetTiempoE() {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setTiempoE(23);
        Integer tiempoe = pedido.getTiempoE();
        Assertions.assertEquals(23, tiempoe);
    }

    @Test
    void getSetReclamo() {
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

        ReclamoEntity reclamo = new ReclamoEntity();
        reclamo.setPedido(pedido);
        reclamo.setIdReclamo(1);
        reclamo.setEstado(ReclamoEntity.EstadoReclamo.Pendiente);
        reclamo.setComentario("hola");
        reclamo.setFecha(fecha);
        reclamo.setTipo(ReclamoEntity.TipoReclamo.Reembolso);
        Set<ReclamoEntity> reclamos = new HashSet<ReclamoEntity>();
        reclamos.add(reclamo);

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setReclamo(reclamos);
        Set<ReclamoEntity> reclamoResultados = pedido.getReclamo();
    }

    @Test
    void getSetCliente() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setEmail("cliente@gmail.com");
        cliente.setRol(UsuarioEntity.Rol.Cliente);
        cliente.setPassWord("123");
        cliente.setBloqueado(false);
        cliente.setNombre("Cliente");
        cliente.setApellido("Apellido");
        cliente.setCelular("09999");
        cliente.setFoto("url");

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setCliente(cliente);
        ClienteEntity cliente2 = pedidoEntity.getCliente();
        Assertions.assertEquals(cliente, cliente2);
    }

    @Test
    void getSetItem() {
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

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setPedido(pedido);
        itemEntity.setNombre("Hamburguesa");
        itemEntity.setIdItem(1);
        itemEntity.setCantidad(1);
        itemEntity.setPrecio(200.0);
        Set<ItemEntity> items = new HashSet<ItemEntity>();
        items.add(itemEntity);

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setItem(items);
        Set<ItemEntity> items2 = pedidoEntity.getItem();
        Assertions.assertEquals(items, items2);
    }


    @Test
    void addItem() {
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

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setPedido(pedido);
        itemEntity.setNombre("Hamburguesa");
        itemEntity.setIdItem(1);
        itemEntity.setCantidad(1);
        itemEntity.setPrecio(200.0);

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.addItem(itemEntity);
        Set<ItemEntity> items2 = pedidoEntity.getItem();

        Assertions.assertEquals(1, items2.size());
    }

    @Test
    void removeItem() {
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

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setPedido(pedido);
        itemEntity.setNombre("Hamburguesa");
        itemEntity.setIdItem(1);
        itemEntity.setCantidad(1);
        itemEntity.setPrecio(200.0);

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.addItem(itemEntity);
        Set<ItemEntity> items2 = pedidoEntity.getItem();
        Assertions.assertEquals(1, items2.size());
        pedidoEntity.removeItem(itemEntity);
        Assertions.assertEquals(0, items2.size());
    }

    @Test
    void getSetItems(){
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

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setPedido(pedido);
        itemEntity.setNombre("Hamburguesa");
        itemEntity.setIdItem(1);
        itemEntity.setCantidad(1);
        itemEntity.setPrecio(200.0);
        Set<ItemEntity> items = new HashSet<ItemEntity>();
        items.add(itemEntity);

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setItems(items);
        Set<ItemEntity> items2 = pedidoEntity.getItems();
        Assertions.assertEquals(items, items2);
    }

    @Test
    void getSetClienteEmail(){
        ClienteEntity cliente = new ClienteEntity();
        cliente.setEmail("cliente@gmail.com");
        cliente.setRol(UsuarioEntity.Rol.Cliente);
        cliente.setPassWord("123");
        cliente.setBloqueado(false);
        cliente.setNombre("Cliente");
        cliente.setApellido("Apellido");
        cliente.setCelular("09999");
        cliente.setFoto("url");

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setCliente(cliente);
        String email = pedidoEntity.getClienteEmail();
        Assertions.assertEquals("cliente@gmail.com", email);
    }

    @Test
    void getSetDireccion(){
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setDireccion("direccion");
        String dire = pedidoEntity.getDireccion();
        Assertions.assertEquals("direccion", dire);
    }

}
