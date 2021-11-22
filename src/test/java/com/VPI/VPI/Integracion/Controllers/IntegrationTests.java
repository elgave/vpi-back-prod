package com.VPI.VPI.Integracion.Controllers;

import com.VPI.VPI.Controllers.AdministradorController;
import com.VPI.VPI.Controllers.AuthController;
import com.VPI.VPI.Controllers.ClienteController;
import com.VPI.VPI.Controllers.RestauranteController;
import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Entities.*;
import com.VPI.VPI.Repositories.IPedidoRepository;
import com.VPI.VPI.Security.Jwt.JwtUtil;
import com.VPI.VPI.Security.UserDetailsServiceImpl;
import com.VPI.VPI.Services.*;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IntegrationTests {

    /*********  Nota  **********/
    // Como los identificadores de varias entidades son autonumerados, se tienen que cambiar a mano los id de este archivo. Para eso hacer ctrl f y buscar "//cambiar" poniendo el id correspondiente de la bd

    @Autowired
    AuthController authController = new AuthController();

    @Autowired
    ClienteController clienteController = new ClienteController();

    @Autowired
    private AuthenticationManager authenticationManager = new AuthenticationManager() {
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return null;
        }
    };
    @Autowired
    private UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
    @Autowired
    private JwtUtil jwtUtil = new JwtUtil();
    @Autowired
    IUsuarioService usuarioService = new UsuarioService();
    @Autowired
    ClienteService clienteService = new ClienteService();
    @Autowired
    RestauranteService restauranteService = new RestauranteService();
    @Autowired
    private EmailService emailService = new EmailService();
    @Autowired
    PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence charSequence) {
            return null;
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return false;
        }
    };
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();
    @Value("${spring.mail.username}")
    private String mailFrom;

    @Autowired
    private IAWSS3Service awss3Service;

    @Autowired
    RestauranteController restauranteController = new RestauranteController();

    @Mock
    private BindingResult mockBindingResult;

    @Autowired
    ClienteService iClienteService = new ClienteService() {
        @Override
        public ClienteEntity createCliente(NuevoClienteDto clienteDto) {
            return null;
        }

        @Override
        public boolean isBloqueado(String email) {
            return false;
        }

        @Override
        public PedidoEntity altaPedido(AltaPedidoDto altaPedidoDto, String idRest, String idCliente) {
            return null;
        }

        @Override
        public List<RestauranteDto> getRestaurantes() {
            return null;
        }

        @Override
        public List<MenuDto> getMenusResturante(String nomRestaurante) {
            return null;
        }

        @Override
        public List<AltaAgregadoDto> agragadosByMenu(Integer idMenu) {
            return null;
        }

        @Override
        public void agregarFavorito(String idCliente, String idRestaurante) {

        }

        @Override
        public void agregarDireccion(DireccionDto direccion) {

        }

        @Override
        public ReclamoEntity altaReclamo(ReclamoDto reclamoDto) {
            return null;
        }

        @Override
        public RestauranteDto getDatosRestaurante(String nomRestaurante) {
            return null;
        }

        @Override
        public List<DireccionDto> getDirecciones(String email) {
            return null;
        }

        @Override
        public List<UsuarioDto> buscarClientes(Boolean bloqueado, String busqueda) {
            return null;
        }

        @Override
        public Double obtenerCalificacionGlobal(String idCliente) {
            return null;
        }

        @Override
        public List<PedidoDto> getPedidos(String email) {
            return null;
        }

        @Override
        public void calificarRestaurante(CalificacionDto calificacionDto) {

        }

        @Override
        public boolean isFavorito(FavoritoDto favoritoDto) {
            return false;
        }

        @Override
        public Integer cantDirecciones(String email) {
            return null;
        }

        @Override
        public void eliminarCalificacionRestaurante(CalificacionDto calificacionDto) {

        }

        @Override
        public void eliminarDireccion(Integer id) {

        }

        @Override
        public void modificarDireccion(ModificarDireccionDto modificarDireccionDto) {

        }

        @Override
        public ClienteDto obtenerMisDatos(String email) {
            return null;
        }

        @Override
        public Boolean puedeCalificar(CalificacionDto calificacionDto) {
            return null;
        }

        @Override
        public Integer obtenerCalificacionRestaurante(CalificacionDto calificacionDto) {
            return null;
        }

        @Override
        public void eliminarMiCuenta(String idCliente) {

        }

        @Override
        public void quitarFavorito(String idCliente, String idRestaurante) {

        }

        @Override
        public List<RestauranteDto> restaurantesFavoritos(String idCliente) {
            return null;
        }

        @Override
        public List<RestauranteDto> buscarRestaurantesAbiertos(String busqueda) {
            return null;
        }
    };
    IPedidoRepository pedidoRepository = new IPedidoRepository() {
        @Override
        public Optional<PedidoEntity> findByIdPedido(Integer id) {
            return Optional.empty();
        }

        @Override
        public List<PedidoEntity> findByRestauranteOrderByIdPedidoDesc(RestauranteEntity restaurante) {
            return null;
        }

        @Override
        public List<PedidoEntity> findByClienteOrderByIdPedidoDesc(ClienteEntity cliente) {
            return null;
        }

        @Override
        public List<PedidoEntity> findByRestauranteAndEstadoNotOrderByIdPedidoDesc(RestauranteEntity restauranteEntity, PedidoEntity.EstadoPedido estado) {
            return null;
        }

        @Override
        public List<PedidoEntity> findByRestauranteAndEstadoOrderByIdPedidoDesc(RestauranteEntity restauranteEntity, PedidoEntity.EstadoPedido estado) {
            return null;
        }

        @Override
        public List<PedidoEntity> findByRestauranteAndCliente(RestauranteEntity restauranteEntity, ClienteEntity clienteEntity) {
            return null;
        }

        @Override
        public Integer cantPedidos(String restaurante, PedidoEntity.EstadoPedido estado) {
            return null;
        }

        @Override
        public Integer tiempoDeEntregaTotal(String restaurante) {
            return null;
        }

        @Override
        public List<PedidoEntity> findByRestauranteAndEstadoAndFechaLessThanEqualAndFechaGreaterThanEqualAndPagoAcreditadoTrue(RestauranteEntity restaurante, PedidoEntity.EstadoPedido estado, Date fechaHasta, Date fechaDesde) {
            return null;
        }

        @Override
        public List<PedidoEntity> findByRestauranteAndEstadoAndPagoAcreditadoTrue(RestauranteEntity restaurante, PedidoEntity.EstadoPedido estado) {
            return null;
        }

        @Override
        public List<PedidoEntity> findByRestauranteAndEstadoAndPagoAcreditadoTrueAndPagoOnline(RestauranteEntity restaurante, PedidoEntity.EstadoPedido estado, Boolean pagoOnline) {
            return null;
        }

        @Override
        public List<PedidoEntity> findByRestauranteAndEstadoAndFechaLessThanEqualAndFechaGreaterThanEqualAndPagoAcreditadoTrueAndPagoOnline(RestauranteEntity restaurante, PedidoEntity.EstadoPedido estado, Date fechaHasta, Date fechaDesde, Boolean pagoOnline) {
            return null;
        }

        @Override
        public List<PedidoEntity> findAll() {
            return null;
        }

        @Override
        public List<PedidoEntity> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<PedidoEntity> findAllById(Iterable<Integer> iterable) {
            return null;
        }

        @Override
        public <S extends PedidoEntity> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends PedidoEntity> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public <S extends PedidoEntity> List<S> saveAllAndFlush(Iterable<S> iterable) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<PedidoEntity> iterable) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Integer> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public PedidoEntity getOne(Integer integer) {
            return null;
        }

        @Override
        public PedidoEntity getById(Integer integer) {
            return null;
        }

        @Override
        public <S extends PedidoEntity> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends PedidoEntity> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<PedidoEntity> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends PedidoEntity> S save(S s) {
            return null;
        }

        @Override
        public Optional<PedidoEntity> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(PedidoEntity pedidoEntity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> iterable) {

        }

        @Override
        public void deleteAll(Iterable<? extends PedidoEntity> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends PedidoEntity> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends PedidoEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends PedidoEntity> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends PedidoEntity> boolean exists(Example<S> example) {
            return false;
        }
    };
    @Autowired
    private AdministradorController administradorController = new AdministradorController();

    @Autowired
    IAdminService adminService = new AdminService();

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void user() {
    }



    /*************************     Controlador Auth    *************************/

    @Test
    @Order(1)
    void registroRestaurante() {
        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return "archivo.png";
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        String res = "{\"email\":\"restauranteVPITesting@gmail.com\",\"password\":\"123\",\"nombreRestaurante\":\"restauranteVPITesting\",\"nroHabilitacion\":\"1235\",\"razonSocial\":\"rs\",\"rut\":\"234\",\"direccion\":\"dir\",\"descripcionMenues\":\"descm\",\"celular\":\"987624\"}";
        try {
            ResponseEntity<?> response = authController.registroRestaurante(multipartFile, res);
            String salida = "<200 OK OK,[]>";
            Assertions.assertEquals(salida, response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String res3 = "{\"email\":\"restauranteVPITesting4@gmail.com\",\"password\":\"123\",\"nombreRestaurante\":\"restauranteVPITesting4\",\"nroHabilitacion\":\"12345\",\"razonSocial\":\"rs25\",\"rut\":\"23224\",\"direccion\":\"dir\",\"descripcionMenues\":\"descm\",\"celular\":\"98765424\"}";
        try {
            ResponseEntity<?> response4 = authController.registroRestaurante(multipartFile, res3);
            String salida4 = "<200 OK OK,[]>";
            Assertions.assertEquals(salida4, response4.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String res4 = "{\"email\":\"restauranteVPITesting5@gmail.com\",\"password\":\"123\",\"nombreRestaurante\":\"restauranteVPITesting5\",\"nroHabilitacion\":\"12342\",\"razonSocial\":\"rs22\",\"rut\":\"23222\",\"direccion\":\"dir\",\"descripcionMenues\":\"descm\",\"celular\":\"98765423\"}";
        try {
            ResponseEntity<?> response5 = authController.registroRestaurante(multipartFile, res4);
            String salida5 = "<200 OK OK,[]>";
            Assertions.assertEquals(salida5, response5.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(2)
    void registroRestauranteSinFoto() {
        NuevoRestauranteDto nuevoRestauranteDto = new NuevoRestauranteDto();
        nuevoRestauranteDto.setNombreRestaurante("restauranteVPITesting2SF");
        nuevoRestauranteDto.setUrlFoto("url");
        nuevoRestauranteDto.setEmail("restauranteVPITesting2@gmail.com");
        nuevoRestauranteDto.setCelular("0987665433");
        nuevoRestauranteDto.setDireccion("casa");
        nuevoRestauranteDto.setDescripcionMenues("menus");
        nuevoRestauranteDto.setHorario("lunes");
        nuevoRestauranteDto.setNroHabilitacion("2323");
        nuevoRestauranteDto.setPassword("2343");
        nuevoRestauranteDto.setPrecioEnvio(234);
        nuevoRestauranteDto.setRazonSocial("razón social");
        nuevoRestauranteDto.setRut("123435");
        ResponseEntity<?> response = authController.registroRestauranteSinFoto(nuevoRestauranteDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

        ResponseEntity<?> response2 = authController.registroRestauranteSinFoto(nuevoRestauranteDto);
        String salida2 = "<400 BAD_REQUEST Bad Request,El email ya se encuentra registrado,[]>";
        Assertions.assertEquals(salida2, response2.toString());

    }

    @Test
    @Order(3)
    void registroCliente() {

        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return "archivo.png";
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        String cliente = "{\"email\":\"clienteVPITesting@gmail.com\",\"password\":\"123\",\"nombre\":\"cli\",\"apellido\":\"apell\",\"celular\":\"09070\",\"urlFoto\":\"url\"}";

        try {
            ResponseEntity<?> response =  authController.registroCliente(multipartFile, cliente);
            String salida = "<200 OK OK,[]>";
            Assertions.assertEquals(salida, response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ResponseEntity<?> response2 =  authController.registroCliente(multipartFile, cliente);
            String salida2 = "<400 BAD_REQUEST Bad Request,El email ya se encuentra registrado,[]>";
            Assertions.assertEquals(salida2, response2.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(4)
    void registroClienteSinFoto() {
        NuevoClienteDto nuevoClienteDto = new NuevoClienteDto();
        nuevoClienteDto.setUrlFoto("url");
        nuevoClienteDto.setCelular("098765377");
        nuevoClienteDto.setEmail("clienteVPITesting2@gmail.com");
        nuevoClienteDto.setNombre("nombre");
        nuevoClienteDto.setApellido("apellido");
        nuevoClienteDto.setPassword("123456");
        ResponseEntity<?> response = authController.registroClienteSinFoto(nuevoClienteDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

        ResponseEntity<?> response2 = authController.registroClienteSinFoto(nuevoClienteDto);
        String salida2 = "<400 BAD_REQUEST Bad Request,El email ya se encuentra registrado,[]>";
        Assertions.assertEquals(salida2, response2.toString());

        NuevoClienteDto nuevoClienteDto2 = new NuevoClienteDto();
        nuevoClienteDto2.setUrlFoto("url");
        nuevoClienteDto2.setCelular("0987653773");
        nuevoClienteDto2.setEmail("clienteVPITesting3@gmail.com");
        nuevoClienteDto2.setNombre("nombre");
        nuevoClienteDto2.setApellido("apellido");
        nuevoClienteDto2.setPassword("123456");
        authController.registroClienteSinFoto(nuevoClienteDto2);
    }

    @Test
    @Order(5)
    void login() {
        LoginView loginView = new LoginView();
        loginView.setEmail("restauranteVPITesting2@gmail.com");
        loginView.setPassword("123");
        ResponseEntity<JwtView> response = authController.login(loginView, mockBindingResult);
        String salida = "<400 BAD_REQUEST Bad Request,El restaurante no se encuentra confirmado,[]>";
        Assertions.assertEquals(salida, response.toString());

        LoginView loginView2 = new LoginView();
        loginView2.setEmail("clienteVPITesting2@gmail.com");
        loginView2.setPassword("123456");
        ResponseEntity<JwtView> response2 = authController.login(loginView2, mockBindingResult);
        HttpStatus status = response2.getStatusCode();
        String salida2 = "200 OK";
        Assertions.assertEquals(salida2, status.toString());

    }
    @Test
    void sendEmail() {
    }
    @Test
    void sendEmailTemplate() {
        EmailDto emailDto = new EmailDto();
        emailDto.setMailFrom("notificacionesVPI@gmail.com");
        emailDto.setPasswordTemporal("123");
        emailDto.setMailTo("to@gmail.com");
        emailDto.setSubject("sub");
        emailDto.setUserName("clienteTestingNoExistente@gmail.com");
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
    @Order(6)
    void altaMenuSinFoto() {
        NuevoMenuDto nuevoMenuDto = new NuevoMenuDto();
        nuevoMenuDto.setImagen("url");
        nuevoMenuDto.setNombre("MenuVPITestingSF");
        nuevoMenuDto.setCategoria("Sushi");
        nuevoMenuDto.setCosto(200.0);
        nuevoMenuDto.setDescripcion("desc");
        nuevoMenuDto.setPromocion(false);
        nuevoMenuDto.setRestaurante("restauranteVPITesting2@gmail.com");

        ResponseEntity<?> response = authController.altaMenuSinFoto(nuevoMenuDto, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    @Order(7)
    void altaMenu() {
        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return "archivo.png";
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        String menuDto = "{\"nombre\":\"MenuVPITesting1\",\"costo\":\"300.0\",\"restaurante\":\"restauranteVPITesting2@gmail.com\",\"promocion\":\"false\",\"descuento\":\"0.0\",\"categoria\":\"Sushi\",\"imagen\":\"https://mahatmarice.com/wp-content/uploads/2019/05/Vegetarian-Sushi-Rolls.jpg\",\"descripcion\":\"desc\"}";

        try {
            ResponseEntity<?> response = authController.altaMenu(multipartFile, menuDto);
            String salida = "<200 OK OK,[]>";
            Assertions.assertEquals(salida, response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String menu = "{\"categoria\":\"Hamburguesas\",\"promocion\":\"false\",\"descripcion\":\"desc\",\"costo\":\"222\",\"descuento\":\"0\",\"imagen\":\"url\",\"restaurante\":\"restauranteVPITesting2@gmail.com\"}";
        try {
            ResponseEntity<?> response =  restauranteController.altaMenu(multipartFile, menu);
            String salida = "<200 OK OK,[]>";
            Assertions.assertEquals(salida, response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    /*************************     Controlador Restaurante   *************************/

    @Test
    @Order(8)
    void abrirCerrarRestaurante() {
        AbiertoDto abiertoDto = new AbiertoDto();
        abiertoDto.setAbierto(true);
        abiertoDto.setIdRestaurante("restauranteVPITesting@gmail.com");
        restauranteController.abrirCerrarRestaurante(abiertoDto);

        Boolean isOpen = restauranteController.isAbierto("restauranteVPITesting@gmail.com");

        Assertions.assertEquals(true, isOpen);

        AbiertoDto abiertoDto2 = new AbiertoDto();
        abiertoDto2.setAbierto(false);
        abiertoDto2.setIdRestaurante("restauranteVPITesting2@gmail.com");
        restauranteController.abrirCerrarRestaurante(abiertoDto2);

        Boolean isOpen2 = restauranteController.isAbierto("restauranteVPITesting2@gmail.com");

        Assertions.assertEquals(false, isOpen2);

        AbiertoDto abiertoDto3 = new AbiertoDto();
        abiertoDto3.setAbierto(true);
        abiertoDto3.setIdRestaurante("restauranteVPITesting2@gmail.com");
        restauranteController.abrirCerrarRestaurante(abiertoDto3);

        Boolean isOpen3 = restauranteController.isAbierto("restauranteVPITesting2@gmail.com");

    }

    @Test
    @Order(9)
    void altaAgregado() {
        AltaAgregadoDto altaAgregadoDto = new AltaAgregadoDto();
        altaAgregadoDto.setRestaurante("restauranteVPITesting@gmail.com");
        altaAgregadoDto.setCosto(40.0);
        altaAgregadoDto.setNombre("agregadoTesting");
        altaAgregadoDto.setId(1);

        ResponseEntity<?> response = restauranteController.altaAgregado(altaAgregadoDto);
        List<AltaAgregadoDto> agregados = restauranteController.listarAgregados("restauranteVPITesting@gmail.com");

        Assertions.assertEquals("agregadoTesting", agregados.get(0).getNombre());

        AltaAgregadoDto altaAgregadoDto2 = new AltaAgregadoDto();
        altaAgregadoDto2.setRestaurante("restauranteVPITesting2@gmail.com");
        altaAgregadoDto2.setCosto(80.0);
        altaAgregadoDto2.setNombre("agregadoTesting2");
        altaAgregadoDto2.setId(2);

        restauranteController.altaAgregado(altaAgregadoDto2);
    }

    @Test
    @Order(10)
    void calificarCliente() {
        CalificacionDto calificacionDto = new CalificacionDto();
        calificacionDto.setCliente(false);
        calificacionDto.setPuntuacion(4);
        calificacionDto.setIdCliente("clienteVPITesting@gmail.com");
        calificacionDto.setIdRestaurante("restauranteVPITesting@gmail.com");

        restauranteController.calificarCliente(calificacionDto, mockBindingResult);
        Integer calificacion = restauranteController.obtenerCalificacionCliente(calificacionDto);

        Assertions.assertEquals(4, calificacion);
    }

    @Test
    @Order(11)
    void eliminarCalificacionCliente() {
        CalificacionDto calificacionDto = new CalificacionDto();
        calificacionDto.setCliente(false);
        calificacionDto.setPuntuacion(4);
        calificacionDto.setIdCliente("clienteVPITesting@gmail.com");
        calificacionDto.setIdRestaurante("restauranteVPITesting@gmail.com");

        restauranteController.eliminarCalificacionCliente(calificacionDto, mockBindingResult);
        Integer calificacion = restauranteController.obtenerCalificacionCliente(calificacionDto);

        Assertions.assertEquals(0, calificacion);

    }

    @Test
    @Order(13)
    void modificarAgregado() {

        ModificacionAgregadoDto modificacionAgregadoDto = new ModificacionAgregadoDto();
        modificacionAgregadoDto.setCosto(50.0);
        //Cambiar + 2
        modificacionAgregadoDto.setId(40);

        restauranteController.modificarAgregado(modificacionAgregadoDto);
    }
    @Test
    @Order(14)
    void asociarAgregados() {
        AsociarAgregadoDto asociarAgregadoDto = new AsociarAgregadoDto();
        List<Integer> lista = new ArrayList<Integer>();
        //cambiar + 2
        lista.add(40);

        //cambiar + 3
        asociarAgregadoDto.setIdMenu(77);
        asociarAgregadoDto.setIdAgregados(lista);

        ResponseEntity<?> response = restauranteController.asociarAgregados(asociarAgregadoDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

    }

    @Test
    @Order(17)
    void confirmarPedido() throws IOException {

        ConfirmarPedidoDto confirmarPedidoDto = new ConfirmarPedidoDto();
        confirmarPedidoDto.setTiempoE(35);
        //cambiar +3
        confirmarPedidoDto.setIdPedido(57);
        restauranteController.confirmarPedido(confirmarPedidoDto);

        List<PedidoDto> pedidos2 = clienteController.getPedidos("clienteVPITesting@gmail.com");
        Assertions.assertEquals(PedidoEntity.EstadoPedido.Confirmado, pedidos2.get(2).getEstado());

    }

    @Test
    @Order(24)
    void pagoEnEfectivo() {
        //cambiar +3
        restauranteController.pagoEnEfectivo(57);
        RestauranteService restauranteService = new RestauranteService();
        List<PedidoDto> pedidos = clienteController.getPedidos("clienteVPITesting@gmail.com");
        Assertions.assertEquals(true, pedidos.get(2).getPagoAcreditado());
    }

    @Test
    @Order(27)
    void eliminarMenu() {
        //cambiar +3
        ResponseEntity<?> response = restauranteController.eliminarMenu(78);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

        List<MenuDto> menusObtenidos = clienteController.getMenusRestaurante("restauranteVPITesting2SF");

        Assertions.assertEquals(2, menusObtenidos.size());

    }

    @Test
    @Order(30)
    void obtenerCalificacionGlobal() {
        Double calificacion = restauranteController.obtenerCalificacionGlobal("restauranteVPITesting2@gmail.com");
        Assertions.assertEquals("3.0", calificacion.toString());
    }

    @Test
    @Order(31)
    void eliminarAgregado() {
        //Cambiar + 2
        restauranteController.eliminarAgregado(40);
        List<AltaAgregadoDto> agregados = restauranteController.listarAgregados("restauranteVPITesting2@gmail.com");
        Assertions.assertEquals(0, agregados.size());
    }

    @Test
    @Order(33)
    void rechazarPedido() throws IOException {
        RechazoPedidoDto rechazoPedidoDto = new RechazoPedidoDto();
        //Cambiar +3
        rechazoPedidoDto.setIdPedido(58);
        rechazoPedidoDto.setMotivoRechazo("No tenemos stock");
        restauranteController.rechazarPedido(rechazoPedidoDto);
        List<PedidoDto> pedidos = clienteController.getPedidos("clienteVPITesting@gmail.com");
        Assertions.assertEquals("Rechazado", pedidos.get(1).getEstado().toString());
    }


    /*************************     Controlador Cliente     *************************/

    @Test
    @Order(15)
    void agregarDireccion() {
        DireccionDto direccionDto = new DireccionDto();
        direccionDto.setNombre("Mi Casa");
        direccionDto.setPrincipal(true);
        direccionDto.setNumero("1");
        direccionDto.setCalle("Av italia");
        direccionDto.setBarrio("Barrio");
        direccionDto.setEsquina("Esquina");
        direccionDto.setApto("Apto");
        direccionDto.setIdCliente("clienteVPITesting@gmail.com");

        ResponseEntity<?> response = clienteController.agregarDireccion(direccionDto);
        List<DireccionDto> direcciones = clienteController.getDirecciones("clienteVPITesting@gmail.com");
        Boolean principal = direcciones.get(0).getPrincipal();
        Assertions.assertEquals(true, principal);

        DireccionDto direccionDto2 = new DireccionDto();
        direccionDto2.setNombre("Mi trabajo");
        direccionDto2.setPrincipal(false);
        direccionDto2.setNumero("2");
        direccionDto2.setCalle("Av italia");
        direccionDto2.setBarrio("Barrio");
        direccionDto2.setEsquina("Esquina");
        direccionDto2.setApto("Apto");
        direccionDto2.setIdCliente("clienteVPITesting@gmail.com");

        ResponseEntity<?> response2 = clienteController.agregarDireccion(direccionDto2);
        List<DireccionDto> direcciones2 = clienteController.getDirecciones("clienteVPITesting@gmail.com");
        Integer cant = clienteController.getCantDirecciones("clienteVPITesting@gmail.com");
        Assertions.assertEquals(2, cant);
    }

    @Test
    @Order(16)
    void altaPedido() {

        List<AltaItemDto> itemDtos = new ArrayList<AltaItemDto>();
        AltaItemDto altaItemDto = new AltaItemDto();
        altaItemDto.setNombre("MenuVPITestingSF");
        altaItemDto.setPrecio(400.0);
        altaItemDto.setCantidad(2);
        itemDtos.add(altaItemDto);


        AltaPedidoDto altaPedidoDto = new AltaPedidoDto();
        altaPedidoDto.setIdPedido(1);
        altaPedidoDto.setCliente("clienteVPITesting@gmail.com");
        altaPedidoDto.setRestaurante("restauranteVPITesting2@gmail.com");
        altaPedidoDto.setPagoOnline(false);
        altaPedidoDto.setComentario("comentario");
        altaPedidoDto.setDireccion("Mi Casa");
        altaPedidoDto.setItems(itemDtos);

        ResponseEntity<?> response = clienteController.altaPedido(altaPedidoDto, mockBindingResult);

        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

        AltaPedidoDto altaPedidoDto2 = new AltaPedidoDto();
        altaPedidoDto2.setIdPedido(2);
        altaPedidoDto2.setCliente("clienteVPITesting@gmail.com");
        altaPedidoDto2.setRestaurante("restauranteVPITesting2@gmail.com");
        altaPedidoDto2.setPagoOnline(false);
        altaPedidoDto2.setComentario("comentario");
        altaPedidoDto2.setDireccion("Mi Casa");
        altaPedidoDto2.setItems(itemDtos);

        AltaPedidoDto altaPedidoDto3 = new AltaPedidoDto();
        altaPedidoDto3.setIdPedido(2);
        altaPedidoDto3.setCliente("clienteVPITesting@gmail.com");
        altaPedidoDto3.setRestaurante("restauranteVPITesting2@gmail.com");
        altaPedidoDto3.setPagoOnline(false);
        altaPedidoDto3.setComentario("comentario");
        altaPedidoDto3.setDireccion("Mi Casa");
        altaPedidoDto3.setItems(itemDtos);

        clienteController.altaPedido(altaPedidoDto2, mockBindingResult);
        clienteController.altaPedido(altaPedidoDto3, mockBindingResult);

    }

    @Test
    @Order(20)
    void getAllRestaurantes() {
        List<RestauranteDto> restauranteDtos = clienteController.getAllRestaurantes();

        Assertions.assertEquals("restauranteVPITesting@gmail.com", restauranteDtos.get(0).getEmail());
        Assertions.assertEquals("restauranteVPITesting2@gmail.com", restauranteDtos.get(1).getEmail());
    }

    @Test
    @Order(21)
    void getMenusRestaurante() {

        List<MenuDto> menusObtenidos = clienteController.getMenusRestaurante("restauranteVPITesting2SF");

        Assertions.assertEquals("MenuVPITestingSF", menusObtenidos.get(0).getNombre());
        Assertions.assertEquals("MenuVPITesting1", menusObtenidos.get(1).getNombre());
        Assertions.assertEquals(null, menusObtenidos.get(2).getNombre());
    }

    @Test
    @Order(25)
    void calificarRestaurante() {

        CalificacionDto calificacionDto = new CalificacionDto();
        calificacionDto.setCliente(true);
        calificacionDto.setIdCliente("clienteVPITesting@gmail.com");
        calificacionDto.setIdRestaurante("restauranteVPITesting2@gmail.com");
        calificacionDto.setPuntuacion(3);

        ResponseEntity<?> response = clienteController.calificarRestaurante(calificacionDto, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

        CalificacionDto calificacionDto2 = new CalificacionDto();
        calificacionDto2.setCliente(true);
        calificacionDto2.setIdCliente("clienteVPITesting@gmail.com");
        calificacionDto2.setIdRestaurante("restauranteVPITesting2@gmail.com");
        calificacionDto2.setPuntuacion(4);

        ResponseEntity<?> response2 = clienteController.calificarRestaurante(calificacionDto, mockBindingResult);
        String salida2 = "<200 OK OK,[]>";
        Assertions.assertEquals(salida2, response2.toString());
    }

    @Test
    @Order(25)
    void agregarFavorito() {

        FavoritoDto favoritoDto = new FavoritoDto();
        favoritoDto.setIdCliente("clienteVPITesting@gmail.com");
        favoritoDto.setIdRestaurante("restauranteVPITesting2@gmail.com");

        ResponseEntity<?> response = clienteController.agregarFavorito(favoritoDto, mockBindingResult);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

        Boolean isFav = clienteController.isFavorito(favoritoDto);
        Assertions.assertEquals(true, isFav);

    }

    @Test
    @Order(28)
    void getPedidos() {
        List<PedidoDto> pedidos = iClienteService.getPedidos("clienteVPITesting@gmail.com");
        Assertions.assertEquals(3, pedidos.size());
    }

    @Test
    @Order(29)
    void modificarDireccion() {
        ModificarDireccionDto modificarDireccionDto = new ModificarDireccionDto();
        modificarDireccionDto.setApto("AptoModificado");
        //Cambiar +2
        modificarDireccionDto.setId(89);
        modificarDireccionDto.setPrincipal(true);
        modificarDireccionDto.setNumero("1");
        modificarDireccionDto.setCalle("Av italia");
        modificarDireccionDto.setBarrio("Barrio");
        modificarDireccionDto.setEsquina("Esquina");
        modificarDireccionDto.setIdCliente("clienteVPITesting@gmail.com");

        ResponseEntity<?> response = clienteController.modificarDireccion(modificarDireccionDto);
        List<DireccionDto> direcciones = clienteController.getDirecciones("clienteVPITesting@gmail.com");

        Assertions.assertEquals("AptoModificado", direcciones.get(1).getApto());
    }

    @Test
    @Order(31)
    void eliminarMiCuenta() {
        clienteController.eliminarMiCuenta("clienteVPITesting2@gmail.com");
        BusquedaUsuarioDto busquedaUsuarioDto = new BusquedaUsuarioDto();
        busquedaUsuarioDto.setBloqueado(false);
        busquedaUsuarioDto.setTextoBusqueda("clienteVPITesting2@gmail.com");
        busquedaUsuarioDto.setTipoCliente(true);
        List<UsuarioDto> usuarios = administradorController.usuariosBusqueda(busquedaUsuarioDto);
        List<UsuarioDto> usuarios2 = new ArrayList<UsuarioDto>();
        Assertions.assertEquals(usuarios2, usuarios);
    }

    @Test
    @Order(32)
    void eliminarDireccion() {
        //Cambiar +2
        clienteController.eliminarDireccion(89);
        List<DireccionDto> direcciones = clienteController.getDirecciones("clienteVPITesting@gmail.com");
        Assertions.assertEquals(1, direcciones.size());
    }

    /*

    */





    /**************     Controlador Administrador     **************/

    @Test
    @Order(18)
    void aprobarRestaurante() {

        administradorController.aprobarRestaurante("restauranteVPITesting@gmail.com");
        administradorController.aprobarRestaurante("restauranteVPITesting2@gmail.com");

        RestauranteDto restauranteDto = administradorController.getDatosRestaurante("restauranteVPITesting");
        Boolean confirmado = restauranteDto.getConfirmado();

        Assertions.assertEquals(true, confirmado);

    }

    @Test
    @Order(19)
    void rechazarRestaurante() {

        RechazoBloqueoDTO rechazoBloqueoDTO = new RechazoBloqueoDTO("restauranteVPITesting4@gmail.com", "porque me pintó");

        ResponseEntity<?> response = administradorController.rechazarRestaurante(rechazoBloqueoDTO, mockBindingResult);
        System.out.println(response.toString());
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());
    }

    @Test
    @Order(22)
    void usuariosBusqueda() {
        BusquedaUsuarioDto busquedaUsuarioDto = new BusquedaUsuarioDto();
        busquedaUsuarioDto.setTipoCliente(false);
        busquedaUsuarioDto.setTextoBusqueda("");
        busquedaUsuarioDto.setBloqueado(false);

        List<UsuarioDto> resultado = administradorController.usuariosBusqueda(busquedaUsuarioDto);
        Assertions.assertEquals("restauranteVPITesting@gmail.com", resultado.get(0).getEmail());
        Assertions.assertEquals("restauranteVPITesting2@gmail.com", resultado.get(1).getEmail());
        Assertions.assertEquals("restauranteVPITesting5@gmail.com", resultado.get(2).getEmail());
    }

    @Test
    @Order(23)
    void bloquearRestaurante() {
        RechazoBloqueoDTO rechazoBloqueoDTO = new RechazoBloqueoDTO("restauranteVPITesting5@gmail.com", "porque me pintó");
        administradorController.bloquearRestaurante(rechazoBloqueoDTO, mockBindingResult);
        BusquedaUsuarioDto busquedaUsuarioDto = new BusquedaUsuarioDto();
        busquedaUsuarioDto.setTipoCliente(false);
        busquedaUsuarioDto.setTextoBusqueda("");
        busquedaUsuarioDto.setBloqueado(true);
        List<UsuarioDto> resultado = administradorController.usuariosBusqueda(busquedaUsuarioDto);
        Assertions.assertEquals("restauranteVPITesting5@gmail.com", resultado.get(0).getEmail());
    }

    @Test
    @Order(26)
    void registroAdmin() {
        NuevoAdminDto nuevoAdminDto = new NuevoAdminDto();
        nuevoAdminDto.setPassword("admin123");
        nuevoAdminDto.setEmail("administrador@gmail.com");
        ResponseEntity<?> response = administradorController.registroAdmin(nuevoAdminDto);
        String salida = "<200 OK OK,[]>";
        Assertions.assertEquals(salida, response.toString());

        ResponseEntity<?> response2 = administradorController.registroAdmin(nuevoAdminDto);
        String salida2 = "<400 BAD_REQUEST Bad Request,El email ya se encuentra registrado,[]>";
        Assertions.assertEquals(salida2, response2.toString());
    }



}