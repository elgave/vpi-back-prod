package com.VPI.VPI.Dtos;

import com.VPI.VPI.Entities.MenuEntity;

public class CategoriaDto {

    private MenuEntity.Categoria nombre;

    public MenuEntity.Categoria getNombre() {
        return nombre;
    }

    public void setNombre(MenuEntity.Categoria nombre) {
        this.nombre = nombre;
    }
}
