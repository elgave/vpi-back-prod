package com.VPI.VPI.Repositories;

import com.VPI.VPI.Entities.CalificacionEntity;
import com.VPI.VPI.Entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMenuRepository extends JpaRepository<MenuEntity, Integer> {

   @Query("select count(*) FROM MenuEntity m JOIN RestauranteEntity r on m.restaurante=r.email where m.nombre = :nombre and r.email=:restaurante")
   Integer cantMenu(@Param("nombre")String nombre, @Param("restaurante")String restaurante);

   @Query("select m FROM MenuEntity m JOIN RestauranteEntity r on m.restaurante=r.email where  r.nombreRestaurante=:restaurante")
   List<MenuEntity> menusRestaurante(@Param("restaurante")String restaurante);
   @Query("select m FROM MenuEntity m JOIN RestauranteEntity r on m.restaurante=r.email where  r.email=:restaurante")
   List<MenuEntity> menusRestauranteEmail(@Param("restaurante")String idRestaurante);

   Optional<MenuEntity> findByIdMenu(Integer idMenu);

   @Query("select m FROM MenuEntity m JOIN RestauranteEntity r on m.restaurante=r.email where r.email=:restaurante And m.categoria=:cat")
   List<MenuEntity> menusRestauranteCategoria(@Param("restaurante")String restaurante,@Param("cat") MenuEntity.Categoria cat);

   @Query("select count(m) FROM MenuEntity m where m.categoria=:cat")
   Integer cantMenuXCat(@Param("cat") MenuEntity.Categoria cat);



}


