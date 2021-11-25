package com.VPI.VPI.Controllers;

import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Entities.UsuarioEntity;
import com.VPI.VPI.Security.Jwt.JwtUtil;
import com.VPI.VPI.Security.MyUserDetails;
import com.VPI.VPI.Security.UserDetailsServiceImpl;
import com.VPI.VPI.Services.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;


@CrossOrigin()//accede desde cualquier url
@RequestMapping(value = "/auth")
@RestController
@Api( tags = "Auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    RestauranteService restauranteService;
    @Autowired
    private EmailService emailService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Value("${spring.mail.username}")
    private String mailFrom;

    @Autowired
    private IAWSS3Service awss3Service;



    @PreAuthorize("authenticated")
    @GetMapping("/GetLogeado")
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping("/RegistroRestaurante")
    public ResponseEntity<?> registroRestaurante (@RequestParam("file")MultipartFile file,@RequestParam("restauranteDto") String restauranteDto) throws IOException {

        NuevoRestauranteDto res = new NuevoRestauranteDto();
        try {
             res = new ObjectMapper().readValue(restauranteDto, NuevoRestauranteDto.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(usuarioService.existsByEmail(res.getEmail())){
            return new ResponseEntity(("El email ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }
        if(restauranteService.existsByNombreRestaurante(res.getNombreRestaurante())){
            return new ResponseEntity(("El nombre ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }
        if(restauranteService.existsByNroHabilitacion(res.getNroHabilitacion())){
            return new ResponseEntity(("El numero de habilitación ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }
        String urlFoto = awss3Service.uploadFile(file);
        res.setUrlFoto(urlFoto);
        restauranteService.createRestaurante(res);
        File tempFile = convertMultiPartToFile(file);
        tempFile.delete();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/RegistroRestauranteSinFoto")
    public ResponseEntity<?> registroRestauranteSinFoto (@RequestBody NuevoRestauranteDto restauranteDto){
        
        if(usuarioService.existsByEmail(restauranteDto.getEmail())){
            return new ResponseEntity(("El email ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }
        if(restauranteService.existsByNombreRestaurante(restauranteDto.getNombreRestaurante())){
            return new ResponseEntity(("El nombre ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }
        if(restauranteService.existsByNroHabilitacion(restauranteDto.getNroHabilitacion())){
            return new ResponseEntity(("El numero de habilitación ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }

        restauranteService.createRestaurante(restauranteDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/RegistroCliente")
    public ResponseEntity<?> registroCliente (@RequestParam("file")MultipartFile file,@RequestParam("clienteDto") String clienteDto) throws IOException {

        NuevoClienteDto cli = new NuevoClienteDto();
        try {
            cli = new ObjectMapper().readValue(clienteDto, NuevoClienteDto.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(usuarioService.existsByEmail(cli.getEmail())){
            return new ResponseEntity(("El email ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }
        String urlFoto = awss3Service.uploadFile(file);
        cli.setUrlFoto(urlFoto);

        clienteService.createCliente(cli);
        File tempFile = convertMultiPartToFile(file);
        tempFile.delete();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/RegistroClienteSinFoto")
    public ResponseEntity<?> registroClienteSinFoto (@RequestBody NuevoClienteDto clienteDto){

        if(usuarioService.existsByEmail(clienteDto.getEmail())){
            return new ResponseEntity(("El email ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }
        clienteService.createCliente(clienteDto);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/Login")
    public ResponseEntity<JwtView> login(@Valid @RequestBody LoginView loginView, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return new ResponseEntity(("Campos erroneos"), HttpStatus.BAD_REQUEST);
        }
        Optional<UsuarioEntity> usuarioOpt = usuarioService.findByEmail(loginView.getEmail());

        if(!usuarioOpt.isPresent()) {
            return new ResponseEntity("No existe ningun usuario con el correo ingresado", HttpStatus.BAD_REQUEST);
        }
        UsuarioEntity usuario = usuarioOpt.get();
        if(usuario.getRol()== UsuarioEntity.Rol.Cliente){
            boolean bloqueado = clienteService.isBloqueado(loginView.getEmail());
            if(bloqueado) {
                return new ResponseEntity("Cliente bloqueado", HttpStatus.BAD_REQUEST);
            }
        }
        if(usuario.getRol()== UsuarioEntity.Rol.Restaurante){
            boolean bloqueado = restauranteService.isBloqueado(loginView.getEmail());
            boolean confirmado = restauranteService.isConfirmado(loginView.getEmail());
            if(bloqueado) {
                return new ResponseEntity("Restaurante bloqueado", HttpStatus.BAD_REQUEST);
            }
            if(!confirmado) {
                return new ResponseEntity("El restaurante no se encuentra confirmado", HttpStatus.BAD_REQUEST);
            }
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginView.getEmail(), loginView.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);
        MyUserDetails my = (MyUserDetails) authentication.getPrincipal();
        JwtView jwtView = new JwtView(jwt, my.getUsername(), my.getAuthorities());

        return new ResponseEntity(jwtView, HttpStatus.OK);

    }

    @GetMapping("/email")
    public ResponseEntity<?> sendEmail(){
        emailService.sendMail();
        return new ResponseEntity("Correo enviado con exito", HttpStatus.OK);
    }

    @PostMapping("/emailChangePassword")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailDto emailDto){
        Optional<UsuarioEntity> usuarioOpt = usuarioService.findByEmail(emailDto.getMailTo());
        if(!usuarioOpt.isPresent())
            return new ResponseEntity("No existe ningún usuario con el correo ingresado", HttpStatus.BAD_REQUEST);

        UsuarioEntity usuario = usuarioOpt.get();
        emailDto.setMailFrom(mailFrom);
        emailDto.setMailTo(usuario.getEmail());
        emailDto.setSubject("Cambio de contraseña");
        // UUID uuid = UUID.randomUUID();
        String pass = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        String passwordTemporal = pass.substring(0,8);
        emailDto.setPasswordTemporal(passwordTemporal);
        usuarioService.setpasswordTemporal(usuario.getEmail(),passwordTemporal,usuario.getRol().toString());
        emailService.sendEmailTemplate(emailDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePassView changePassView, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity("Campos mal ingresados", HttpStatus.BAD_REQUEST);
        }
        if(!changePassView.getPassword().equals(changePassView.getConfirmPass())) {
            return new ResponseEntity("Las contraseñas no coinciden", HttpStatus.BAD_REQUEST);
        }
        Optional<UsuarioEntity>  usuarioOpt = usuarioService.findByPassWordTemporal(changePassView.getPasswordTemporal());
        if(!usuarioOpt.isPresent()) {
            return new ResponseEntity("Código incorrecto", HttpStatus.NOT_FOUND);
        }
        usuarioService.changePassword(changePassView.getPasswordTemporal(),changePassView.getPassword());
        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping("/altaMenuSinFoto")
    public ResponseEntity<?> altaMenuSinFoto (@RequestBody NuevoMenuDto menuDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return  new ResponseEntity(("Datos invalidos"), HttpStatus.BAD_REQUEST);

        if(restauranteService.existsMenu(menuDto.getNombre(),menuDto.getRestaurante())){
            return new ResponseEntity(("El menu ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
        }
        String idRestaurante = menuDto.getRestaurante();
        restauranteService.createMenu(menuDto, idRestaurante);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/altaMenu")
    public ResponseEntity<?> altaMenu (@RequestParam("file")MultipartFile file,@RequestParam("menuDto") String menuDto) throws IOException {

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



    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }



}
