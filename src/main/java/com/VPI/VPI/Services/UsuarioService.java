package com.VPI.VPI.Services;


import com.VPI.VPI.Dtos.NewUserView;
import com.VPI.VPI.Entities.AdminEntity;
import com.VPI.VPI.Entities.ClienteEntity;
import com.VPI.VPI.Entities.RestauranteEntity;
import com.VPI.VPI.Entities.UsuarioEntity;
import com.VPI.VPI.Repositories.IAdminRepository;
import com.VPI.VPI.Repositories.IClienteRepository;
import com.VPI.VPI.Repositories.IRestauranteRepository;
import com.VPI.VPI.Repositories.IUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UsuarioService implements IUsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IRestauranteRepository restauranteRepository;
    @Autowired
    private IAdminRepository adminRepository;



    private UsuarioEntity mapToEntity(NewUserView newUserView)  {
        UsuarioEntity usuarioEntity = modelMapper.map(newUserView, UsuarioEntity.class);
        return usuarioEntity;
    }

    public List<UsuarioEntity> getAllUsuarios() {

        return usuarioRepository.findAll();
    }


    public UsuarioEntity createUsuario(UsuarioEntity usuario) {
        String pass = usuario.getPassWord();
        String passEncript = passwordEncoder.encode(pass);

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setPassWord(passEncript);
        usuarioEntity.setRol(usuario.getRol());

        return usuarioRepository.save(usuarioEntity);
    }


    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public Optional<UsuarioEntity> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Optional<UsuarioEntity> findByPassWordTemporal(String passWordTemporal) {
        return usuarioRepository.findByPasswordTemporal(passWordTemporal);
    }

    @Override
    public Void setpasswordTemporal(String email, String pass, String rol) {
        if(rol== UsuarioEntity.Rol.Cliente.toString()){
            Optional<ClienteEntity> clienteOpt = clienteRepository.findByEmail(email);
            ClienteEntity cliente = clienteOpt.get();
            cliente.setPasswordTemporal(pass);
            clienteRepository.save(cliente);
        }
        if(rol== UsuarioEntity.Rol.Restaurante.toString()){
            Optional<RestauranteEntity> restauranteOpt = restauranteRepository.findByEmail(email);
            RestauranteEntity restaurante = restauranteOpt.get();
            restaurante.setPasswordTemporal(pass);
            restauranteRepository.save(restaurante);
        }if(rol== UsuarioEntity.Rol.Admin.toString()){
            Optional<AdminEntity> adminOpt = adminRepository.findByEmail(email);
            AdminEntity admin = adminOpt.get();
            admin.setPasswordTemporal(pass);
            adminRepository.save(admin);
        }
        return null;
    }

    @Override
    public Void changePassword(String passTemporal, String pass) {
        Optional<UsuarioEntity>  usuarioOpt = usuarioRepository.findByPasswordTemporal(passTemporal);
        UsuarioEntity usuario = usuarioOpt.get();
        if(usuario.getRol().toString()== UsuarioEntity.Rol.Cliente.toString()){
            Optional<ClienteEntity> clienteOpt = clienteRepository.findByEmail(usuario.getEmail());

            ClienteEntity cliente = clienteOpt.get();
            String passEncript = passwordEncoder.encode(pass);
            cliente.setPassWord(passEncript);
            cliente.setPasswordTemporal(null);
            clienteRepository.save(cliente);
        }
        if(usuario.getRol().toString()== UsuarioEntity.Rol.Admin.toString()){
            Optional<AdminEntity> adminOpt = adminRepository.findByEmail(usuario.getEmail());
            AdminEntity admin = adminOpt.get();
            String passEncript = passwordEncoder.encode(pass);
            admin.setPassWord(passEncript);
            admin.setPasswordTemporal(null);
            adminRepository.save(admin);
        }
        if(usuario.getRol().toString()== UsuarioEntity.Rol.Restaurante.toString()){
            Optional<RestauranteEntity> restOpt = restauranteRepository.findByEmail(usuario.getEmail());
            RestauranteEntity restaurante = restOpt.get();
            String passEncript = passwordEncoder.encode(pass);
            restaurante.setPassWord(passEncript);
            restaurante.setPasswordTemporal(null);
            restauranteRepository.save(restaurante);
        }
        return null;
    }


   

    
}