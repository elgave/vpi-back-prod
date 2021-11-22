package com.VPI.VPI.Dtos;

import com.VPI.VPI.Entities.ReclamoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ReclamoDto {

    private Integer idReclamo;
    private Integer pedido;
    private String estado;
    private ReclamoEntity.TipoReclamo tipo;
    private String comentario;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date fecha;



    public Integer getPedido() {
        return pedido;
    }

    public void setPedido(Integer pedido) {
        this.pedido = pedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ReclamoEntity.TipoReclamo getTipo() {
        return tipo;
    }

    public void setTipo(ReclamoEntity.TipoReclamo tipo) {
        this.tipo = tipo;
    }

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }
}