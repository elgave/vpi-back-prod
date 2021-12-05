package com.VPI.VPI.Controllers;

import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Services.IAWSS3Service;
import com.VPI.VPI.Services.IClienteService;
import com.VPI.VPI.Services.IRestauranteService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@PreAuthorize("hasAuthority('Restaurante')")
@RestController
@RequestMapping(value = "/restaurante")
@Api( tags = "Restaurante")
public class RestauranteController{

    @Autowired
    IRestauranteService restauranteService;
    @Autowired
    IClienteService clienteService ;
    @Autowired
    private IAWSS3Service awss3Service;

    @PostMapping("/altaAgregado")
    public ResponseEntity<?> altaAgregado (@RequestBody AltaAgregadoDto dto){
        if(restauranteService.existeAgregado(dto.getRestaurante(),dto.getNombre())){

            return new ResponseEntity(("Ya existe un agregado con este nombre" ),HttpStatus.BAD_REQUEST);
        }else{
        String idRest = dto.getRestaurante();
        restauranteService.altaAgregado(dto,idRest);
        return new ResponseEntity(HttpStatus.OK);}
    }
    @PostMapping("abrirCerrar")
    public ResponseEntity<?> abrirCerrarRestaurante(@RequestBody AbiertoDto abiertoDto){

        return new ResponseEntity(this.restauranteService.abrirCerrar(abiertoDto),HttpStatus.OK);
    }

    @GetMapping("/getPedidos")
    public List<PedidoDto> getPedidos(String idRestaurante) {
        return restauranteService.pedidoByRestaurante(idRestaurante);
    }

