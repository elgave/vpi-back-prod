package com.VPI.VPI.Dtos;

import com.VPI.VPI.Entities.ReclamoEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class BusquedaReclamoDto {

    private Integer idPedido;
    @NotNull
    private ReclamoEntity.EstadoReclamo estadoReclamo;
    private Date fechaDesde;
    private Date fechaHasta;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public ReclamoEntity.EstadoReclamo getEstadoReclamo() {
        return estadoReclamo;
    }

    public void setEstadoReclamo(ReclamoEntity.EstadoReclamo estadoReclamo) {
        this.estadoReclamo = estadoReclamo;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
}
