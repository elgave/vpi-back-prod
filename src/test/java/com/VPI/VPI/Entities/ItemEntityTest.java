package com.VPI.VPI.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ItemEntityTest {

    @Test
    void getSetIdItem() {
        ItemEntity item = new ItemEntity();
        item.setIdItem(1);
        Integer id = item.getIdItem();
        Assertions.assertEquals(1, id);
    }

    @Test
    void getSetCantidad() {
        ItemEntity item = new ItemEntity();
        item.setCantidad(2);
        Integer cant = item.getCantidad();
        Assertions.assertEquals(2, cant);
    }

    @Test
    void getSetPrecio() {
        ItemEntity item = new ItemEntity();
        item.setPrecio(100.0);
        Double precio = item.getPrecio();
        Assertions.assertEquals(100.0, precio);
    }

    @Test
    void getSetNombre() {
        ItemEntity item = new ItemEntity();
        item.setNombre("nombre");
        String nombre = item.getNombre();
        Assertions.assertEquals("nombre", nombre);
    }

    @Test
    void getPedido() {
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

        ItemEntity item = new ItemEntity();
        item.setPedido(pedido);
        PedidoEntity pedido2 = item.getPedido();
        Assertions.assertEquals(pedido, pedido2);
    }

}