package com.VPI.VPI.Repositories;

import com.VPI.VPI.Entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, String> {

   Optional<UsuarioEntity> findByEmail(String email);
   Boolean existsByEmail(String  email);
   Optional<UsuarioEntity> findByPasswordTemporal(String passwordTemporal);



}


