package com.VPI.VPI.Repositories;

import com.VPI.VPI.Entities.ClienteEntity;
import com.VPI.VPI.Entities.DireccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDireccionRepository extends JpaRepository<DireccionEntity, Integer> {

    List<DireccionEntity> findByCliente(ClienteEntity clienteEntity);
    @Query("select count(*) FROM DireccionEntity where cliente=:cliente")
    Integer cantDirecciones(@Param("cliente")ClienteEntity cliente);
    boolean existsByClienteAndNombre(ClienteEntity cliente, String nombre);

}
