package com.VPI.VPI.Controllers;

import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Entities.ClienteEntity;
import com.VPI.VPI.Entities.RestauranteEntity;
import com.VPI.VPI.Entities.UsuarioEntity;
import com.VPI.VPI.Services.IAdminService;
import com.VPI.VPI.Services.IClienteService;
import com.VPI.VPI.Services.IRestauranteService;
import com.VPI.VPI.Services.IUsuarioService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class AdministradorControllerTest {

    @InjectMocks
    AdministradorController administradorController;

    @Mock
    IAdminService adminService;

    @Mock
    IUsuarioService usuarioService;

    @Mock
    IRestauranteService restauranteService;

    @Mock
    IClienteService clienteService;

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
    void aprobarRestaurante() {
        RestauranteDto restauranteDto1 = new RestauranteDto();
        restauranteDto1.setNombreRestaurante("restaurante7");
        restauranteDto1.setCelular("099888777");
        restauranteDto1.setDireccion("direccion1");
        restauranteDto1.setConfirmado(false);
        restauranteDto1.setEmail("resturante7@gmail.com");
        restauranteDto1.setFoto("url");
        restauranteDto1.setDescripcionMenues("descripcion");
        restauranteDto1.setHorario("horario");
        restauranteDto1.setNroHabilitacion("45");
        restauranteDto1.setPrecioEnvio(566);
        restauranteDto1.setRazonSocial("razon social");
        restauranteDto1.setRut("232334");

        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setNombreRestaurante("restaurante7");
        restaurante.setCelular("099888777");
        restaurante.setDireccion("direccion1");
        restaurante.setConfirmado(true);
        restaurante.setEmail("resturante7@gmail.com");
        restaurante.setFoto("url");
        restaurante.setDescripcionMenues("descripcion");
        restaurante.setHorario("horario");
        restaurante.setNroHabilitacion("45");
        restaurante.setPrecioEnvio(566);
        restaurante.setRazonSocial("razon social");
        restaurante.setRut("232334");

        administradorController.aprobarRestaurante("resturante7@gmail.com");


        Assertions.assertEquals(true, restaurante.getConfirmado());


    }

    @Test
    void rechazarRestaurante() {
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
        restaurante.setEmail("restaurante9@gmail.com");

        RechazoBloqueoDTO rechazoBloqueoDTO = new RechazoBloqueoDTO("restaurante9@gmail.com", "porque me pintó");

        ResponseEntity<?> response = administradorController.rechazarRestaurante(rechazoBloqueoDTO, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void bloquearRestaurante() {
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
        restaurante.setEmail("restaurante9@gmail.com");

        RechazoBloqueoDTO rechazoBloqueoDTO = new RechazoBloqueoDTO("restaurante9@gmail.com", "porque me pintó");

        ResponseEntity<?> response = administradorController.bloquearRestaurante(rechazoBloqueoDTO, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

        ObjectError error = null;
        mockBindingResult.addError(error);
        RechazoBloqueoDTO rechazoBloqueoDTO2 = new RechazoBloqueoDTO("", "");
        ResponseEntity<?> response2 = administradorController.bloquearRestaurante(rechazoBloqueoDTO2, mockBindingResult);
        String salida2 = "<200 OK OK,[]>";
        System.out.println(response2.toString());
        Assertions.assertEquals(salida2, response2.toString());
    }

    @Test
    void bloquearCliente() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setEmail("cliente@gmail.com");
        cliente.setRol(UsuarioEntity.Rol.Cliente);
        cliente.setPassWord("123");
        cliente.setBloqueado(false);
        cliente.setNombre("Cliente");
        cliente.setApellido("Apellido");
        cliente.setCelular("09999");
        cliente.setFoto("url");

        RechazoBloqueoDTO rechazoBloqueoDTO = new RechazoBloqueoDTO("cliente@gmail.com", "porque me pintó");

        ResponseEntity<?> response = administradorController.bloquearCliente(rechazoBloqueoDTO, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void listarRestaurantesPendientes() {
        RestauranteDto restauranteDto1 = new RestauranteDto();
        restauranteDto1.setNombreRestaurante("restaurante3");
        restauranteDto1.setCelular("099888777");
        restauranteDto1.setDireccion("direccion1");
        restauranteDto1.setConfirmado(false);
        restauranteDto1.setEmail("resturante4@gmail.com");
        restauranteDto1.setFoto("url");
        restauranteDto1.setDescripcionMenues("descripcion");
        restauranteDto1.setHorario("horario");
        restauranteDto1.setNroHabilitacion("45");
        restauranteDto1.setPrecioEnvio(566);
        restauranteDto1.setRazonSocial("razon social");
        restauranteDto1.setRut("232334");

        RestauranteDto restauranteDto2 = new RestauranteDto();
        restauranteDto2.setNombreRestaurante("restaurante3");
        restauranteDto2.setCelular("099888777");
        restauranteDto2.setDireccion("direccion1");
        restauranteDto2.setConfirmado(false);
        restauranteDto2.setEmail("resturante5@gmail.com");
        restauranteDto2.setFoto("url");
        restauranteDto2.setDescripcionMenues("descripcion");
        restauranteDto2.setHorario("horario");
        restauranteDto2.setNroHabilitacion("45");
        restauranteDto2.setPrecioEnvio(566);
        restauranteDto2.setRazonSocial("razon social");
        restauranteDto2.setRut("232334");

        List<RestauranteDto> restaurantes = new ArrayList<RestauranteDto>();
        restaurantes.add(restauranteDto1);
        restaurantes.add(restauranteDto2);

        Mockito.when(restauranteService.findByConfirmado(false)).thenReturn(restaurantes);

        List<RestauranteDto> restaurantes2 = new ArrayList<RestauranteDto>();
        restaurantes2 = administradorController.listarRestaurantesPendientes();

        Assertions.assertEquals(restaurantes, restaurantes2);
    }

    @Test
    void registroAdmin() {
        NuevoAdminDto nuevoAdminDto = new NuevoAdminDto();
        nuevoAdminDto.setPassword("1234");
        nuevoAdminDto.setEmail("administardor@gmail.com");
        ResponseEntity<?> response = administradorController.registroAdmin(nuevoAdminDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void usuariosBusqueda() {
        BusquedaUsuarioDto busquedaUsuarioDto = new BusquedaUsuarioDto();
        busquedaUsuarioDto.setTipoCliente(true);
        busquedaUsuarioDto.setTextoBusqueda("");
        busquedaUsuarioDto.setBloqueado(false);

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setBloqueado(false);
        usuarioDto.setCelular("0999");
        usuarioDto.setNombre("Juan");
        usuarioDto.setEmail("cliente@gmail.com");
        usuarioDto.setCalificacionGlobal(4.0);
        Date fecha = new Date();
        usuarioDto.setFechaCreacion(fecha);

        UsuarioDto usuarioDto2 = new UsuarioDto();
        usuarioDto2.setBloqueado(false);
        usuarioDto2.setCelular("0999");
        usuarioDto2.setNombre("Juan");
        usuarioDto2.setEmail("cliente2@gmail.com");
        usuarioDto2.setCalificacionGlobal(4.0);
        usuarioDto2.setFechaCreacion(fecha);

        List<UsuarioDto> listaUsuarios = new ArrayList<UsuarioDto>();
        listaUsuarios.add(usuarioDto);
        listaUsuarios.add(usuarioDto2);

        Mockito.when(clienteService.buscarClientes(busquedaUsuarioDto.getBloqueado(), busquedaUsuarioDto.getTextoBusqueda())).thenReturn(listaUsuarios);

        List<UsuarioDto> clientes = administradorController.usuariosBusqueda(busquedaUsuarioDto);
        Assertions.assertEquals(listaUsuarios, clientes);

        BusquedaUsuarioDto busquedaUsuarioDtoRestaurante = new BusquedaUsuarioDto();
        busquedaUsuarioDtoRestaurante.setTipoCliente(false);
        busquedaUsuarioDtoRestaurante.setTextoBusqueda("");
        busquedaUsuarioDtoRestaurante.setBloqueado(false);

        Mockito.when(restauranteService.buscarRestaurantes(busquedaUsuarioDto.getBloqueado(), busquedaUsuarioDto.getTextoBusqueda())).thenReturn(listaUsuarios);

        List<UsuarioDto> restaurantes = administradorController.usuariosBusqueda(busquedaUsuarioDtoRestaurante);
        Assertions.assertEquals(listaUsuarios, restaurantes);
    }

    @Test
    void eliminarUsuario(){
        EliminarUsuarioDto eliminarUsuarioDto = new EliminarUsuarioDto();
        eliminarUsuarioDto.setIdUsuario("cliente@gmail.com");
        eliminarUsuarioDto.setCliente(true);
        administradorController.eliminarUsuario(eliminarUsuarioDto);
    }

}