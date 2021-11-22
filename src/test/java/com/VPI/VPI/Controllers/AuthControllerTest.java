package com.VPI.VPI.Controllers;

import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Security.Jwt.JwtUtil;
import com.VPI.VPI.Security.UserDetailsServiceImpl;
import com.VPI.VPI.Services.*;
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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class AuthControllerTest {

    @InjectMocks
    AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    UsuarioService usuarioService;
    @Mock
    ClienteService clienteService;
    @Mock
    RestauranteService restauranteService;
    @Mock
    private EmailService emailService;

    @Mock
    private IAWSS3Service awss3Service;

    @Value("${spring.mail.username}")
    private String mailFrom;

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
    void user() {
    }

    @Test
    void registroRestaurante() {
        MockMultipartFile multipartFile
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        String res = "{\"email\":\"restaurante@gmail.com\",\"password\":\"123\",\"nombreRestaurante\":\"res\",\"nroHabilitacion\":\"123\",\"razonSocial\":\"rs\",\"rut\":\"232\",\"direccion\":\"dir\",\"descripcionMenues\":\"descm\",\"celular\":\"987654\"}";
        Mockito.when(awss3Service.uploadFile(multipartFile)).thenReturn("ok");
        try {
            ResponseEntity<?> response =  authController.registroRestaurante(multipartFile, res);
            String salida = "<200 OK OK,[]>";
            Assertions.assertEquals(salida, response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void registroRestauranteSinFoto() {
        NuevoRestauranteDto nuevoRestauranteDto = new NuevoRestauranteDto();
        nuevoRestauranteDto.setNombreRestaurante("restaurante");
        nuevoRestauranteDto.setUrlFoto("url");
        nuevoRestauranteDto.setEmail("restaurante@gmail.com");
        nuevoRestauranteDto.setCelular("098766543");
        nuevoRestauranteDto.setDireccion("casa");
        nuevoRestauranteDto.setDescripcionMenues("menus");
        nuevoRestauranteDto.setHorario("lunes");
        nuevoRestauranteDto.setNroHabilitacion("232");
        nuevoRestauranteDto.setPassword("2343");
        nuevoRestauranteDto.setPrecioEnvio(234);
        nuevoRestauranteDto.setRazonSocial("razón social");
        nuevoRestauranteDto.setRut("12343");
        ResponseEntity<?> response = authController.registroRestauranteSinFoto(nuevoRestauranteDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

    }

    @Test
    void registroCliente() {
        MockMultipartFile multipartFile
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        String cliente = "{\"email\":\"cliente@gmail.com\",\"password\":\"123\",\"nombre\":\"cli\",\"apellido\":\"apell\",\"celular\":\"09070\",\"urlFoto\":\"url\"}";
        Mockito.when(awss3Service.uploadFile(multipartFile)).thenReturn("ok");
        try {
            ResponseEntity<?> response =  authController.registroCliente(multipartFile, cliente);
            String salida = "<200 OK OK,[]>";
            Assertions.assertEquals(salida, response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void registroClienteSinFoto() {
        NuevoClienteDto nuevoClienteDto = new NuevoClienteDto();
        nuevoClienteDto.setUrlFoto("url");
        nuevoClienteDto.setCelular("098765377");
        nuevoClienteDto.setEmail("cliente@gmail.com");
        nuevoClienteDto.setNombre("nombre");
        nuevoClienteDto.setApellido("apellido");
        ResponseEntity<?> response = authController.registroClienteSinFoto(nuevoClienteDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void login() {
        LoginView loginView = new LoginView();
        loginView.setEmail("cliente@gmail.com");
        loginView.setPassword("1234");
        ResponseEntity<JwtView> response = authController.login(loginView, mockBindingResult);
        String salida = "<400 BAD_REQUEST Bad Request,No existe ningun usuario con el correo ingresado,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void sendEmail() {

    }

    @Test
    void sendEmailTemplate() {
        EmailDto emailDto = new EmailDto();
        emailDto.setMailFrom("from@gmail.com");
        emailDto.setPasswordTemporal("123");
        emailDto.setMailTo("to@gmail.com");
        emailDto.setSubject("sub");
        emailDto.setUserName("cliente@gmail.com");
        ResponseEntity<?> response = authController.sendEmailTemplate(emailDto);
        String salida = "<400 BAD_REQUEST Bad Request,No existe ningun usuario con el correo ingresado,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void changePassword() {
        ChangePassView changePassView = new ChangePassView();
        changePassView.setPassword("12345");
        changePassView.setConfirmPass("12345");
        changePassView.setPasswordTemporal("123");
        ResponseEntity<?> response = authController.changePassword(changePassView, mockBindingResult);
        String salida = "<404 NOT_FOUND Not Found,No existe ningun usuario con estas credenciales,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void altaMenuSinFoto() {
        NuevoMenuDto nuevoMenuDto = new NuevoMenuDto();
        nuevoMenuDto.setImagen("url");
        nuevoMenuDto.setNombre("menu");
        nuevoMenuDto.setCategoria("hamburguesa");
        nuevoMenuDto.setCosto(300.0);
        nuevoMenuDto.setDescripcion("desc");
        nuevoMenuDto.setPromocion(false);
        nuevoMenuDto.setRestaurante("restaurante");

        ResponseEntity<?> response = authController.altaMenuSinFoto(nuevoMenuDto, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    void altaMenu() {
    }

    @Test
    void convertMultiPartToFile() {
    }
}