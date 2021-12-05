package com.VPI.VPI.Repositories;

import com.VPI.VPI.Entities.FavoritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IFavoritoRepository extends JpaRepository<FavoritoEntity, Integer> {

    @Query("select f from FavoritoEntity f where f.cliente_id = :cliente and f.restaurante_id = :restaurante")
    List<FavoritoEntity> obtenerFavoritoCliente(@Param("cliente")String cliente, @Param("restaurante") String restaurante);

    @Transactional
    @Modifying
    @Query("delete from FavoritoEntity f where  f.restaurante_id = :restaurante")
    void borrarFavoritosHaciaRestaurante(@Param("restaurante")String restaurante);

    @Transactional
    @Modifying
    @Query("delete from FavoritoEntity f where  f.cliente_id = :cliente")
    void borrarFavoritosDeCliente(@Param("cliente")String cliente);


}
