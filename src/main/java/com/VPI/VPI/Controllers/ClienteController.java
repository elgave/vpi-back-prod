package com.VPI.VPI.Controllers;

import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Services.IClienteService;
import com.VPI.VPI.Services.IRestauranteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@PreAuthorize("hasAuthority('Cliente')")//seguridad basica del controlador
@CrossOrigin
@RestController
@RequestMapping(value = "/cliente")
@Api( tags = "Cliente")
public class ClienteController {
    @Autowired
    IClienteService clienteService;
    @Autowired
    IRestauranteService restauranteService;

    @PostMapping("/altaPedido")
    public ResponseEntity<?> altaPedido (@Valid @RequestBody AltaPedidoDto altaPedidoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);
        String idCliente = altaPedidoDto.getCliente();
        String idRest = altaPedidoDto.getRestaurante();
        clienteService.altaPedido(altaPedidoDto,idRest,idCliente);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/agregarFavorito")
    public ResponseEntity<?> agregarFavorito (@Valid @RequestBody FavoritoDto favoritoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);

        clienteService.agregarFavorito(favoritoDto.getIdCliente(),favoritoDto.getIdRestaurante());
        return new ResponseEntity(HttpStatus.OK);
    }
    //rafa empieza
    @PostMapping("/agregarToken")
    public ResponseEntity<?> agregarToken (@Valid @RequestBody TokenMobileDto tokenDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);

        clienteService.agregarTokenMobile(tokenDto.getIdCliente(),tokenDto.getToken());
        return new ResponseEntity(HttpStatus.OK);
    }
    // rafa termina

    @PostMapping("/agregarDireccion")
    public ResponseEntity<?> agregarDireccion (@RequestBody DireccionDto direccionDto){
        if(clienteService.existsDireccion(direccionDto.getIdCliente(),direccionDto.getNombre())) {
            return new ResponseEntity(("Ya tienes una direccion con el nombre: "+ direccionDto.getNombre()), HttpStatus.BAD_REQUEST);
        }
        clienteService.agregarDireccion(direccionDto);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/getAllRestaurantes")
    public List<RestauranteDto> getAllRestaurantes() {

        return clienteService.getRestaurantes();
    }

    @GetMapping("/getMenusRestaurante")
    public List<MenuDto> getMenusRestaurante(String nombreRestaurante) {

        return clienteService.getMenusResturante(nombreRestaurante);
    }

    @GetMapping("/getAgregados")
    public List<AltaAgregadoDto> getAgregados(Integer idMenu) {

        return clienteService.agragadosByMenu(idMenu);
    }

    @PostMapping("altaReclamo")
    public ResponseEntity<?> altaReclamo ( @RequestBody ReclamoDto reclamoDto){
            clienteService.altaReclamo(reclamoDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getDatosRestaurante")
    public RestauranteDto getDatosRestaurante(String nombreRestaurante) {
        return clienteService.getDatosRestaurante(nombreRestaurante);
    }

    @GetMapping("/getDirecciones")
    public List<DireccionDto> getDirecciones(String email) {

        return clienteService.getDirecciones(email);
    }

    @GetMapping("/getPedidos")
    public List<PedidoDto> getPedidos(String email) {
        return clienteService.getPedidos(email);
    }


    @PostMapping("/calificarRestaurante")
    public ResponseEntity<?> calificarRestaurante (@Valid @RequestBody CalificacionDto calificacionDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);

        clienteService.calificarRestaurante(calificacionDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/IsFavorito")
    public Boolean isFavorito(@Valid @RequestBody FavoritoDto favoritoDto) {

        return clienteService.isFavorito(favoritoDto);
    }

    @GetMapping("/getCantDirecciones")
    public Integer getCantDirecciones(String email) {

        return clienteService.cantDirecciones(email);
    }

    @PostMapping("eliminarCalificacion")
    public ResponseEntity<?> eliminarCalificacionRestaurante (@Valid @RequestBody CalificacionDto calificacionDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);

        clienteService.eliminarCalificacionRestaurante(calificacionDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("obtenerMiCalificacion/{id}")
    public Double obtenerCalificacionGlobal(@PathVariable("id") String id){

        return clienteService.obtenerCalificacionGlobal(id);

    }

    @PostMapping("eliminarDireccion")
    public ResponseEntity<?> eliminarDireccion (@RequestBody Integer id){
        clienteService.eliminarDireccion(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("modificarDireccion")
    public ResponseEntity<?> modificarDireccion ( @RequestBody ModificarDireccionDto modificarDireccionDto){

        clienteService.modificarDireccion(modificarDireccionDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("obtenerMisDatos/{email}")
    public ClienteDto obtenerMisDatos(@PathVariable("email") String email ){

        return clienteService.obtenerMisDatos(email);

    }

    @PostMapping("puedeCalificar")
    public Boolean puedeCalificar(@Valid @RequestBody CalificacionDto calificacionDto) {

        return clienteService.puedeCalificar(calificacionDto);
    }
    @PostMapping("obtenerCalificacionRestaurante")
    public Integer obtenerCalificacionRestaurante(@Valid @RequestBody CalificacionDto calificacionDto) {

        return clienteService.obtenerCalificacionRestaurante(calificacionDto);
    }

    @GetMapping("eliminarMiCuenta/{email}")
    public ResponseEntity<?> eliminarMiCuenta(@PathVariable("email") String email ){

         clienteService.eliminarMiCuenta(email);

         return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/quitarFavoritoCliente")
    public ResponseEntity<?> quitarFavoritoCliente (@RequestBody FavoritoDto favoritoDto){
        clienteService.quitarFavorito(favoritoDto.getIdCliente(),favoritoDto.getIdRestaurante());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("restFavoritos/{idCliente}")
    public List<RestauranteDto> restFavoritos(@PathVariable("idCliente") String idCliente) {
        return clienteService.restaurantesFavoritos(idCliente);
    }


    @GetMapping("buscarRestaurantesAbiertos/{busqueda}")
    public List<RestauranteDto> buscarRestaurantesAbiertos(@PathVariable("busqueda") String busqueda) {
        return clienteService.buscarRestaurantesAbiertos(busqueda);
    }

    @GetMapping("restaurantesMasVentas")
    public List<RestaurantesMasVentasDto> restaurantesMasVentas(){

        return restauranteService.restaurantesMasVentas();
    }






}
