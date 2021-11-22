package com.VPI.VPI.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reclamos")
public class ReclamoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReclamo;
    @Enumerated(EnumType.STRING)
    private EstadoReclamo estado;
    @Enumerated(EnumType.STRING)
    private TipoReclamo tipo;
    private String comentario;
    private Date fecha;

    public enum TipoReclamo {Reembolso,
        Compensacion}
    public enum EstadoReclamo {Pendiente,
        Reembolsado, Rechazado,
        Compensado}


    /*Relaciones*/

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPedido",referencedColumnName = "idPedido")
    private PedidoEntity pedido;

    public ReclamoEntity() {
    }

    public ReclamoEntity(Integer idReclamo, EstadoReclamo estado, TipoReclamo tipo, String comentario, Date fecha, PedidoEntity pedido) {
        this.idReclamo = idReclamo;
        this.estado = estado;
        this.tipo = tipo;
        this.comentario = comentario;
        this.fecha = fecha;
        this.pedido = pedido;
    }

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public EstadoReclamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoReclamo estado) {
        this.estado = estado;
    }

    public TipoReclamo getTipo() {
        return tipo;
    }

    public void setTipo(TipoReclamo tipo) {
        this.tipo = tipo;
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

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
}