    @PostMapping("/pagoEnEfectivo")
    public ResponseEntity<?> pagoEnEfectivo(@RequestBody Integer idPedido) {
        restauranteService.pagoEnEfectivo(idPedido);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("confirmarPedido")
    public ResponseEntity<?> confirmarPedido(@RequestBody ConfirmarPedidoDto confirmarPedidoDto) throws IOException {
        restauranteService.confirmarPedido(confirmarPedidoDto.getIdPedido(),confirmarPedidoDto.getTiempoE());
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/aceptarReclamo")
    public ResponseEntity<?> aceptarReclamo(@RequestBody AceptaReclamoDto aceptaReclamoDto) throws IOException {
        restauranteService.aceptarReclamo(aceptaReclamoDto.getIdReclamo(), aceptaReclamoDto.getTipo());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/rechazarReclamo")
    public ResponseEntity<?> rechazarReclamo (@Valid @RequestBody RechazoReclamoDto rechazoReclamoDto, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);

        restauranteService.rechazarReclamo(rechazoReclamoDto.getIdReclamo(),rechazoReclamoDto.getMotivoRechazo());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getPedidosPendientes")
    public List<PedidoDto> getPedidosPendientes(String idRestaurante) {
        return restauranteService.pedidosPendienteRestaurante(idRestaurante);
    }
    @PostMapping("/listarReclamos")
    public ResponseEntity<List<ReclamoDto>> listarReclamos (@Valid @RequestBody BusquedaReclamoDto busquedaReclamoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);

        restauranteService.listarReclamos(busquedaReclamoDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/isAbierto")
    public Boolean isAbierto(String idRestaurante) {
        return restauranteService.isAbierto(idRestaurante);
    }

    @PostMapping("/getAllReclamos")
    public List<ReclamoDto> getAllReclamos(@RequestBody ReclamosFiltroDto dto) {
        return restauranteService.reclamosConFiltro(dto);
    }

    @GetMapping("/getPedidosSinPendientes")
    public List<PedidoDto> getPedidosSinPendientes(String idRestaurante) {
        return restauranteService.pedidosSinPendienteRestaurante(idRestaurante);
    }

    @GetMapping("/getMenus")
    public List<MenuDto> getMenus(String idRestauarante) {
        return restauranteService.listMenus(idRestauarante);
    }

    @PostMapping("/calificarCliente")
    public ResponseEntity<?> calificarCliente (@Valid @RequestBody CalificacionDto calificacionDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);

        restauranteService.calificarCliente(calificacionDto);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("eliminarCalificacion")
    public ResponseEntity<?> eliminarCalificacionCliente (@Valid @RequestBody CalificacionDto calificacionDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);

        restauranteService.eliminarCalificacionCliente(calificacionDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("obtenerMiCalificacion/{id}")
    public Double obtenerCalificacionGlobal(@PathVariable("id") String id){

        return restauranteService.obtenerCalificacionGlobal(id);

    }

    @PostMapping ("eliminarAgregado")
    public ResponseEntity<?> eliminarAgregado (@RequestBody Integer id){

        restauranteService.eliminarAgregado(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("modificarAgregado")
    public ResponseEntity<?> modificarAgregado ( @RequestBody ModificacionAgregadoDto modificarAgregadoDto){

        restauranteService.modificarAgregado(modificarAgregadoDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("listarAgregados/{id}")
    public List<AltaAgregadoDto> listarAgregados(@PathVariable("id") String id){

        return restauranteService.listarAgregados(id);
    }

    @PostMapping("eliminarMenu")
    public  ResponseEntity<?> eliminarMenu (@RequestBody Integer id){

        restauranteService.eliminarMenu(id);

        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("obtenerMisDatos/{email}")
    public RestauranteDto obtenerMisDatos(@PathVariable("email") String email ){

        return restauranteService.obtenerMisDatos(email);

    }

    @PostMapping("altaMenu")
    public ResponseEntity<?> altaMenu (@RequestParam("file") MultipartFile file, @RequestParam("menuDto") String menuDto) throws IOException {

        NuevoMenuDto menu = new NuevoMenuDto();
        try {
            menu = new ObjectMapper().readValue(menuDto, NuevoMenuDto.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(restauranteService.existsMenu(menu.getNombre(),menu.getRestaurante())){
            return new ResponseEntity(("El menu ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }

        String urlFoto = awss3Service.uploadFile(file);
        menu.setImagen(urlFoto);

        String idRestaurante = menu.getRestaurante();
        restauranteService.createMenu(menu, idRestaurante);
        File tempFile = convertMultiPartToFile(file);
        tempFile.delete();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("obtenerCalificacionCliente")
    public Integer obtenerCalificacionCliente(@Valid @RequestBody CalificacionDto calificacionDto) {

        return restauranteService.obtenerCalificacionCliente(calificacionDto);
    }

    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    @PostMapping("asociarAgregados")
    public  ResponseEntity<?> asociarAgregados (@RequestBody AsociarAgregadoDto asociarAgregadoDto){

        restauranteService.asociarAgregados(asociarAgregadoDto);

        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("agregadosNoMenu")
    public List<AltaAgregadoDto> agregadosNoMenu(@RequestBody RestMenuDto restMenuDto){

        return restauranteService.agregadosQueNoEstanEnElMenu(restMenuDto);
    }

//    @PostMapping("modificarMenu")
//    public ResponseEntity<?> modificarMenu(@RequestBody MenuDto menuDto){
//
//        restauranteService.modificarMenu(menuDto);
//        return  new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping("modificarMenu")
    public ResponseEntity<?> modificarMenu (@RequestParam("file") MultipartFile file, @RequestParam("menuDto") String menuDto) throws IOException {

        MenuDto menu = new MenuDto();
        try {
            menu = new ObjectMapper().readValue(menuDto, MenuDto.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(restauranteService.existsMenu(menu.getNombre(),menu.getRestaurante())){
            return new ResponseEntity(("El menu ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }

        String urlFoto = awss3Service.uploadFile(file);
        menu.setImagen(urlFoto);


        restauranteService.modificarMenu(menu);
        File tempFile = convertMultiPartToFile(file);
        tempFile.delete();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("modificarMenuSinFoto")
    public ResponseEntity<?> modificarMenuSinFoto (@RequestBody MenuDto menuDto){

        restauranteService.modificarMenu(menuDto);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("getAgregados")
    public List<AltaAgregadoDto> getAgregados(Integer idMenu) {
        return restauranteService.agragadosByMenu(idMenu);
    }

    @PostMapping("quitarAgregado")
    public ResponseEntity<?> quitarAgregado(@RequestBody QuitarAgregadoDto dto){

        restauranteService.quitarAgregados(dto);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getRestConPromo")
    public List<RestauranteDto> getRestConPromo() {
        return restauranteService.restConPromo();
    }


    @PostMapping("menuCategoria")
    public List<MenuDto> menuCategoria(@RequestBody MenuCategoriaDto dto){

        return restauranteService.menuCatagorias(dto.getIdRestaurante(), dto.getCategoria());
    }

    @PostMapping("balance")
    public List<PedidoDto> balance(@RequestBody BalanceDto dto) {

        return restauranteService.balance(dto);
    }

    @PostMapping("rechazarPedido")
    public ResponseEntity<?> rechazarPedido (@RequestBody RechazoPedidoDto dto) throws IOException {
        restauranteService.rechazarPedido(dto.getIdPedido(),dto.getMotivoRechazo());
        return new ResponseEntity(HttpStatus.OK);
    }

}
