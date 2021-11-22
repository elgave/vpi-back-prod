package com.VPI.VPI.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DireccionEntityTest {

    @Test
    void Setup() {
    }

    @Test
    void setGetId() {
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setId(1);
        Integer id = direccionEntity.getId();
        Assertions.assertEquals(1, id);
    }

    @Test
    void getSetNombre() {
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setNombre("casa");
        String nombre = direccionEntity.getNombre();
        Assertions.assertEquals("casa", nombre);
    }

    @Test
    void getSetPrincipal() {
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setPrincipal(true);
        Boolean princial = direccionEntity.getPrincipal();
        Assertions.assertEquals(true, princial);
    }

    @Test
    void getSetCalle() {
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setCalle("calle");
        String calle = direccionEntity.getCalle();
        Assertions.assertEquals("calle", calle);
    }

    @Test
    void getSetEsquina() {
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setEsquina("esquina");
        String esquina = direccionEntity.getEsquina();
        Assertions.assertEquals("esquina", esquina);
    }

    @Test
    void getSetNumero() {
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setNumero("123");
        String numero = direccionEntity.getNumero();
        Assertions.assertEquals("123", numero);
    }
    @Test
    void getSetApto() {
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setApto("apto");
        String apto = direccionEntity.getApto();
        Assertions.assertEquals("apto", apto);
    }

    @Test
    void getSetBarrio() {
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setBarrio("barrio");
        String barrio= direccionEntity.getBarrio();
        Assertions.assertEquals("barrio", barrio);
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

        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setCliente(cliente);
        ClienteEntity cliente2 = direccionEntity.getCliente();
        Assertions.assertEquals(cliente, cliente2);
    }
}