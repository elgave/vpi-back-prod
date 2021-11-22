package com.VPI.VPI.Services;


import com.VPI.VPI.Dtos.NuevoAdminDto;
import com.VPI.VPI.Dtos.RestauranteDto;
import com.VPI.VPI.Entities.*;
import com.VPI.VPI.Repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service

public class AdminService implements IAdminService {
    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private IRestauranteRepository restauranteRepository;

    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private  EmailService emialService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IRestauranteService restauranteService;
    @Autowired
    private IReclamoRepository reclamoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<AdminEntity> getAllAdmins() {

        return adminRepository.findAll();
    }

    private List<RestauranteDto> mapToDto(List<RestauranteEntity> restauranteEntities) {
        List<RestauranteDto> restauranteDtos = Arrays.asList(modelMapper.map(restauranteEntities, RestauranteDto[].class));
        return restauranteDtos;
    }


    public AdminEntity createAdmin(NuevoAdminDto admin) {
        String pass = admin.getPassword();
        String passEncript = passwordEncoder.encode(pass);

        AdminEntity usuarioEntity = new AdminEntity();
        usuarioEntity.setEmail(admin.getEmail());
        usuarioEntity.setPassWord(passEncript);
        usuarioEntity.setRol(UsuarioEntity.Rol.Admin);
        Date currentSqlDate = new Date(System.currentTimeMillis());

        usuarioEntity.setFecha(currentSqlDate);

        return adminRepository.save(usuarioEntity);
    }

    public AdminEntity createAdminAFuego( ) {
        String pass = "1234";
        String passEncript = passwordEncoder.encode(pass);
        Date currentSqlDate = new Date(System.currentTimeMillis());

        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setEmail("Admin");
        adminEntity.setPassWord(passEncript);
        adminEntity.setRol(UsuarioEntity.Rol.Admin);
        adminEntity.setPasswordTemporal(null);
        adminEntity.setFecha(currentSqlDate);

        return adminRepository.save(adminEntity);
    }

    public void aprobarRestarurante(String id)
    {
        Optional<RestauranteEntity> res = restauranteRepository.findByEmail(id);

        RestauranteEntity restaurante = res.get();

        restaurante.setConfirmado(true);

        restauranteRepository.save(restaurante);

        emialService.sendEmailAprobacion(id);

    }
    @Override
    public Optional<AdminEntity> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public void bloquearRestaurante(String idRestaurante, String motivo){

        Optional<RestauranteEntity> restaurante = restauranteRepository.findByEmail(idRestaurante);
        restaurante.get().setBloqueado(true);
        List<PedidoEntity> pedidos = pedidoRepository.findByRestauranteOrderByIdPedidoDesc(restaurante.get());

        for(PedidoEntity p: pedidos){

            if(p.getEstado().toString().equals("Pendiente")) {
                p.setEstado(PedidoEntity.EstadoPedido.Rechazado);
                pedidoRepository.save(p);
            }
        }

        restauranteRepository.save(restaurante.get());
        emialService.sendEmailBloqueoRestaurante(idRestaurante,motivo);

    }
    public void bloquearCliente(String idCliente, String motivo){

        Optional<ClienteEntity> cliente = clienteRepository.findByEmail(idCliente);
        cliente.get().setBloqueado(true);

        clienteRepository.save(cliente.get());
        emialService.sendEmailBloqueoCliente(idCliente,motivo);

    }

    public void eliminarUsuario(Boolean isCliente, String idUsuario){

        if(isCliente){
            clienteService.eliminarMiCuenta(idUsuario);
        }else{
            restauranteService.eliminarCuenta(idUsuario);
        }
    }

    @Override
    public void calificacionVPI(String idRestaurante) {
        Optional<RestauranteEntity> restauranteOpt = restauranteRepository.findByEmail(idRestaurante);
        RestauranteEntity restaurante = restauranteOpt.get();
        Double cantPedidos = Double.valueOf(pedidoRepository.cantPedidos(idRestaurante, PedidoEntity.EstadoPedido.Confirmado));
        Double cantReclamos = Double.valueOf(reclamoRepository.cantReclamos(restaurante));
        //Integer tiempoEntregaTotal = pedidoRepository.tiempoDeEntregaTotal(idRestaurante);

        List<RestauranteEntity> restauranteEntities = restauranteRepository.findAll();
        Integer max = 0;
        for(RestauranteEntity r: restauranteEntities) {
            if(pedidoRepository.cantPedidos(idRestaurante, PedidoEntity.EstadoPedido.Confirmado) > max){
                max= pedidoRepository.cantPedidos(idRestaurante, PedidoEntity.EstadoPedido.Confirmado);
            }

        }

        Double maxPedidos = Double.valueOf(max);
        Double calificacion;

        if(cantPedidos == 0.0 || ((cantPedidos/maxPedidos)<(cantReclamos/cantPedidos))){
            calificacion = Double.valueOf(0);
        }else{
            Double c = 5*(cantPedidos/maxPedidos-cantReclamos/cantPedidos);
            calificacion = new BigDecimal(c).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        }


        restaurante.setCalificacionVPI(calificacion);
        restauranteRepository.save(restaurante);
    }

    @Override
    public Integer cantPedidos(String idRestaurante) {
        return pedidoRepository.cantPedidos(idRestaurante, PedidoEntity.EstadoPedido.Confirmado);
    }

    @Override
    public List<RestauranteDto> getRestaurantes() {
        List<RestauranteDto> restauranteDtos = mapToDto(restauranteRepository.findByConfirmadoTrue());
        return restauranteDtos;
    }


}