package com.VPI.VPI.Services;

import com.VPI.VPI.Dtos.NuevoAdminDto;
import com.VPI.VPI.Dtos.RestauranteDto;
import com.VPI.VPI.Entities.AdminEntity;

import java.util.List;
import java.util.Optional;

public interface IAdminService {
    List<AdminEntity> getAllAdmins();
    AdminEntity createAdmin(NuevoAdminDto admin);
    AdminEntity createAdminAFuego( );
    Optional<AdminEntity> findByEmail(String email);
    void aprobarRestarurante(String id);
    void bloquearRestaurante(String idRestaurante, String motivo);
    void bloquearCliente(String idCliente,String motivo);
    void eliminarUsuario(Boolean isCliente, String isUsuario);
    void calificacionVPI(String idRestaurante);
    Integer cantPedidos(String idRestautante);
    List<RestauranteDto> getRestaurantes();



}
