package com.VPI.VPI.Repositories;

import com.VPI.VPI.Entities.AgregadoEntity;
import com.VPI.VPI.Entities.MenuEntity;
import com.VPI.VPI.Entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAgregadoRepository extends JpaRepository<AgregadoEntity, Integer> {

    List<AgregadoEntity> findByMenus(MenuEntity menus);
    List<AgregadoEntity> findByRestaurante(RestauranteEntity restaurante);
    List<AgregadoEntity> findByRestauranteAndIdNotIn(RestauranteEntity restaurante,  List<Integer> idAgregados );
    Boolean existsByRestauranteAndNombre(RestauranteEntity restaurante, String nombreAgregado);

}


