package com.VPI.VPI.Dtos;

import com.VPI.VPI.Entities.PedidoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class PedidoDto {


    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    private Integer idPedido;
    private String cliente;
    private String restaurante;
    private Boolean pagoOnline;
    private Boolean pagoAcreditado;
    private String comentario;
    private Double precioTotal;
    private List<AltaItemDto> items;
    private PedidoEntity.EstadoPedido estado;
    private String comentarioEstado;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date fecha;
    private Integer tiempoE;
    private String direccion;
    private List<ReclamoDto> reclamos;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public Boolean getPagoOnline() {
        return pagoOnline;
    }

    public void setPagoOnline(Boolean pagoOnline) {
        this.pagoOnline = pagoOnline;
    }

    public Boolean getPagoAcreditado() {
        return pagoAcreditado;
    }

    public void setPagoAcreditado(Boolean pagoAcreditado) {
        this.pagoAcreditado = pagoAcreditado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public List<AltaItemDto> getItems() {
        return items;
    }

    public void setItems(List<AltaItemDto> items) {
        this.items = items;
    }

    public PedidoEntity.EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(PedidoEntity.EstadoPedido estado) {
        this.estado = estado;
    }

    public String getComentarioEstado() {
        return comentarioEstado;
    }

    public void setComentarioEstado(String comentarioEstado) {
        this.comentarioEstado = comentarioEstado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getTiempoE() {
        return tiempoE;
    }

    public void setTiempoE(Integer tiempoE) {
        this.tiempoE = tiempoE;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<ReclamoDto> getReclamos() {
        return reclamos;
    }

    public void setReclamos(List<ReclamoDto> reclamos) {
        this.reclamos = reclamos;
    }
}
