package com.VPI.VPI.Repositories;

import com.VPI.VPI.Entities.AdminEntity;
import com.VPI.VPI.Entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<AdminEntity, String> {

   Optional<AdminEntity> findByEmail(String email);
   Optional<AdminEntity> findByPasswordTemporal(String passwordTemporal);



}


