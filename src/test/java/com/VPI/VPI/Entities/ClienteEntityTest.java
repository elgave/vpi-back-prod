package com.VPI.VPI.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClienteEntityTest {

    @Test
    void getSetNombre() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNombre("Cliente");
        String nombre = cliente.getNombre();
        Assertions.assertEquals("Cliente", nombre);
    }

    @Test
    void getSetApellido() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setApellido("Apellido");
        String apellido = cliente.getApellido();
        Assertions.assertEquals("Apellido", apellido);
    }

    @Test
    void getSetBloqueado() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setBloqueado(false);
        Boolean bloqueado = cliente.getBloqueado();
        Assertions.assertEquals(false, bloqueado);
    }

    @Test
    void getSetCelular() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setCelular("098309988");
        String celular = cliente.getCelular();
        Assertions.assertEquals("098309988", celular);
    }

    @Test
    void getSetFoto() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setFoto("url");
        String foto = cliente.getFoto();
        Assertions.assertEquals("url", foto);
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
        cliente.setPedido(pedidos);
        Set<PedidoEntity> pedidos2 = cliente.getPedido();
        Assertions.assertEquals(pedidos, pedidos2);
    }

    @Test
    void getSetDireccion() {
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
        Set<DireccionEntity> direcciones2 = cliente.getDireccion();
        Assertions.assertEquals(direcciones, direcciones2);
    }
}