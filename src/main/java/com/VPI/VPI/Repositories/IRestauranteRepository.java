package com.VPI.VPI.Repositories;

import com.VPI.VPI.Entities.CalificacionEntity;
import com.VPI.VPI.Entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRestauranteRepository extends JpaRepository<RestauranteEntity, String> {

   Optional<RestauranteEntity> findByEmail(String email);
   Boolean existsByNombreRestaurante(String  nombreRestaurante);
   Boolean existsByNroHabilitacion(String  nroHabilitacion);
   List<RestauranteEntity> findByConfirmadoTrueAndAbiertoTrueAndBloqueadoFalse();
   List<RestauranteEntity> findByConfirmado(Boolean confirmado);
   void deleteById(String email);
   Optional<RestauranteEntity> findByNombreRestaurante(String nombreRestaurante);
   List<RestauranteEntity> findByBloqueadoTrueAndEmailIgnoreCaseContainingOrBloqueadoTrueAndNombreRestauranteIgnoreCaseContainingOrBloqueadoTrueAndCelularIgnoreCaseContaining(String busqueda,String busqueda2,String busqueda3);
   List<RestauranteEntity> findByBloqueadoFalseAndEmailIgnoreCaseContainingOrBloqueadoFalseAndNombreRestauranteIgnoreCaseContainingOrBloqueadoFalseAndCelularIgnoreCaseContaining(String busqueda,String busqueda2,String busqueda3);

   List<RestauranteEntity> findByConfirmadoTrueAndAbiertoTrueAndBloqueadoFalseAndEmailIgnoreCaseContainingOrConfirmadoTrueAndAbiertoTrueAndBloqueadoFalseAndNombreRestauranteIgnoreCaseContainingOrConfirmadoTrueAndAbiertoTrueAndBloqueadoFalseAndCelularIgnoreCaseContaining(String busqueda,String busqueda2,String busqueda3);
   @Query("select c from CalificacionEntity c where c.isCliente=true and   c.restaurante_id = :restaurante")
   List<CalificacionEntity> obtenerMisCalificaciones(@Param("restaurante")String restaurante);

   @Query("select r.abierto from RestauranteEntity r where r.email = :restaurante")
   Boolean isAbierto(@Param("restaurante")String restaurante);

   @Query("select Distinct r FROM RestauranteEntity r JOIN MenuEntity m on r.email=m.restaurante where m.promocion=true")
   List<RestauranteEntity> restaurantesConPromo();

   @Query("select Distinct r FROM RestauranteEntity r JOIN FavoritoEntity f on r.email=f.restaurante_id where f.cliente_id=:cliente")
   List<RestauranteEntity> restaurantesFavoritos(@Param("cliente")String idCliente);

   List<RestauranteEntity> findByConfirmadoTrue();




}


