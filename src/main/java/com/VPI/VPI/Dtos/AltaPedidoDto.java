package com.VPI.VPI.Dtos;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class AltaPedidoDto {


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
    @NotBlank
    private Boolean pagoAcreditado;
    private String comentario;
    private Double precioTotal;
    private List<AltaItemDto> items;
    private String direccion;



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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
