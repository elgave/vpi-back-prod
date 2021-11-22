package com.VPI.VPI.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReclamoEntityTest {
    Date fecha = new Date(System.currentTimeMillis());
    @Test
    void getSetIdReclamo() {
        ReclamoEntity reclamo = new ReclamoEntity();
        reclamo.setIdReclamo(1);
        Integer id = reclamo.getIdReclamo();
        Assertions.assertEquals(1,id);
    }

    @Test
    void getSetEstado() {
        ReclamoEntity reclamo = new ReclamoEntity();
        reclamo.setEstado(ReclamoEntity.EstadoReclamo.Pendiente);
        ReclamoEntity.EstadoReclamo estado  = reclamo.getEstado();
        Assertions.assertEquals(ReclamoEntity.EstadoReclamo.Pendiente, estado);
    }


    @Test
    void getSetTipo() {
        ReclamoEntity reclamo = new ReclamoEntity();
        reclamo.setTipo(ReclamoEntity.TipoReclamo.Compensacion);
        ReclamoEntity.TipoReclamo tipo  = reclamo.getTipo();
        Assertions.assertEquals(ReclamoEntity.TipoReclamo.Compensacion, tipo);
    }

    @Test
    void getSetComentario() {
        ReclamoEntity reclamo = new ReclamoEntity();
        reclamo.setComentario("comentario");
        String comentario = reclamo.getComentario();
        Assertions.assertEquals("comentario", comentario);
    }

    @Test
    void getSetFecha() {
        ReclamoEntity reclamo = new ReclamoEntity();
        reclamo.setFecha(fecha);
        Date fecha2 = reclamo.getFecha();
        Assertions.assertEquals(fecha, fecha2);
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

        ReclamoEntity reclamo = new ReclamoEntity();
        reclamo.setPedido(pedido);
        PedidoEntity pedido2 = reclamo.getPedido();
        Assertions.assertEquals(pedido, pedido2);
    }

}