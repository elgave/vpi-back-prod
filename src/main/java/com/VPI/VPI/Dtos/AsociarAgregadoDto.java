package com.VPI.VPI.Dtos;

import java.util.List;

public class AsociarAgregadoDto {

    private Integer idMenu;
    private List<Integer> idAgregados;


    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public List<Integer> getIdAgregados() {
        return idAgregados;
    }

    public void setIdAgregados(List<Integer> idAgregados) {
        this.idAgregados = idAgregados;
    }
}
