package com.VPI.VPI.Dtos;

public class ConfirmarPedidoDto {
    private Integer idPedido;
    private Integer tiempoE;

    public ConfirmarPedidoDto(Integer idPedido, Integer tiempoE) {
        this.idPedido = idPedido;
        this.tiempoE = tiempoE;
    }

    public ConfirmarPedidoDto() {}

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getTiempoE() {
        return tiempoE;
    }

    public void setTiempoE(Integer tiempoE) {
        this.tiempoE = tiempoE;
    }
}
