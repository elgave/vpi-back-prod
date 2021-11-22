package com.VPI.VPI.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalificacionEntityTest {

    @Test
    void setup(){
    }

    @Test
    void getId() {
        CalificacionEntity CalificacionTest= new CalificacionEntity(123, 5, true, "idC", "idR");
        CalificacionTest.setId(1);
        int id= CalificacionTest.getId();
        Assertions.assertEquals(1, id);
    }

    @Test
    void getSetPuntuacion() {
        CalificacionEntity CalificacionTest= new CalificacionEntity(123, 5, true, "idC", "idR");
        CalificacionTest.setPuntuacion(5);
        int puntuacion= CalificacionTest.getPuntuacion();
        Assertions.assertEquals(5, puntuacion);
    }

    @Test
    void isCliente() {
        CalificacionEntity CalificacionTest= new CalificacionEntity(123, 5, true, "idC", "idR");
        CalificacionTest.setCliente(true);
        Boolean isCliente = CalificacionTest.isCliente();
        Assertions.assertEquals(true, isCliente);
    }

    @Test
    void getSetCliente_id() {
        CalificacionEntity CalificacionTest= new CalificacionEntity(123, 5, true, "idC", "idR");
        CalificacionTest.setCliente_id("1");;
        String id = CalificacionTest.getCliente_id();
        Assertions.assertEquals("1", id);
    }

    @Test
    void getSetRestaurante_id() {
        CalificacionEntity CalificacionTest= new CalificacionEntity(123, 5, true, "idC", "idR");
        CalificacionTest.setRestaurante_id("1");;
        String id = CalificacionTest.getRestaurante_id();
        Assertions.assertEquals("1", id);
    }
}