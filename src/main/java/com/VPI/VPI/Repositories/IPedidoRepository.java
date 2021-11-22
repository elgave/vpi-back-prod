package com.VPI.VPI.Repositories;

import com.VPI.VPI.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPedidoRepository extends JpaRepository<PedidoEntity, Integer> {

   Optional<PedidoEntity> findByIdPedido(Integer id);
   List<PedidoEntity> findByRestauranteOrderByIdPedidoDesc(RestauranteEntity restaurante);
   List<PedidoEntity> findByClienteOrderByIdPedidoDesc(ClienteEntity cliente);
   List<PedidoEntity> findByRestauranteAndEstadoNotOrderByIdPedidoDesc(RestauranteEntity restauranteEntity, PedidoEntity.EstadoPedido estado);
   List<PedidoEntity> findByRestauranteAndEstadoOrderByIdPedidoDesc(RestauranteEntity restauranteEntity, PedidoEntity.EstadoPedido estado);
   List<PedidoEntity> findByRestauranteAndCliente(RestauranteEntity restauranteEntity, ClienteEntity clienteEntity);

   @Query("select count(*) FROM PedidoEntity p JOIN RestauranteEntity r on p.restaurante=r.email where r.email=:restaurante and p.estado=:estado")
   Integer cantPedidos(@Param("restaurante")String restaurante, @Param("estado") PedidoEntity.EstadoPedido estado);

   @Query("select sum(p.tiempoE) FROM PedidoEntity p JOIN RestauranteEntity r on p.restaurante=r.email where r.email=:restaurante")
   Integer tiempoDeEntregaTotal(@Param("restaurante")String restaurante);

   List<PedidoEntity> findByRestauranteAndEstadoAndFechaLessThanEqualAndFechaGreaterThanEqualAndPagoAcreditadoTrue(RestauranteEntity restaurante, PedidoEntity.EstadoPedido estado, Date fechaHasta, Date fechaDesde);
   List<PedidoEntity> findByRestauranteAndEstadoAndPagoAcreditadoTrue(RestauranteEntity restaurante, PedidoEntity.EstadoPedido estado);
   List<PedidoEntity> findByRestauranteAndEstadoAndPagoAcreditadoTrueAndPagoOnline(RestauranteEntity restaurante, PedidoEntity.EstadoPedido estado, Boolean pagoOnline);
   List<PedidoEntity> findByRestauranteAndEstadoAndFechaLessThanEqualAndFechaGreaterThanEqualAndPagoAcreditadoTrueAndPagoOnline(RestauranteEntity restaurante, PedidoEntity.EstadoPedido estado, Date fechaHasta, Date fechaDesde, Boolean pagoOnline);





}


