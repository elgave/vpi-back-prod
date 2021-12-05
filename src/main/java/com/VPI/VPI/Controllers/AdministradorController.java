package com.VPI.VPI.Controllers;

import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Services.IAdminService;
import com.VPI.VPI.Services.IClienteService;
import com.VPI.VPI.Services.IRestauranteService;
import com.VPI.VPI.Services.IUsuarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@PreAuthorize("hasAuthority('Admin')")//seguridad basica del controlador
@RestController
@RequestMapping(value = "/admin")
@Api( tags = "Admin")
public class AdministradorController {

    @Autowired
    IAdminService adminService;

    @Autowired
    IRestauranteService restauranteService;

    @Autowired
    IClienteService clienteService;

    @Autowired
    IUsuarioService usuarioService;

    @PostMapping("aprobarRestaurante")
    public void aprobarRestaurante(@RequestBody String id){
        this.adminService.aprobarRestarurante(id);
    }

    @PostMapping("rechazarRestaurante")
    public ResponseEntity<?> rechazarRestaurante (@Valid @RequestBody RechazoBloqueoDTO rechazoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);

        restauranteService.deleteByEmail(rechazoDto.getEmail(),rechazoDto.getMotivoRechazo());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/bloquearRestaurante")
    public ResponseEntity<?> bloquearRestaurante (@Valid @RequestBody RechazoBloqueoDTO rechazoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);

        adminService.bloquearRestaurante(rechazoDto.getEmail(),rechazoDto.getMotivoRechazo());
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/bloquearCliente")
    public ResponseEntity<?> bloquearCliente (@Valid @RequestBody RechazoBloqueoDTO rechazoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);

        adminService.bloquearCliente(rechazoDto.getEmail(),rechazoDto.getMotivoRechazo());
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("restaurantesPendientes")
    public List<RestauranteDto> listarRestaurantesPendientes() {return this.restauranteService.findByConfirmado(false); }

    @PostMapping("/registroAdmin")
    public ResponseEntity<?> registroAdmin (@RequestBody NuevoAdminDto adminDto){
        if(usuarioService.existsByEmail(adminDto.getEmail())){
            return new ResponseEntity(("El email ya se encuentra registrado"), HttpStatus.BAD_REQUEST);
        }

        adminService.createAdmin(adminDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("usuariosBusqueda")
    public List<UsuarioDto> usuariosBusqueda(@RequestBody BusquedaUsuarioDto busquedaUsuarioDto) {

        if(busquedaUsuarioDto.getTipoCliente()) {
            return this.clienteService.buscarClientes(busquedaUsuarioDto.getBloqueado(),busquedaUsuarioDto.getTextoBusqueda());
        }
        else{
            return this.restauranteService.buscarRestaurantes(busquedaUsuarioDto.getBloqueado(),busquedaUsuarioDto.getTextoBusqueda());
        }

    }

    @PostMapping("eliminarUsuario")
    public void eliminarUsuario(@RequestBody EliminarUsuarioDto eliminarUsuarioDto) {

        if(eliminarUsuarioDto.getCliente()) {
            this.clienteService.eliminarMiCuenta(eliminarUsuarioDto.getIdUsuario());
        }
        else{
            this.restauranteService.eliminarCuenta(eliminarUsuarioDto.getIdUsuario());
        }

    }

    @GetMapping("restaurantesMasVentas")
    public List<RestaurantesMasVentasDto> restaurantesMasVentas(){

        return restauranteService.restaurantesMasVentas();
    }

    @GetMapping("/getDatosRestaurante")
    public RestauranteDto getDatosRestaurante(String nombreRestaurante) {
        return clienteService.getDatosRestaurante(nombreRestaurante);
    }

    @GetMapping("/getMenusRestaurante")
    public List<MenuDto> getMenusRestaurante(String nombreRestaurante) {

        return clienteService.getMenusResturante(nombreRestaurante);
    }

    @PostMapping("/calificacionVPI")
    public ResponseEntity<?> calificacionVPI (@RequestBody String idRestaurante)  {

        if(adminService.cantPedidos(idRestaurante) < 10){
            Integer c = 10 - adminService.cantPedidos(idRestaurante) ;
            return new ResponseEntity(("Faltan " +c+" pedidos para poder calificar" ),HttpStatus.BAD_REQUEST);
        }else{
            adminService.calificacionVPI(idRestaurante);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/getRestaurantes")
    public List<RestauranteDto> getRestaurantes() {
        return this.adminService.getRestaurantes();
    }

    @GetMapping("restaurantesMejorVPI")
    public List<RestaurantesMejorCaliDto> restaurantesMejorVPI(){

        return restauranteService.restaurantesMejorVPI();
    }

    @GetMapping("restaurantesMejorTiempo")
    public List<RestaurantesMejorCaliDto> restaurantesMejorTiempo(){

        return restauranteService.restaurantesMejorTiempo();
    }

    @GetMapping("restaurantesMejorCali")
    public List<RestaurantesMejorCaliDto> restaurantesMejorCali(){
        return adminService.restaurantesMejorCaliCliente();
    }


}
