package com.VPI.VPI.Controllers;

import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Entities.PedidoEntity;
import com.VPI.VPI.Entities.ReclamoEntity;
import com.VPI.VPI.Services.IAWSS3Service;
import com.VPI.VPI.Services.IClienteService;
import com.VPI.VPI.Services.IRestauranteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
class RestauranteControllerTest {

    @InjectMocks
    RestauranteController restauranteController;

    @Mock
    IRestauranteService restauranteService;

    @Mock
    IClienteService clienteService ;

    @Mock
    private IAWSS3Service awss3Service;

    @Mock
    private BindingResult mockBindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void altaAgregado() {
        AltaAgregadoDto altaAgregadoDto = new AltaAgregadoDto();
        altaAgregadoDto.setRestaurante("restaurante@gmail.com");
        altaAgregadoDto.setCosto(400.0);
        altaAgregadoDto.setNombre("nombre");
        altaAgregadoDto.setId(1);

        ResponseEntity<?> response = restauranteController.altaAgregado(altaAgregadoDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void abrirCerrarRestaurante() {
        AbiertoDto abiertoDto = new AbiertoDto();
        abiertoDto.setAbierto(false);
        abiertoDto.setIdRestaurante("restaurante@gmail.com");
        ResponseEntity<?> response = restauranteController.abrirCerrarRestaurante(abiertoDto);
        String salida = "<200 OK OK,false,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void getPedidos() {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setIdPedido(1);
        pedidoDto.setRestaurante("restaurante@gmail.com");
        pedidoDto.setCliente("clienet@gmail.com");
        pedidoDto.setComentario("comentario");
        pedidoDto.setTiempoE(2);
        pedidoDto.setComentarioEstado("estado");
        pedidoDto.setDireccion("direccion");
        pedidoDto.setEstado(PedidoEntity.EstadoPedido.Pendiente);
        Date fecha = new Date();
        pedidoDto.setFecha(fecha);
        List<AltaItemDto> altaItemDtos = new ArrayList<AltaItemDto>();
        pedidoDto.setItems(altaItemDtos);
        pedidoDto.setPagoAcreditado(false);
        pedidoDto.setPrecioTotal(300.0);
        List<PedidoDto> pedidos = new ArrayList<PedidoDto>();
        pedidos.add(pedidoDto);
        Mockito.when(restauranteService.pedidoByRestaurante("restaurante@gmail.com")).thenReturn(pedidos);

        List<PedidoDto> lista = restauranteController.getPedidos("restaurante@gmail.com");
        Assertions.assertEquals(pedidos, lista);
    }

    @Test
    void pagoEnEfectivo() {
        ResponseEntity<?> response = restauranteController.pagoEnEfectivo(1);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void confirmarPedido() throws IOException {
        ConfirmarPedidoDto confirmarPedidoDto = new ConfirmarPedidoDto();
        confirmarPedidoDto.setIdPedido(1);
        confirmarPedidoDto.setTiempoE(34);
        ResponseEntity<?> response = restauranteController.confirmarPedido(confirmarPedidoDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void aceptarReclamo() throws IOException {
        AceptaReclamoDto aceptaReclamoDto = new AceptaReclamoDto();
        aceptaReclamoDto.setIdReclamo(1);
        aceptaReclamoDto.setTipo("Compensación");

        ResponseEntity<?> response = restauranteController.aceptarReclamo(aceptaReclamoDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void rechazarReclamo() throws IOException {
        RechazoReclamoDto rechazoReclamoDto = new RechazoReclamoDto();
        rechazoReclamoDto.setIdReclamo(1);
        rechazoReclamoDto.setMotivoRechazo("porque si");

        ResponseEntity<?> response = restauranteController.rechazarReclamo(rechazoReclamoDto, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void getPedidosPendientes() {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setIdPedido(1);
        pedidoDto.setRestaurante("restaurante@gmail.com");
        pedidoDto.setCliente("clienet@gmail.com");
        pedidoDto.setComentario("comentario");
        pedidoDto.setTiempoE(2);
        pedidoDto.setComentarioEstado("estado");
        pedidoDto.setDireccion("direccion");
        pedidoDto.setEstado(PedidoEntity.EstadoPedido.Pendiente);
        Date fecha = new Date();
        pedidoDto.setFecha(fecha);
        List<AltaItemDto> altaItemDtos = new ArrayList<AltaItemDto>();
        pedidoDto.setItems(altaItemDtos);
        pedidoDto.setPagoAcreditado(false);
        pedidoDto.setPrecioTotal(300.0);
        List<PedidoDto> pedidos = new ArrayList<PedidoDto>();
        pedidos.add(pedidoDto);

        Mockito.when(restauranteService.pedidosPendienteRestaurante("restaurante@gmail.com")).thenReturn(pedidos);
        List<PedidoDto> pedidos2 = restauranteController.getPedidosPendientes("restaurante@gmail.com");
        Assertions.assertEquals(pedidos, pedidos2);
    }

    @Test
    void listarReclamos() {
        BusquedaReclamoDto busquedaReclamoDto = new BusquedaReclamoDto();
        busquedaReclamoDto.setEstadoReclamo(ReclamoEntity.EstadoReclamo.Pendiente);
        busquedaReclamoDto.setIdPedido(1);
        Date fecha = new Date();
        busquedaReclamoDto.setFechaDesde(fecha);
        busquedaReclamoDto.setFechaHasta(fecha);

        ResponseEntity<List<ReclamoDto>> response = restauranteController.listarReclamos(busquedaReclamoDto, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void isAbierto() {
        Mockito.when(restauranteService.isAbierto("restaurante@gmail.com")).thenReturn(true);
        Boolean isAbierto = restauranteController.isAbierto("restaurante@gmail.com");
        Assertions.assertEquals(true, isAbierto);
    }

    @Test
    void getAllReclamos() {

    }

    @Test
    void getPedidosSinPendientes() {
    }

    @Test
    void getMenus() {
        MenuDto menuDto = new MenuDto();
        menuDto.setImagen("url");
        menuDto.setIdMenu(1);
        menuDto.setDescuento(23.0);
        menuDto.setRestaurante("restaurante@gmail.com");
        menuDto.setDescripcion("desc");
        menuDto.setCategoria("Hamburguesa");
        menuDto.setPromocion(false);
        menuDto.setCosto(300.0);
        menuDto.setNombre("menu");

        List<MenuDto> menus = new ArrayList<MenuDto>();
        menus.add(menuDto);

        Mockito.when(restauranteService.listMenus("restaurante@gmail.com")).thenReturn(menus);

        List<MenuDto> menusLista = restauranteController.getMenus("restaurante@gmail.com");
        Assertions.assertEquals(menus, menusLista);
    }

    @Test
    void calificarCliente() {
        CalificacionDto calificacionDto = new CalificacionDto();
        calificacionDto.setCliente(false);
        calificacionDto.setIdRestaurante("restaurante@gmail.com");
    }

    @Test
    void eliminarCalificacionCliente() {
        CalificacionDto calificacionDto = new CalificacionDto();
        calificacionDto.setIdRestaurante("restaurante@gmail.com");
        calificacionDto.setCliente(false);
        calificacionDto.setIdCliente("cliente@gmail.com");
        calificacionDto.setPuntuacion(3);

        ResponseEntity<?> response = restauranteController.eliminarCalificacionCliente(calificacionDto, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void obtenerCalificacionGlobal() {
    }

    @Test
    void eliminarAgregado() {
        AltaAgregadoDto altaAgregadoDto = new AltaAgregadoDto();
        altaAgregadoDto.setRestaurante("restaurante@gmail.com");
        altaAgregadoDto.setCosto(400.0);
        altaAgregadoDto.setNombre("nombre");
        altaAgregadoDto.setId(1);

        ResponseEntity<?> response = restauranteController.altaAgregado(altaAgregadoDto);
        ResponseEntity<?> response2 =  restauranteController.eliminarAgregado(1);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

    }

    @Test
    void modificarAgregado() {
        AltaAgregadoDto altaAgregadoDto = new AltaAgregadoDto();
        altaAgregadoDto.setRestaurante("restaurante@gmail.com");
        altaAgregadoDto.setCosto(400.0);
        altaAgregadoDto.setNombre("nombre");
        altaAgregadoDto.setId(1);

        restauranteController.altaAgregado(altaAgregadoDto);

        ModificacionAgregadoDto modificacionAgregadoDto = new ModificacionAgregadoDto();
        modificacionAgregadoDto.setCosto(500.0);
        modificacionAgregadoDto.setId(1);

        ResponseEntity<?> response = restauranteController.modificarAgregado(modificacionAgregadoDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void listarAgregados() {
        AltaAgregadoDto altaAgregadoDto = new AltaAgregadoDto();
        altaAgregadoDto.setId(1);
        altaAgregadoDto.setRestaurante("restaurante@gamil.com");
        altaAgregadoDto.setNombre("nombre");
        altaAgregadoDto.setCosto(400.0);

        List<AltaAgregadoDto> agregados = new ArrayList<AltaAgregadoDto>();
        agregados.add(altaAgregadoDto);

        Mockito.when(restauranteService.listarAgregados("restaurante@gmail.com")).thenReturn(agregados);

        List<AltaAgregadoDto> agregados2 = restauranteController.listarAgregados("restaurante@gmail.com");

        Assertions.assertEquals(agregados, agregados2);
    }

    @Test
    void eliminarMenu() {
        ResponseEntity<?> response = restauranteController.eliminarMenu(1);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void obtenerMisDatos() {
    }

    @Test
    void altaMenu() {
        MockMultipartFile multipartFile
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        String menu = "{\"categoria\":\"Hamburguesa\",\"promocion\":\"false\",\"descripcion\":\"desc\",\"costo\":\"222\",\"descuento\":\"4\",\"imagen\":\"url\",\"restaurante\":\"res\"}";
        try {
            Mockito.when(awss3Service.uploadFile(multipartFile)).thenReturn("ok");
            ResponseEntity<?> response =  restauranteController.altaMenu(multipartFile, menu);
            String salida = "<200 OK OK,[]>";
            Assertions.assertEquals(salida, response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void obtenerCalificacionCliente() {
        CalificacionDto calificacionDto = new CalificacionDto();
        calificacionDto.setPuntuacion(0);
        calificacionDto.setIdCliente("cliente@gmail.com");
        calificacionDto.setIdRestaurante("restaurante@gmail.com");
        calificacionDto.setCliente(false);

        Integer calificacion = restauranteController.obtenerCalificacionCliente(calificacionDto);
        Assertions.assertEquals(0, calificacion);
    }

    @Test
    void convertMultiPartToFile() {

    }

    @Test
    void asociarAgregados() {
        AsociarAgregadoDto asociarAgregadoDto = new AsociarAgregadoDto();
        List<Integer> lista = new ArrayList<Integer>();
        asociarAgregadoDto.setIdAgregados(lista);
        asociarAgregadoDto.setIdMenu(1);

        ResponseEntity<?> response = restauranteController.asociarAgregados(asociarAgregadoDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void agregadosNoMenu() {
    }

    @Test
    void modificarMenu() {
    }

    @Test
    void getAgregados() {
        AltaAgregadoDto altaAgregadoDto = new AltaAgregadoDto();
        altaAgregadoDto.setCosto(400.0);
        altaAgregadoDto.setRestaurante("restaurante@gmail.com");
        altaAgregadoDto.setNombre("agregado");
        altaAgregadoDto.setId(1);

        List<AltaAgregadoDto> agregados = new ArrayList<AltaAgregadoDto>();
        agregados.add(altaAgregadoDto);

        Mockito.when(restauranteService.agragadosByMenu(1)).thenReturn(agregados);
        List<AltaAgregadoDto> agregados2 = restauranteController.getAgregados(1);

        Assertions.assertEquals(agregados, agregados2);

    }

    @Test
    void quitarAgregado() {
        AsociarAgregadoDto asociarAgregadoDto = new AsociarAgregadoDto();
        List<Integer> lista = new ArrayList<Integer>();
        asociarAgregadoDto.setIdAgregados(lista);
        asociarAgregadoDto.setIdMenu(1);

        restauranteController.asociarAgregados(asociarAgregadoDto);

        QuitarAgregadoDto quitarAgregadoDto = new QuitarAgregadoDto();
        quitarAgregadoDto.setIdAgregado(1);
        quitarAgregadoDto.setIdMenu(1);
        ResponseEntity<?> response = restauranteController.quitarAgregado(quitarAgregadoDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void getRestConPromo() {
    }

    @Test
    void menuCategoria() {
    }
}