package com.VPI.VPI.Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pedidos")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;
    private Boolean pagoOnline;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    private Boolean pagoAcreditado;
    private String comentario;
    private String comentarioEstado;
    private Double precioTotal;
    private Date fecha;
    private Integer tiempoE;
    private String direccion;

    public enum EstadoPedido {Pendiente, Confirmado,
        Entregado, Rechazado}

    public PedidoEntity() {
    }

    public PedidoEntity(Integer idPedido, Boolean pagoOnline, EstadoPedido estado, Boolean pagoAcreditado, String comentario, String comentarioEstado, Double precioTotal, Date fecha, Integer tiempoE, String direccion, Set<ReclamoEntity> reclamo, ClienteEntity cliente, Set<ItemEntity> items, RestauranteEntity restaurante) {
        this.idPedido = idPedido;
        this.pagoOnline = pagoOnline;
        this.estado = estado;
        this.pagoAcreditado = pagoAcreditado;
        this.comentario = comentario;
        this.comentarioEstado = comentarioEstado;
        this.precioTotal = precioTotal;
        this.fecha = fecha;
        this.tiempoE = tiempoE;
        this.direccion = direccion;
        this.reclamo = reclamo;
        this.cliente = cliente;
        this.items = items;
        this.restaurante = restaurante;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Boolean getPagoOnline() {
        return pagoOnline;
    }

    public RestauranteEntity getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteEntity restaurante) {
        this.restaurante = restaurante;
    }

    public void setPagoOnline(Boolean pagoOnline) {
        this.pagoOnline = pagoOnline;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
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

    public String getComentarioEstado() {
        return comentarioEstado;
    }

    public void setComentarioEstado(String comentarioEstado) {
        this.comentarioEstado = comentarioEstado;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
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

    public Set<ReclamoEntity> getReclamo() {
        return reclamo;
    }

    public void setReclamo(Set<ReclamoEntity> reclamo) {
        this.reclamo = reclamo;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public Set<ItemEntity> getItem() {
        return items;
    }

    public void setItem(Set<ItemEntity> item) {
        this.items = item;
    }

    public Set<ItemEntity> getItems() {
        return items;
    }

    public void setItems(Set<ItemEntity> items) {
        this.items = items;
    }

    public String getClienteEmail(){
        return this.cliente.getEmail();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /*Relaciones*/
    @OneToMany(mappedBy = "pedido")
    private Set<ReclamoEntity> reclamo = new HashSet<>();

    @ManyToOne

    @JoinColumn(name = "idCliente",referencedColumnName = "email")
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Set<ItemEntity> items = new HashSet<>();
    public void addItem(ItemEntity item){
        items.add(item);
        item.setPedido(this);
    }
    public void removeItem(ItemEntity item){
        items.remove(item);
        item.setPedido(null);
    }

    @ManyToOne
    @JoinColumn(name = "idRestaurante",referencedColumnName = "email")
    private RestauranteEntity restaurante;


}
