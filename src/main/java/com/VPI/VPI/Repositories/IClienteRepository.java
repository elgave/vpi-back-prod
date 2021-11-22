package com.VPI.VPI.Repositories;

import com.VPI.VPI.Entities.CalificacionEntity;
import com.VPI.VPI.Entities.ClienteEntity;
import com.VPI.VPI.Entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteEntity, String> {

   Optional<ClienteEntity> findByEmail(String email);
   Optional<ClienteEntity> findByPasswordTemporal(String passwordTemporal);

   List<ClienteEntity> findByBloqueadoTrueAndEmailIgnoreCaseContainingOrBloqueadoTrueAndNombreIgnoreCaseContainingOrBloqueadoTrueAndCelularIgnoreCaseContaining(String busqueda, String busqueda2, String busqueda3);
   List<ClienteEntity> findByBloqueadoFalseAndEmailIgnoreCaseContainingOrBloqueadoFalseAndNombreIgnoreCaseContainingOrBloqueadoFalseAndCelularIgnoreCaseContaining(String busqueda,String busqueda2,String busqueda3);
   @Query("select c from CalificacionEntity c where c.isCliente=false and   c.cliente_id = :cliente")
   List<CalificacionEntity> obtenerMisCalificaciones(@Param("cliente")String cliente);

   List<ClienteEntity> findByBloqueadoTrue();
   List<ClienteEntity> findByBloqueadoFalse();



}


