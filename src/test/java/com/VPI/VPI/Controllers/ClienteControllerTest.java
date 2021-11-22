package com.VPI.VPI.Controllers;

import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Entities.*;
import com.VPI.VPI.Services.IClienteService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class ClienteControllerTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    IClienteService iClienteService;

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
    void altaPedido() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setEmail("cliente@gmail.com");
        cliente.setRol(UsuarioEntity.Rol.Cliente);
        cliente.setPassWord("123");
        cliente.setBloqueado(false);
        cliente.setNombre("Cliente");
        cliente.setApellido("Apellido");
        cliente.setCelular("09999");
        cliente.setFoto("url");

        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setCliente(cliente);
        direccionEntity.setApto("apto");
        direccionEntity.setBarrio("barrio");
        direccionEntity.setNombre("casa2");
        direccionEntity.setEsquina("esquina");
        direccionEntity.setNumero("111");
        direccionEntity.setCalle("calle");
        direccionEntity.setPrincipal(true);
        direccionEntity.setId(1);

        Set<DireccionEntity> direcciones = new HashSet<DireccionEntity>();
        direcciones.add(direccionEntity);

        cliente.setDireccion(direcciones);

        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setNombreRestaurante("resturante");
        restaurante.setConfirmado(true);
        restaurante.setCelular("0999");
        restaurante.setBloqueado(false);
        restaurante.setDireccion("casa2");
        restaurante.setFoto("url");
        restaurante.setAbierto(true);
        restaurante.setDescripcionMenues("descripciones");
        restaurante.setHorario("horario");
        restaurante.setNroHabilitacion("3434");
        restaurante.setRazonSocial("razon social");
        restaurante.setPrecioEnvio(45);
        restaurante.setEmail("restaurante@gmail.com");

        MenuEntity menu = new MenuEntity();
        menu.setNombre("menu");
        menu.setCategoria(MenuEntity.Categoria.Hamburguesas);
        menu.setCosto(456.0);
        menu.setDescripcion("des");
        menu.setImagen("url");
        menu.setPromocion(false);
        menu.setIdMenu(1);
        menu.setRestaurante(restaurante);

        PedidoEntity pedido = new PedidoEntity();
        pedido.setIdPedido(1);
        pedido.setCliente(cliente);
        pedido.setDireccion("casa2");
        pedido.setEstado(PedidoEntity.EstadoPedido.Pendiente);
        pedido.setRestaurante(restaurante);
        pedido.setComentario("comentario");
        Date fecha = new Date();
        pedido.setFecha(fecha);
        pedido.setPagoOnline(true);
        pedido.setPrecioTotal(500.0);
        pedido.setTiempoE(45);

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setPedido(pedido);
        itemEntity.setNombre("Hamburguesa");
        itemEntity.setIdItem(1);
        itemEntity.setCantidad(1);
        itemEntity.setPrecio(200.0);

        AltaItemDto altaItemDto = new AltaItemDto();
        altaItemDto.setCantidad(1);
        altaItemDto.setPedido("Hamburguesa");
        altaItemDto.setPrecio(200.0);
        altaItemDto.setNombre("Hamburguesa");

        List<AltaItemDto> listaItem = new ArrayList<AltaItemDto>();
        listaItem.add(altaItemDto);

        AltaPedidoDto altaPedidoDto = new AltaPedidoDto();
        altaPedidoDto.setIdPedido(1);
        altaPedidoDto.setCliente("cliente@gmail.com");
        altaPedidoDto.setRestaurante("restaurante@gmail.com");
        altaPedidoDto.setPagoOnline(false);
        altaPedidoDto.setComentario("comentario");
        altaPedidoDto.setDireccion("casa2");
        altaPedidoDto.setPrecioTotal(200.0);
        altaPedidoDto.setItems(listaItem);

        ResponseEntity<?> response = clienteController.altaPedido(altaPedidoDto, mockBindingResult);

        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

    }

    @Test
    void agregarFavorito() {
        RestauranteDto restauranteDto1 = new RestauranteDto();
        restauranteDto1.setNombreRestaurante("restaurante3");
        restauranteDto1.setCelular("099888777");
        restauranteDto1.setDireccion("direccion1");
        restauranteDto1.setConfirmado(true);
        restauranteDto1.setEmail("resturante3@gmail.com");
        restauranteDto1.setFoto("url");
        restauranteDto1.setDescripcionMenues("descripcion");
        restauranteDto1.setHorario("horario");
        restauranteDto1.setNroHabilitacion("45");
        restauranteDto1.setPrecioEnvio(566);
        restauranteDto1.setRazonSocial("razon social");
        restauranteDto1.setRut("232334");

        ClienteEntity cliente = new ClienteEntity();
        cliente.setEmail("cliente3@gmail.com");
        cliente.setRol(UsuarioEntity.Rol.Cliente);
        cliente.setPassWord("123");
        cliente.setBloqueado(false);
        cliente.setNombre("Cliente");
        cliente.setApellido("Apellido");
        cliente.setCelular("09999");
        cliente.setFoto("url");

        FavoritoDto favoritoDto = new FavoritoDto();
        favoritoDto.setIdCliente("cliente3@gmail.com");
        favoritoDto.setIdRestaurante("resturante3@gmail.com");

        ResponseEntity<?> response = clienteController.agregarFavorito(favoritoDto, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

    }

    @Test
    void agregarDireccion() {
        DireccionDto direccionDto = new DireccionDto();
        direccionDto.setNombre("casa");
        direccionDto.setPrincipal(true);
        direccionDto.setNumero("2");
        direccionDto.setCalle("Av italia");
        direccionDto.setBarrio("barrio");
        direccionDto.setEsquina("esquina");
        direccionDto.setApto("apto");

        ResponseEntity<?> response = clienteController.agregarDireccion(direccionDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void getAllRestaurantes() {
        RestauranteDto restauranteDto1 = new RestauranteDto();
        restauranteDto1.setNombreRestaurante("restaurante1");
        restauranteDto1.setCelular("099888777");
        restauranteDto1.setDireccion("direccion1");
        restauranteDto1.setConfirmado(true);
        restauranteDto1.setEmail("resturante1@gmail.com");
        restauranteDto1.setFoto("url");
        restauranteDto1.setDescripcionMenues("descripcion");
        restauranteDto1.setHorario("horario");
        restauranteDto1.setNroHabilitacion("45");
        restauranteDto1.setPrecioEnvio(566);
        restauranteDto1.setRazonSocial("razon social");
        restauranteDto1.setRut("232334");

        RestauranteDto restauranteDto2 = new RestauranteDto();
        restauranteDto2.setNombreRestaurante("restaurante2");
        restauranteDto2.setCelular("099888797");
        restauranteDto2.setDireccion("direccion2");
        restauranteDto2.setConfirmado(true);
        restauranteDto2.setEmail("resturante2@gmail.com");
        restauranteDto2.setFoto("url");
        restauranteDto2.setDescripcionMenues("descripcion");
        restauranteDto2.setHorario("horario");
        restauranteDto2.setNroHabilitacion("42");
        restauranteDto2.setPrecioEnvio(56);
        restauranteDto2.setRazonSocial("razon social 2");
        restauranteDto2.setRut("2323234");

        List<RestauranteDto> restaurantesLista = new ArrayList<RestauranteDto>();
        restaurantesLista.add(restauranteDto1);
        restaurantesLista.add(restauranteDto2);

        Mockito.when(iClienteService.getRestaurantes()).thenReturn(restaurantesLista);

        List<RestauranteDto> listaObtenida = clienteController.getAllRestaurantes();

        Assertions.assertEquals(restaurantesLista, listaObtenida);
    }

    @Test
    void getMenusRestaurante() {
        MenuDto menuDto1 = new MenuDto();
        menuDto1.setIdMenu(1);
        menuDto1.setNombre("menu1");
        menuDto1.setRestaurante("restaurante1");
        menuDto1.setCategoria("Pizza");
        menuDto1.setCosto(500.0);
        menuDto1.setDescripcion("descripcion");
        menuDto1.setPromocion(true);
        menuDto1.setDescuento(10.0);
        menuDto1.setImagen("url");

        MenuDto menuDto2 = new MenuDto();
        menuDto1.setIdMenu(2);
        menuDto1.setNombre("menu2");
        menuDto1.setRestaurante("restaurante1");
        menuDto1.setCategoria("Pizza");
        menuDto1.setCosto(500.0);
        menuDto1.setDescripcion("descripcion");
        menuDto1.setPromocion(true);
        menuDto1.setDescuento(10.0);
        menuDto1.setImagen("url");

        MenuDto menuDto3 = new MenuDto();
        menuDto1.setIdMenu(3);
        menuDto1.setNombre("menu3");
        menuDto1.setRestaurante("restaurante1");
        menuDto1.setCategoria("Pizza");
        menuDto1.setCosto(500.0);
        menuDto1.setDescripcion("descripcion");
        menuDto1.setPromocion(true);
        menuDto1.setDescuento(10.0);
        menuDto1.setImagen("url");

        List<MenuDto> menusLista = new ArrayList<MenuDto>();
        menusLista.add(menuDto1);
        menusLista.add(menuDto2);
        menusLista.add(menuDto3);

        Mockito.when(iClienteService.getMenusResturante("restaurante1")).thenReturn(menusLista);

        List<MenuDto> menusObtenidos = clienteController.getMenusRestaurante("restaurante1");

        Assertions.assertEquals(menusLista, menusObtenidos);
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

        Mockito.when(iClienteService.agragadosByMenu(1)).thenReturn(agregados);
        List<AltaAgregadoDto> agregados2 = clienteController.getAgregados(1);

        Assertions.assertEquals(agregados, agregados2);
    }

    @Test
    void altaReclamo() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setEmail("cliente@gmail.com");
        cliente.setRol(UsuarioEntity.Rol.Cliente);
        cliente.setPassWord("123");
        cliente.setBloqueado(false);
        cliente.setNombre("Cliente");
        cliente.setApellido("Apellido");
        cliente.setCelular("09999");
        cliente.setFoto("url");

        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setCliente(cliente);
        direccionEntity.setApto("apto");
        direccionEntity.setBarrio("barrio");
        direccionEntity.setNombre("casa2");
        direccionEntity.setEsquina("esquina");
        direccionEntity.setNumero("111");
        direccionEntity.setCalle("calle");
        direccionEntity.setPrincipal(true);
        direccionEntity.setId(1);

        Set<DireccionEntity> direcciones = new HashSet<DireccionEntity>();
        direcciones.add(direccionEntity);

        cliente.setDireccion(direcciones);

        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setNombreRestaurante("resturante");
        restaurante.setConfirmado(true);
        restaurante.setCelular("0999");
        restaurante.setBloqueado(false);
        restaurante.setDireccion("casa2");
        restaurante.setFoto("url");
        restaurante.setAbierto(true);
        restaurante.setDescripcionMenues("descripciones");
        restaurante.setHorario("horario");
        restaurante.setNroHabilitacion("3434");
        restaurante.setRazonSocial("razon social");
        restaurante.setPrecioEnvio(45);
        restaurante.setEmail("restaurante@gmail.com");

        MenuEntity menu = new MenuEntity();
        menu.setNombre("menu");
        menu.setCategoria(MenuEntity.Categoria.Hamburguesas);
        menu.setCosto(456.0);
        menu.setDescripcion("des");
        menu.setImagen("url");
        menu.setPromocion(false);
        menu.setIdMenu(1);
        menu.setRestaurante(restaurante);

        PedidoEntity pedido = new PedidoEntity();
        pedido.setIdPedido(1);
        pedido.setCliente(cliente);
        pedido.setDireccion("casa2");
        pedido.setEstado(PedidoEntity.EstadoPedido.Pendiente);
        pedido.setRestaurante(restaurante);
        pedido.setComentario("comentario");
        Date fecha = new Date();
        pedido.setFecha(fecha);
        pedido.setPagoOnline(true);
        pedido.setPrecioTotal(500.0);
        pedido.setTiempoE(45);

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setPedido(pedido);
        itemEntity.setNombre("Hamburguesa");
        itemEntity.setIdItem(1);
        itemEntity.setCantidad(1);
        itemEntity.setPrecio(200.0);

        AltaItemDto altaItemDto = new AltaItemDto();
        altaItemDto.setCantidad(1);
        altaItemDto.setPedido("Hamburguesa");
        altaItemDto.setPrecio(200.0);
        altaItemDto.setNombre("Hamburguesa");

        List<AltaItemDto> listaItem = new ArrayList<AltaItemDto>();
        listaItem.add(altaItemDto);

        AltaPedidoDto altaPedidoDto = new AltaPedidoDto();
        altaPedidoDto.setIdPedido(2);
        altaPedidoDto.setCliente("cliente@gmail.com");
        altaPedidoDto.setRestaurante("restaurante@gmail.com");
        altaPedidoDto.setPagoOnline(false);
        altaPedidoDto.setComentario("comentario");
        altaPedidoDto.setDireccion("casa2");
        altaPedidoDto.setPrecioTotal(200.0);
        altaPedidoDto.setItems(listaItem);

        ResponseEntity<?> response = clienteController.altaPedido(altaPedidoDto, mockBindingResult);

        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

        ReclamoDto reclamoDto = new ReclamoDto();
        reclamoDto.setIdReclamo(1);
        reclamoDto.setPedido(2);
        Date fecha2 = new Date();
        reclamoDto.setFecha(fecha2);
        reclamoDto.setComentario("comentario");
        reclamoDto.setTipo(ReclamoEntity.TipoReclamo.Reembolso);
        reclamoDto.setEstado("Pendiente");

        ResponseEntity<?> response2 = clienteController.altaReclamo(reclamoDto);
        String salida2 = "<200 OK OK,[]>";
        Assertions.assertEquals(salida2, response.toString());

    }

    @Test
    void getDatosRestaurante() {
        RestauranteDto restauranteDto1 = new RestauranteDto();
        restauranteDto1.setNombreRestaurante("restaurante3");
        restauranteDto1.setCelular("099888777");
        restauranteDto1.setDireccion("direccion1");
        restauranteDto1.setConfirmado(true);
        restauranteDto1.setEmail("resturante3@gmail.com");
        restauranteDto1.setFoto("url");
        restauranteDto1.setDescripcionMenues("descripcion");
        restauranteDto1.setHorario("horario");
        restauranteDto1.setNroHabilitacion("45");
        restauranteDto1.setPrecioEnvio(566);
        restauranteDto1.setRazonSocial("razon social");
        restauranteDto1.setRut("232334");

        Mockito.when(iClienteService.getDatosRestaurante("restaurante@gmail.com")).thenReturn(restauranteDto1);
        RestauranteDto restauranteDatos = clienteController.getDatosRestaurante("restaurante@gmail.com");
        Assertions.assertEquals(restauranteDto1, restauranteDatos);
    }

    @Test
    void getDirecciones() {
        DireccionDto direccionDto = new DireccionDto();
        direccionDto.setNombre("casa");
        direccionDto.setPrincipal(true);
        direccionDto.setNumero("2");
        direccionDto.setCalle("Av italia");
        direccionDto.setBarrio("barrio");
        direccionDto.setEsquina("esquina");
        direccionDto.setApto("apto");

        List<DireccionDto> direcciones = new ArrayList<DireccionDto>();
        direcciones.add(direccionDto);

        Mockito.when(iClienteService.getDirecciones("cliente@gmail.com")).thenReturn(direcciones);
        List<DireccionDto> direcciones2 = clienteController.getDirecciones("cliente@gmail.com");

        Assertions.assertEquals(direcciones, direcciones2);
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

        Mockito.when(iClienteService.getPedidos("cliente@gmail.com")).thenReturn(pedidos);
        List<PedidoDto> pedidos2 = clienteController.getPedidos("cliente@gmail.com");

        Assertions.assertEquals(pedidos, pedidos2);
    }

    @Test
    void calificarRestaurante() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setEmail("cliente2@gmail.com");
        cliente.setRol(UsuarioEntity.Rol.Cliente);
        cliente.setPassWord("123");
        cliente.setBloqueado(false);
        cliente.setNombre("Cliente");
        cliente.setApellido("Apellido");
        cliente.setCelular("09999");
        cliente.setFoto("url");

        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setCliente(cliente);
        direccionEntity.setApto("apto");
        direccionEntity.setBarrio("barrio");
        direccionEntity.setNombre("casa2");
        direccionEntity.setEsquina("esquina");
        direccionEntity.setNumero("111");
        direccionEntity.setCalle("calle");
        direccionEntity.setPrincipal(true);
        direccionEntity.setId(1);

        Set<DireccionEntity> direcciones = new HashSet<DireccionEntity>();
        direcciones.add(direccionEntity);

        cliente.setDireccion(direcciones);

        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setNombreRestaurante("resturante2");
        restaurante.setConfirmado(true);
        restaurante.setCelular("0999");
        restaurante.setBloqueado(false);
        restaurante.setDireccion("casa2");
        restaurante.setFoto("url");
        restaurante.setAbierto(true);
        restaurante.setDescripcionMenues("descripciones");
        restaurante.setHorario("horario");
        restaurante.setNroHabilitacion("3434");
        restaurante.setRazonSocial("razon social");
        restaurante.setPrecioEnvio(45);
        restaurante.setEmail("restaurante2@gmail.com");

        CalificacionDto calificacionDto = new CalificacionDto();
        calificacionDto.setCliente(true);
        calificacionDto.setIdCliente("cliente2@gmail.com");
        calificacionDto.setIdRestaurante("restaurante2@gmail.com");
        calificacionDto.setPuntuacion(3);

        ResponseEntity<?> response = clienteController.calificarRestaurante(calificacionDto, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void isFavorito() {
        FavoritoDto favoritoDto = new FavoritoDto();
        favoritoDto.setIdCliente("cliente3@gmail.com");
        favoritoDto.setIdRestaurante("resturante3@gmail.com");

        Mockito.when(iClienteService.isFavorito(favoritoDto)).thenReturn(true);
        Boolean isFav = clienteController.isFavorito(favoritoDto);
        Assertions.assertEquals(true, isFav);
    }

    @Test
    void getCantDirecciones() {
        Mockito.when(iClienteService.cantDirecciones("cliente@gmail.com")).thenReturn(1);
        Integer cant = clienteController.getCantDirecciones("cliente@gmail.com");
        Assertions.assertEquals(1, cant);
    }

    @Test
    void eliminarCalificacionRestaurante() {
        CalificacionDto calificacionDto = new CalificacionDto();
        calificacionDto.setCliente(true);
        calificacionDto.setIdRestaurante("restaurante@gmail.com");
        calificacionDto.setIdCliente("cliente@gmail.com");
        calificacionDto.setPuntuacion(2);

        ResponseEntity<?>  response = clienteController.eliminarCalificacionRestaurante(calificacionDto, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void obtenerCalificacionGlobal() {
        Double calif = clienteController.obtenerCalificacionGlobal("cliente@gmail.com");
        Assertions.assertEquals(0, calif);

    }

    @Test
    void eliminarMiCuenta() {
        ResponseEntity<?> response = clienteController.eliminarMiCuenta("cliente@gmail.com");
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void modificarDireccion() {
        ModificarDireccionDto modificarDireccionDto = new ModificarDireccionDto();
        modificarDireccionDto.setApto("apto");

        ResponseEntity<?> response = clienteController.modificarDireccion(modificarDireccionDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void eliminarDireccion() {
        ResponseEntity<?> response = clienteController.eliminarDireccion(1);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }
}