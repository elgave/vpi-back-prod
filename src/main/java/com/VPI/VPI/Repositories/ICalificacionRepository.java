package com.VPI.VPI.Repositories;

import com.VPI.VPI.Entities.CalificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICalificacionRepository extends JpaRepository<CalificacionEntity, Integer> {

    @Query("select c from CalificacionEntity c where c.isCliente=true and   c.cliente_id = :cliente and c.restaurante_id = :restaurante")
    List<CalificacionEntity> obtenerCalificacionCliente(@Param("cliente")String cliente,@Param("restaurante") String restaurante);

    @Query("select c from CalificacionEntity c where c.isCliente=false and   c.cliente_id = :cliente and c.restaurante_id = :restaurante")
    List<CalificacionEntity> obtenerCalificacionRestaurante(@Param("cliente")String cliente,@Param("restaurante") String restaurante);

    @Transactional
    @Modifying
    @Query("delete from CalificacionEntity c where c.isCliente = false and c.cliente_id = :cliente")
    void borrarCalificacionesHaciaCliente(@Param("cliente")String cliente);

    @Transactional
    @Modifying
    @Query("delete from CalificacionEntity c where c.isCliente = true and c.restaurante_id = :restaurante")
    void borrarCalificacionesHaciaRestaurante(@Param("restaurante")String restaurante);

}
