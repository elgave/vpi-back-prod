package com.VPI.VPI.Services;

import com.VPI.VPI.Entities.UsuarioEntity;


import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<UsuarioEntity> getAllUsuarios();
    UsuarioEntity createUsuario(UsuarioEntity usuario);
    boolean existsByEmail(String email);
    Optional<UsuarioEntity> findByEmail(String email);
    Optional<UsuarioEntity> findByPassWordTemporal(String passWordTemporal);
    Void setpasswordTemporal(String email, String pass, String rol);
    Void changePassword(String email, String pass);

}
