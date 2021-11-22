package com.VPI.VPI.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FavoritoEntityTest {

    @Test
    void getId() {
        FavoritoEntity favorito = new FavoritoEntity();
        favorito.setId(1);
        Integer id = favorito.getId();
        Assertions.assertEquals(1, id);
    }

    @Test
    void getCliente_id() {
        FavoritoEntity favorito = new FavoritoEntity();
        favorito.setCliente_id("cliente@gmail.com");
        String cliente = favorito.getCliente_id();
        Assertions.assertEquals("cliente@gmail.com", cliente);
    }

    @Test
    void getRestaurante_id() {
        FavoritoEntity favorito = new FavoritoEntity();
        favorito.setRestaurante_id("restaurante@gmail.com");
        String rest = favorito.getRestaurante_id();
        Assertions.assertEquals("restaurante@gmail.com", rest);
    }
}