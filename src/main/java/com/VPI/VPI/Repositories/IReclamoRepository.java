package com.VPI.VPI.Repositories;

import com.VPI.VPI.Entities.PedidoEntity;
import com.VPI.VPI.Entities.ReclamoEntity;
import com.VPI.VPI.Entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IReclamoRepository extends JpaRepository<ReclamoEntity, Integer> {

    Optional<ReclamoEntity> findByIdReclamo(Integer id);
    List<ReclamoEntity> findByPedidoAndFechaLessThanEqualAndFechaGreaterThanEqual(PedidoEntity pedido,Date fechaHasta, Date fechaDesde);
    List<ReclamoEntity> findByPedidoAndEstadoEqualsAndFechaLessThanEqualAndFechaGreaterThanEqual(PedidoEntity pedido, ReclamoEntity.EstadoReclamo estado, Date fechaHasta, Date fechaDesde);
    //List<ReclamoEntity> findByPedidoAndEstadoPendienteAndFechaLessThanEqualAndFechaGreaterThanEqual(PedidoEntity pedido,Date fechaHasta, Date fechaDesde);
    //List<ReclamoEntity> findByPedidoAndEstadoReembolsadoAndFechaLessThanEqualAndFechaGreaterThanEqual(PedidoEntity pedido,Date fechaHasta, Date fechaDesde);
    //List<ReclamoEntity> findByPedidoAndEstadoRechazadoAndFechaLessThanEqualAndFechaGreaterThanEqual(PedidoEntity pedido,Date fechaHasta, Date fechaDesde);
    //List<ReclamoEntity> findByPedidoAndEstadoCompensadoAndFechaLessThanEqualAndFechaGreaterThanEqual(PedidoEntity pedido,Date fechaHasta, Date fechaDesde);
    List<ReclamoEntity> findByEstadoEqualsAndFechaLessThanEqualAndFechaGreaterThanEqual(ReclamoEntity.EstadoReclamo estadoReclamo, Date fechaHasta,Date fechaDesde);
    List<ReclamoEntity> findByFechaLessThanEqualAndFechaGreaterThanEqual(Date fechaHasta, Date fechaDesde);
    @Query("select r FROM ReclamoEntity r JOIN PedidoEntity p on r.pedido=p.idPedido where p.restaurante=:restaurante And r.estado=:estado")
    List<ReclamoEntity> listReclamos(@Param("restaurante") RestauranteEntity restaurante, @Param("estado") ReclamoEntity.EstadoReclamo estado);

    @Query("select count(*) FROM ReclamoEntity r JOIN PedidoEntity p on r.pedido=p.idPedido where p.restaurante= :restaurante ")
    Integer cantReclamos(@Param("restaurante")RestauranteEntity restaurante);

    @Query("select r FROM ReclamoEntity r JOIN PedidoEntity p on r.pedido=p.idPedido where p.restaurante=:restaurante")
    List<ReclamoEntity> listReclamosSinFiltro(@Param("restaurante") RestauranteEntity restaurante);

    List<ReclamoEntity> findByPedido(PedidoEntity p);



}


