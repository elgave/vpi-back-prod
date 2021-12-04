package com.VPI.VPI.Services;


import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Entities.*;
import com.VPI.VPI.Repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.IOException;

import java.math.BigDecimal;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.*;

@Service

public class RestauranteService implements IRestauranteService {
    @Autowired
    private IRestauranteRepository restauranteRepository;
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IMenuRepository menuRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IAgregadoRepository agregadoRepository;
    @Autowired
    private  EmailService emialService;
    @Autowired
    private  NotificationService notificationervice;
    @Autowired
    private IPedidoRepository pedidoRepository;
    @Autowired
    private IItemRepository itemRepository;
    @Autowired
    private IReclamoRepository reclamoRepository;

    @Autowired
    private IFavoritoRepository favoritoRepository;

    @Autowired
    private  ICalificacionRepository calificacionRepository;

    private MenuEntity mapToEntity(NuevoMenuDto menuDto)  {
        MenuEntity menuEntity = modelMapper.map(menuDto, MenuEntity.class);
        return menuEntity;
    }

    private RestauranteEntity mapToEntity(NuevoRestauranteDto restauranteDto)  {
        RestauranteEntity restauranteEntity = modelMapper.map(restauranteDto, RestauranteEntity.class);
        return restauranteEntity;
    }
    private AgregadoEntity mapToEntity(AltaAgregadoDto agregadoDto)  {
        AgregadoEntity agregadoEntity = modelMapper.map(agregadoDto, AgregadoEntity.class);
        return agregadoEntity;
    }

    private RestauranteDto mapToDto(RestauranteEntity restaurante)  {
        RestauranteDto res = modelMapper.map(restaurante, RestauranteDto.class);
        return res;
    }

    private List<PedidoDto> mapPedidoDetalleToDtos(List<PedidoEntity> pedidoEntities)  {
        List<PedidoDto> pedidoDtos = Arrays.asList(modelMapper.map(pedidoEntities, PedidoDto[].class));
        return pedidoDtos;
    }

    private List<ReclamoDto> mapReclamoToDtos(List<ReclamoEntity> reclamoEntities)  {
        List<ReclamoDto> reclamoDtos = Arrays.asList(modelMapper.map(reclamoEntities, ReclamoDto[].class));
        return reclamoDtos;
    }

    private List<MenuDto> mapMenuToDtos(List<MenuEntity> menuEntity)  {
        List<MenuDto> menusDto = Arrays.asList(modelMapper.map(menuEntity, MenuDto[].class));
        return menusDto;
    }

    private List<RestauranteDto> mapRestauranteToDtos(List<RestauranteEntity> restauranteEntities)  {
        List<RestauranteDto> restauranteDtos = Arrays.asList(modelMapper.map(restauranteEntities, RestauranteDto[].class));
        return restauranteDtos;
    }

    private List<AltaPedidoDto> mapPedidoToDtosManual(List<PedidoEntity> pedidoEntities)  {
        List<AltaPedidoDto> altaPedidoDtos = new ArrayList<>();
        for (PedidoEntity p: pedidoEntities) {
            AltaPedidoDto altaPedidoDto = new AltaPedidoDto();
            altaPedidoDto.setIdPedido(p.getIdPedido());
            altaPedidoDto.setCliente(p.getCliente().getEmail());
            altaPedidoDto.setRestaurante(p.getRestaurante().getNombreRestaurante());
            altaPedidoDto.setComentario(p.getComentario());
            altaPedidoDto.setPagoAcreditado(p.getPagoAcreditado());
            altaPedidoDto.setPagoOnline(p.getPagoOnline());
            altaPedidoDto.setPrecioTotal(p.getPrecioTotal());

           List<ItemEntity> items = itemRepository.findByPedido(p);
            List<AltaItemDto> itemDtos = new ArrayList<>();
            for (int i=0;i<items.size();i++) {
               AltaItemDto itemDto = new AltaItemDto();

               itemDto.setNombre(items.get(i).getNombre());
               itemDto.setCantidad(items.get(i).getCantidad());
               itemDto.setPrecio(items.get(i).getPrecio());
               itemDtos.add(itemDto);

            }
            altaPedidoDto.setItems(itemDtos);

            altaPedidoDtos.add(altaPedidoDto);
        }
        return altaPedidoDtos;
    }



    private List<PedidoDto> mapPedidoConReclamoToDtosManual(List<PedidoEntity> pedidoEntities)  {
        List<PedidoDto> pedidoDtos = new ArrayList<>();

        for (PedidoEntity p: pedidoEntities) {
            PedidoDto pedidoDto = new PedidoDto();
            pedidoDto.setIdPedido(p.getIdPedido());
            pedidoDto.setCliente(p.getCliente().getEmail());
            pedidoDto.setRestaurante(p.getRestaurante().getNombreRestaurante());
            pedidoDto.setComentario(p.getComentario());
            pedidoDto.setPagoAcreditado(p.getPagoAcreditado());
            pedidoDto.setPagoOnline(p.getPagoOnline());
            pedidoDto.setPrecioTotal(p.getPrecioTotal());
            pedidoDto.setEstado(p.getEstado());
            pedidoDto.setFecha(p.getFecha());

            List<ItemEntity> items = itemRepository.findByPedido(p);
            List<AltaItemDto> itemDtos = new ArrayList<>();
            for (int i=0;i<items.size();i++) {
                AltaItemDto itemDto = new AltaItemDto();

                itemDto.setNombre(items.get(i).getNombre());
                itemDto.setCantidad(items.get(i).getCantidad());
                itemDto.setPrecio(items.get(i).getPrecio());
                itemDtos.add(itemDto);
            }
            pedidoDto.setItems(itemDtos);

            List<ReclamoEntity> reclamos = reclamoRepository.findByPedido(p);
            List<ReclamoDto> reclamoDtos = new ArrayList<>();
            for (int i=0;i<reclamos.size();i++) {
                ReclamoDto reclamoDto = new ReclamoDto();

                reclamoDto.setIdReclamo(reclamos.get(i).getIdReclamo());
                reclamoDto.setEstado(String.valueOf(reclamos.get(i).getEstado()));
                reclamoDtos.add(reclamoDto);
            }
            pedidoDto.setReclamos(reclamoDtos);

            pedidoDtos.add(pedidoDto);
        }
        return pedidoDtos;
    }

    private List<AltaAgregadoDto> mapAgregadoToDtos(List<AgregadoEntity> agregadoEntities)  {
        List<AltaAgregadoDto> agregadoDtos = Arrays.asList(modelMapper.map(agregadoEntities, AltaAgregadoDto[].class));
        return agregadoDtos;
    }


    public List<RestauranteEntity> getAllClientes() {
        return restauranteRepository.findAll();
    }


    public RestauranteEntity createRestaurante(NuevoRestauranteDto restauranteDto ) {
        RestauranteEntity restaurante= mapToEntity(restauranteDto);

        String pass = restaurante.getPassWord();
        String passEncript = passwordEncoder.encode(pass);

        RestauranteEntity restauranteEntity = new RestauranteEntity();
        restauranteEntity.setEmail(restaurante.getEmail());
        restauranteEntity.setPassWord(passEncript);
        restauranteEntity.setRol(ClienteEntity.Rol.Restaurante);
        Date currentSqlDate = new Date(System.currentTimeMillis());
        restauranteEntity.setFecha(currentSqlDate);

        restauranteEntity.setNombreRestaurante(restaurante.getNombreRestaurante());
        restauranteEntity.setNroHabilitacion(restaurante.getNroHabilitacion());
        restauranteEntity.setRazonSocial(restaurante.getRazonSocial());
        restauranteEntity.setRut(restaurante.getRut());
        restauranteEntity.setAbierto(false);
        restauranteEntity.setDireccion(restaurante.getDireccion());
        restauranteEntity.setDescripcionMenues(restaurante.getDescripcionMenues());
        restauranteEntity.setConfirmado(false);
        restauranteEntity.setBloqueado(false);
        restauranteEntity.setCelular(restaurante.getCelular());
        restauranteEntity.setFoto(restauranteDto.getUrlFoto());
        restauranteEntity.setPrecioEnvio(restaurante.getPrecioEnvio());
        restauranteEntity.setHorario(restaurante.getHorario());
        restauranteEntity.setCalificacionVPI(0.0);

        return restauranteRepository.save(restauranteEntity);
    }

    public MenuEntity createMenu(NuevoMenuDto menuDto,String idRestaurante) {
        MenuEntity menu= mapToEntity(menuDto);

        Optional<RestauranteEntity>  restauranteEntity = restauranteRepository.findByEmail(idRestaurante);
        RestauranteEntity restaurante = restauranteEntity.get();
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setRestaurante(restaurante);
        menuEntity.setNombre(menu.getNombre());
        menuEntity.setCategoria(menu.getCategoria());
        if(menu.getPromocion() == null){
            menu.setPromocion(false);
        }
        menuEntity.setPromocion(menu.getPromocion());
        menuEntity.setDescripcion(menu.getDescripcion());
        menuEntity.setCosto(menu.getCosto());
        menuEntity.setDescuento(menu.getDescuento());
        menuEntity.setImagen(menu.getImagen());


        return menuRepository.save(menuEntity);
    }


    @Override
    public boolean existsMenu(String nombre, String idRestaurante) {

        Integer count = menuRepository.cantMenu(nombre,idRestaurante);
        if (count==0){
            return false;
        }else
            return  true;
    }

    @Override
    public Boolean existsByNombreRestaurante(String nombreRestaurante) {
        return restauranteRepository.existsByNombreRestaurante(nombreRestaurante);
    }

    @Override
    public Boolean existsByNroHabilitacion(String nroHabilitacion) {
        return restauranteRepository.existsByNroHabilitacion(nroHabilitacion);
    }

    @Override
    public boolean isBloqueado(String email) {
        Optional<RestauranteEntity> restOpt = restauranteRepository.findByEmail(email);
        RestauranteEntity restaurante = restOpt.get();
        return restaurante.getBloqueado();
    }

    @Override
    public boolean isConfirmado(String email) {
        Optional<RestauranteEntity> restOpt = restauranteRepository.findByEmail(email);
        RestauranteEntity restaurante = restOpt.get();
        return restaurante.getConfirmado();
    }

    @Override
    public AgregadoEntity altaAgregado(AltaAgregadoDto agregadoDto, String idRestaurante) {
        AgregadoEntity agregado= mapToEntity(agregadoDto);

        Optional<RestauranteEntity>  restauranteEntity = restauranteRepository.findByEmail(idRestaurante);
        RestauranteEntity restaurante = restauranteEntity.get();
        AgregadoEntity agregadoEntity = new AgregadoEntity();
        agregadoEntity.setRestaurante(restaurante);
        agregadoEntity.setCosto(agregadoDto.getCosto());
        agregadoEntity.setNombre(agregadoDto.getNombre());

        return agregadoRepository.save(agregadoEntity);
    }

    public List<RestauranteDto> findByConfirmado(Boolean confirmado){

        List<RestauranteEntity> restaurantes = restauranteRepository.findByConfirmado(false);

        List<RestauranteDto> restaurantesDto = new ArrayList<>();
        for (RestauranteEntity r: restaurantes) {
            RestauranteDto resta = mapToDto(r);

            restaurantesDto.add(resta);
        }

        return restaurantesDto;
    }

    public void deleteByEmail (String email, String motivo){


        restauranteRepository.deleteById(email);
        emialService.sendEmailRechazo(email,motivo);
    }


    @Override
    public List<PedidoDto> pedidoByRestaurante(String idRestaurante) {
        Optional<RestauranteEntity> restauranteOpt = restauranteRepository.findByEmail(idRestaurante);
        RestauranteEntity restaurante = restauranteOpt.get();
        List<PedidoEntity> pedidoEntities = pedidoRepository.findByRestauranteOrderByIdPedidoDesc((restaurante));
        List<PedidoDto> pedidos = mapPedidoDetalleToDtos(pedidoRepository.findByRestauranteOrderByIdPedidoDesc(restaurante));
        return pedidos;
    }

    @Override
    public  Boolean abrirCerrar(AbiertoDto abiertoDto){
        Optional<RestauranteEntity> restaurante = restauranteRepository.findByEmail(abiertoDto.getIdRestaurante());
        restaurante.get().setAbierto(abiertoDto.getAbierto());
        restauranteRepository.save(restaurante.get());
        return abiertoDto.getAbierto();

    }

    @Override
    public void pagoEnEfectivo(Integer idPedido) {
        Optional<PedidoEntity> pedidoOpt = pedidoRepository.findByIdPedido(idPedido);
        PedidoEntity pedidoEntity = pedidoOpt.get();
        pedidoEntity.setPagoAcreditado(true);
        pedidoRepository.save(pedidoEntity);
    }

    @Override
    public void confirmarPedido(Integer idPedido, Integer tiempoE) throws IOException {
        Optional<PedidoEntity> pedidoOpt = pedidoRepository.findByIdPedido(idPedido);
        PedidoEntity pedidoEntity = pedidoOpt.get();
        if(pedidoEntity.getEstado().equals(PedidoEntity.EstadoPedido.Pendiente)){
            pedidoEntity.setTiempoE(tiempoE);
            pedidoEntity.setEstado(PedidoEntity.EstadoPedido.Confirmado);
        }
        pedidoRepository.save(pedidoEntity);
        emialService.sendEmailConfirmarPedido(pedidoEntity.getClienteEmail(), tiempoE);

        String emailClienteNoti = pedidoEntity.getClienteEmail();
        Optional<ClienteEntity> clienteEntity = clienteRepository.findByEmail(emailClienteNoti);

        String token = clienteEntity.get().getToken();
        if(token != null) {
            notificationervice.sendNotifiction(token, tiempoE);
        }
    }
    public void aceptarReclamo(Integer idReclamo, String tipo) throws IOException {
        Optional<ReclamoEntity> reclamoOpt = reclamoRepository.findByIdReclamo(idReclamo);
        ReclamoEntity reclamoEntity = reclamoOpt.get();
        if(tipo.equals("Reembolso")) {
            reclamoEntity.setEstado(ReclamoEntity.EstadoReclamo.Reembolsado);
        }
        if(tipo.equals("Compensacion")) {
            reclamoEntity.setEstado(ReclamoEntity.EstadoReclamo.Compensado);
        }
        reclamoRepository.save(reclamoEntity);

        emialService.sendEmailAceptaReclamo(reclamoEntity.getPedido().getCliente().getEmail(), tipo);

        Optional<ClienteEntity> clienteEntity = clienteRepository.findByEmail(reclamoEntity.getPedido().getCliente().getEmail());

        String token = clienteEntity.get().getToken();
        if(token != null) {
            notificationervice.sendNotifictionAceptarReclamo(token, tipo);
        }

//restauranteEntity.setRol(ClienteEntity.Rol.Restaurante);

    }

    public List<UsuarioDto> buscarRestaurantes(Boolean bloqueado, String busqueda){

        List<UsuarioDto> restauranteBusquedaDtoList = new ArrayList<>();

        List<RestauranteEntity> restaurantes = new ArrayList<>();
        if(bloqueado){

            restaurantes = restauranteRepository.findByBloqueadoTrueAndEmailIgnoreCaseContainingOrBloqueadoTrueAndNombreRestauranteIgnoreCaseContainingOrBloqueadoTrueAndCelularIgnoreCaseContaining(busqueda,busqueda,busqueda);

        }else{
            restaurantes = restauranteRepository.findByBloqueadoFalseAndEmailIgnoreCaseContainingOrBloqueadoFalseAndNombreRestauranteIgnoreCaseContainingOrBloqueadoFalseAndCelularIgnoreCaseContaining(busqueda,busqueda,busqueda);
        }

        for(RestauranteEntity r: restaurantes){

            UsuarioDto res = new UsuarioDto();

            res.setEmail(r.getEmail());
            res.setBloqueado(r.getBloqueado());
            res.setCelular(r.getCelular());
            res.setNombre(r.getNombreRestaurante());
            res.setDireccion(r.getDireccion());
            res.setFechaCreacion(r.getFecha());
            res.setCalificacionGlobal(obtenerCalificacionGlobal(r.getEmail()));

            restauranteBusquedaDtoList.add(res);
        }

        return restauranteBusquedaDtoList;
    }

    public Double obtenerCalificacionGlobal(String idRestaurante){

        Double calificacionGlobal = 0.0;
        try {
            List<CalificacionEntity> calificaciones = restauranteRepository.obtenerMisCalificaciones(idRestaurante);

            Double calificacionSuma = 0.0;

            if (calificaciones.stream().count() > 0) {
                for (CalificacionEntity c : calificaciones) {
                    calificacionSuma += c.getPuntuacion();
                }

                DecimalFormat df = new DecimalFormat("#.#");
                calificacionGlobal = Double.valueOf(calificacionSuma / calificaciones.stream().count());

                String cali = df.format(calificacionGlobal);
                calificacionGlobal = Double.parseDouble(cali);
            }
        }
        catch (Exception exception){

        }
        return  calificacionGlobal;
    }

    public void rechazarReclamo(Integer idReclamo, String motivo) throws IOException {

        Optional<ReclamoEntity> reclamo = reclamoRepository.findByIdReclamo(idReclamo);

        Optional<PedidoEntity> pedido = pedidoRepository.findByIdPedido(reclamo.get().getPedido().getIdPedido());

        String mailCliente = pedido.get().getClienteEmail();

        reclamo.get().setEstado(ReclamoEntity.EstadoReclamo.Rechazado);

        reclamoRepository.save(reclamo.get());

        emialService.sendEmailRechazarReclamo(mailCliente,motivo);

        Optional<ClienteEntity> clienteEntity = clienteRepository.findByEmail(mailCliente);

        String token = clienteEntity.get().getToken();
        if(token != null) {
            notificationervice.sendNotifictionRechazoReclamo(token, motivo);
        }

    }

    @Override
    public List<PedidoDto> pedidosPendienteRestaurante(String idRestaurante) {
        Optional<RestauranteEntity> restauranteOpt = restauranteRepository.findByEmail(idRestaurante);
        RestauranteEntity restaurante = restauranteOpt.get();
        List<PedidoEntity> pedidoEntities = pedidoRepository.findByRestauranteAndEstadoOrderByIdPedidoDesc(restaurante, PedidoEntity.EstadoPedido.Pendiente);
        List<PedidoDto> pedidos = mapPedidoDetalleToDtos(pedidoEntities);
        return pedidos;
    }
    public  List<ReclamoDto> listarReclamos(BusquedaReclamoDto busquedaReclamoDto){

        List<ReclamoDto> reclamos = new ArrayList<>();
        List<ReclamoEntity> reclamoEntities = new ArrayList<>();
        if(busquedaReclamoDto.getIdPedido() != 0) {

            Optional<PedidoEntity> pedido = pedidoRepository.findByIdPedido(busquedaReclamoDto.getIdPedido());

            if (busquedaReclamoDto.getEstadoReclamo().equals("Todo")){

                reclamoEntities = reclamoRepository.findByPedidoAndFechaLessThanEqualAndFechaGreaterThanEqual(pedido.get(),busquedaReclamoDto.getFechaHasta(),busquedaReclamoDto.getFechaDesde());

            }
            else {
                reclamoEntities = reclamoRepository.findByPedidoAndEstadoEqualsAndFechaLessThanEqualAndFechaGreaterThanEqual(pedido.get(), busquedaReclamoDto.getEstadoReclamo(),busquedaReclamoDto.getFechaHasta(),busquedaReclamoDto.getFechaDesde());
            }
        }
        else{

            if (busquedaReclamoDto.getEstadoReclamo().equals("Todo")){

                reclamoEntities = reclamoRepository.findByFechaLessThanEqualAndFechaGreaterThanEqual(busquedaReclamoDto.getFechaHasta(),busquedaReclamoDto.getFechaDesde());

            }
            else {
                reclamoEntities = reclamoRepository.findByEstadoEqualsAndFechaLessThanEqualAndFechaGreaterThanEqual(busquedaReclamoDto.getEstadoReclamo(),busquedaReclamoDto.getFechaHasta(),busquedaReclamoDto.getFechaDesde());
            }

        }

        for(ReclamoEntity r: reclamoEntities){

            ReclamoDto reclamoDto = new ReclamoDto();

            reclamoDto.setIdReclamo(r.getIdReclamo());
            reclamoDto.setPedido(r.getPedido().getIdPedido());
            reclamoDto.setEstado(r.getEstado().toString());
            reclamoDto.setTipo(r.getTipo());
            reclamoDto.setFecha(r.getFecha());

            reclamos.add(reclamoDto);
        }
        return  reclamos;
    }

    @Override
    public Boolean isAbierto(String idRestaurante) {
        return restauranteRepository.isAbierto(idRestaurante);
    }

    @Override
    public List<ReclamoDto> reclamosConFiltro(ReclamosFiltroDto dto ) {
        List<ReclamoDto> reclamoDtos = new ArrayList<>();
        Optional<RestauranteEntity> restauranteOpt = restauranteRepository.findByEmail(dto.getIdRestaurante());
        RestauranteEntity restaurante = restauranteOpt.get();

        if(dto.getFiltro().equals("Todos")){
            reclamoDtos = mapReclamoToDtos(reclamoRepository.listReclamosSinFiltro(restaurante));

        }
        else {

            ReclamoEntity.EstadoReclamo estado = ReclamoEntity.EstadoReclamo.valueOf(dto.getFiltro());
            reclamoDtos = mapReclamoToDtos(reclamoRepository.listReclamos(restaurante, estado));
        }

        for(ReclamoDto r : reclamoDtos) {
            Optional<PedidoEntity> pedido = pedidoRepository.findByIdPedido(r.getPedido());
            r.setCliente(pedido.get().getCliente().getEmail());
        }

        return reclamoDtos;
    }

    @Override
    public List<PedidoDto> pedidosSinPendienteRestaurante(String idRestaurante) {
        Optional<RestauranteEntity> restauranteOpt = restauranteRepository.findByEmail(idRestaurante);
        RestauranteEntity restaurante = restauranteOpt.get();
        List<PedidoEntity> pedidoEntities = pedidoRepository.findByRestauranteAndEstadoNotOrderByIdPedidoDesc(restaurante, PedidoEntity.EstadoPedido.Pendiente);
        List<PedidoDto> pedidos = mapPedidoDetalleToDtos(pedidoEntities);
        return pedidos;
    }

    public void calificarCliente(CalificacionDto calificacionDto){

        CalificacionEntity calificacion = new CalificacionEntity();

        List<CalificacionEntity> cal = calificacionRepository.obtenerCalificacionRestaurante(calificacionDto.getIdCliente(),calificacionDto.getIdRestaurante());

        if(cal.stream().count() == 0) {

            calificacion.setCliente(calificacionDto.getCliente());
            calificacion.setCliente_id(calificacionDto.getIdCliente());
            calificacion.setRestaurante_id(calificacionDto.getIdRestaurante());
            calificacion.setPuntuacion(calificacionDto.getPuntuacion());

            calificacionRepository.save(calificacion);
        }else{

            calificacion = cal.get(0);

            calificacion.setPuntuacion(calificacionDto.getPuntuacion());

            calificacionRepository.save(calificacion);
        }

    }

    public void eliminarCalificacionCliente(CalificacionDto calificacionDto){

        List<CalificacionEntity> cal = calificacionRepository.obtenerCalificacionRestaurante(calificacionDto.getIdCliente(),calificacionDto.getIdRestaurante());

        calificacionRepository.delete(cal.get(0));

    }

    public void eliminarAgregado(Integer id){

        Optional<AgregadoEntity> agregado = agregadoRepository.findById(id);

        agregadoRepository.delete(agregado.get());

    }

    public  void modificarAgregado(ModificacionAgregadoDto modificacionAgregadoDto){

        Optional<AgregadoEntity> agregado = agregadoRepository.findById(modificacionAgregadoDto.getId());

        agregado.get().setCosto(modificacionAgregadoDto.getCosto());

        agregadoRepository.save(agregado.get());

    }


    @Override
    public List<MenuDto> listMenus(String email) {
        List<MenuEntity> menuEntities = menuRepository.menusRestauranteEmail(email);
        List<MenuDto> menusDto = mapMenuToDtos(menuEntities);
        return menusDto;
    }

    public List<AltaAgregadoDto> listarAgregados(String idRestaurante){

        Optional<RestauranteEntity> restaurante = restauranteRepository.findByEmail(idRestaurante);
        List<AgregadoEntity> agregadosEntity = agregadoRepository.findByRestaurante(restaurante.get());

        List<AltaAgregadoDto> agregadosDtos = new ArrayList<>();

        for(AgregadoEntity a : agregadosEntity){

            AltaAgregadoDto agregadoDto = new AltaAgregadoDto();
            agregadoDto.setRestaurante(a.getRestaurante().getEmail());
            agregadoDto.setCosto(a.getCosto());
            agregadoDto.setNombre(a.getNombre());
            agregadoDto.setId(a.getId());

            agregadosDtos.add(agregadoDto);

        }

        return agregadosDtos;

    }

    public  void eliminarMenu(Integer idMenu){

        Optional <MenuEntity> menu = menuRepository.findByIdMenu(idMenu);

        menuRepository.delete(menu.get());
    }

    public RestauranteDto obtenerMisDatos(String email){

        Optional<RestauranteEntity> restauranteEntity = restauranteRepository.findByEmail(email);

        RestauranteDto restauranteDto = new RestauranteDto();
        restauranteDto.setFoto(restauranteEntity.get().getFoto());
        restauranteDto.setCalificacionVPI(restauranteEntity.get().getCalificacionVPI());
        return  restauranteDto;
    }

    @Override
    public void asociarAgregados(AsociarAgregadoDto asociarAgregadoDto) {
        Optional<MenuEntity> menuOpt = menuRepository.findByIdMenu(asociarAgregadoDto.getIdMenu());
        MenuEntity menuEntity = menuOpt.get();

        List<Integer> listIds = asociarAgregadoDto.getIdAgregados();
        List<AgregadoEntity> agregadoEntities = new ArrayList<>();
        listIds.stream().forEach( id ->{
            Optional<AgregadoEntity> agregadoOPt = agregadoRepository.findById(id);
            AgregadoEntity agregadoEntity = agregadoOPt.get();
            agregadoEntities.add(agregadoEntity);
            menuEntity.addAgregado(agregadoEntity);
            menuRepository.save(menuEntity);
        });

	}
    public  Integer obtenerCalificacionCliente(CalificacionDto calificacionDto){

        Integer calificacion = 0;
        List<CalificacionEntity> cal = calificacionRepository.obtenerCalificacionRestaurante(calificacionDto.getIdCliente(),calificacionDto.getIdRestaurante());

        if(cal.stream().count() > 0) {
            calificacion = cal.get(0).getPuntuacion();
        }

        return  calificacion;
    }

    @Override
    public List<AltaAgregadoDto> agregadosQueNoEstanEnElMenu(RestMenuDto restMenuDto) {
        Optional<RestauranteEntity> restauranteOpt = restauranteRepository.findByEmail(restMenuDto.getIdRestaurante());
        RestauranteEntity restauranteEntity = restauranteOpt.get();
        Optional<MenuEntity> menuOpt = menuRepository.findByIdMenu(restMenuDto.getIdMenu());
        MenuEntity menuEntity = menuOpt.get();
        List<Integer> idAgregados = new ArrayList<>();
        for(AgregadoEntity a : menuEntity.getAgregados()){
            idAgregados.add(a.getId());
        }
        List<AgregadoEntity> agregadoEntities = new ArrayList<>();
        if(idAgregados.isEmpty()){
          agregadoEntities = agregadoRepository.findByRestaurante(restauranteEntity);
        }
        else {
          agregadoEntities = agregadoRepository.findByRestauranteAndIdNotIn(restauranteEntity, idAgregados);
        }
        return mapAgregadoToDtos(agregadoEntities);
    }


    
    public void eliminarCuenta(String idRestaurante) {

        Optional<RestauranteEntity> restauranteEntity = restauranteRepository.findByEmail(idRestaurante);
        List<MenuEntity> menuEntityList = menuRepository.menusRestauranteEmail(idRestaurante);

        if (menuEntityList.stream().count() > 0) {

            for (MenuEntity m : menuEntityList) {

                for (AgregadoEntity a : m.getAgregados()) {

                    m.removeAgregado(a);
                }

                menuRepository.save(m);

                menuRepository.delete(m);
            }
        }
        List<AgregadoEntity> agregadoEntityList = agregadoRepository.findByRestaurante(restauranteEntity.get());

        for(AgregadoEntity a : agregadoEntityList ){

            agregadoRepository.delete(a);
        }

        Optional<RestauranteEntity> deleteUser = restauranteRepository.findByEmail("deleteUserRest");

        List<PedidoEntity> pedidoEntityList = pedidoRepository.findByRestauranteOrderByIdPedidoDesc(restauranteEntity.get());

        if(pedidoEntityList.stream().count() >0 ){

            for(PedidoEntity p: pedidoEntityList){

                p.setRestaurante(deleteUser.get());

                pedidoRepository.save(p);
            }

        }

        calificacionRepository.borrarCalificacionesHaciaRestaurante(idRestaurante);

        favoritoRepository.borrarFavoritosHaciaRestaurante(idRestaurante);

        restauranteRepository.delete(restauranteEntity.get());

    }

    @Override
    public void modificarMenu(MenuDto menuDto) {
        Optional<MenuEntity> menuOpt = menuRepository.findByIdMenu(menuDto.getIdMenu());
        MenuEntity menu = menuOpt.get();

        menu.setDescuento(menuDto.getDescuento());
        menu.setCosto(menuDto.getCosto());
        menu.setDescripcion(menuDto.getDescripcion());
        menu.setPromocion(menuDto.getPromocion());

        MenuEntity.Categoria categoria   = MenuEntity.Categoria.valueOf(menuDto.getCategoria());
        menu.setCategoria(categoria);
        menu.setNombre(menuDto.getNombre());
        menu.setImagen(menuDto.getImagen());

        menuRepository.save(menu);

    }

    @Override
    public List<AltaAgregadoDto> agragadosByMenu(Integer idMenu) {
        Optional<MenuEntity> menuOpt = menuRepository.findByIdMenu(idMenu);
        MenuEntity menu = menuOpt.get();
        List<AltaAgregadoDto> agregadoDtos = mapAgregadoToDtos(agregadoRepository.findByMenus(menu));
        return agregadoDtos;
    }

    @Override
    public void quitarAgregados(QuitarAgregadoDto quitarAgregadoDto) {

        Optional<MenuEntity> menuOpt = menuRepository.findByIdMenu(quitarAgregadoDto.getIdMenu());
        MenuEntity menuEntity = menuOpt.get();
        Optional<AgregadoEntity> agregadoOpt = agregadoRepository.findById(quitarAgregadoDto.getIdAgregado());
        AgregadoEntity agregadoEntity = agregadoOpt.get();
        menuEntity.removeAgregado(agregadoEntity);

        menuRepository.save(menuEntity);

    }



    public List<RestaurantesMasVentasDto> restaurantesMasVentas(){

        List<RestaurantesMasVentasDto> restaurantesMasVentasDtoList = new ArrayList<>();
        List<RestauranteEntity> restaurantes = restauranteRepository.findByConfirmado(true);

        for(RestauranteEntity r :restaurantes){
            if(!r.getEmail().equals("deleteUserRest")) {
                RestaurantesMasVentasDto restaurantesMasVentasDto = new RestaurantesMasVentasDto();
                Integer contador = 0;
                List<PedidoEntity> pedidos = pedidoRepository.findByRestauranteOrderByIdPedidoDesc(r);

                for (PedidoEntity p : pedidos) {

                    if (p.getEstado() == PedidoEntity.EstadoPedido.Confirmado || p.getEstado() == PedidoEntity.EstadoPedido.Entregado) {

                        contador++;

                    }
                }

                restaurantesMasVentasDto.setNombreRestaurante(r.getNombreRestaurante());
                restaurantesMasVentasDto.setFoto(r.getFoto());

                restaurantesMasVentasDto.setCantidadVentas(contador);
                restaurantesMasVentasDto.setAbierto(r.getAbierto());

                restaurantesMasVentasDtoList.add(restaurantesMasVentasDto);
            }
        }

        Collections.sort(restaurantesMasVentasDtoList,Collections.reverseOrder());

        List<RestaurantesMasVentasDto> restaurantesFinal = new ArrayList<>();
        Integer contador2 = 0;

        for(RestaurantesMasVentasDto re: restaurantesMasVentasDtoList){

            if(contador2 > 9)
                break;
            else {
                restaurantesFinal.add(re);
                contador2++;
            }
        }
        return restaurantesFinal;

    }

    @Override
    public List<RestauranteDto> restConPromo() {
        return mapRestauranteToDtos(restauranteRepository.restaurantesConPromo());
    }

    @Override
    public List<MenuDto> menuCatagorias(String idRestaurante, String categoria) {

        MenuEntity.Categoria cat = MenuEntity.Categoria.valueOf(categoria);

        List<MenuEntity> menuEntities = menuRepository.menusRestauranteCategoria(idRestaurante, cat);

        return mapMenuToDtos(menuEntities);
    }

    @Override
    public List<PedidoDto> balance(BalanceDto dto) {
        Optional<RestauranteEntity> restauranteOpt = restauranteRepository.findByEmail(dto.getIdRestaurante());
        RestauranteEntity restaurante = restauranteOpt.get();

        Boolean pagoOnline;
        List<PedidoEntity> menuEntities = new ArrayList<>();
        if((dto.getFechaDesde()== null || dto.getFechaHasta() == null ) ) {
            if (dto.getTipoDeVenta().equals("Todo")) {
                menuEntities = pedidoRepository.findByRestauranteAndEstadoAndPagoAcreditadoTrue(restaurante, PedidoEntity.EstadoPedido.Confirmado);
            }

            if (dto.getTipoDeVenta().equals("Efectivo")){
                 pagoOnline = false;
                 menuEntities = pedidoRepository.findByRestauranteAndEstadoAndPagoAcreditadoTrueAndPagoOnline(restaurante, PedidoEntity.EstadoPedido.Confirmado, pagoOnline);
            }
            if (dto.getTipoDeVenta().equals("PayPal")){
                pagoOnline = true;
                menuEntities = pedidoRepository.findByRestauranteAndEstadoAndPagoAcreditadoTrueAndPagoOnline(restaurante, PedidoEntity.EstadoPedido.Confirmado, pagoOnline);
            }
        }
        else {
            Calendar d = Calendar.getInstance();
            d.setTime(dto.getFechaHasta());
            d.add(Calendar.DATE, 1);
            dto.setFechaHasta(d.getTime());

            if (dto.getTipoDeVenta().equals("Todo")) {
                menuEntities = pedidoRepository.findByRestauranteAndEstadoAndFechaLessThanEqualAndFechaGreaterThanEqualAndPagoAcreditadoTrue(restaurante, PedidoEntity.EstadoPedido.Confirmado, dto.getFechaHasta(), dto.getFechaDesde());
            }

            if (dto.getTipoDeVenta().equals("Efectivo")){
                pagoOnline = false;
                menuEntities = pedidoRepository.findByRestauranteAndEstadoAndFechaLessThanEqualAndFechaGreaterThanEqualAndPagoAcreditadoTrueAndPagoOnline(restaurante, PedidoEntity.EstadoPedido.Confirmado, dto.getFechaHasta(), dto.getFechaDesde(),pagoOnline);
            }

            if (dto.getTipoDeVenta().equals("PayPal")){
                pagoOnline = true;
                menuEntities = pedidoRepository.findByRestauranteAndEstadoAndFechaLessThanEqualAndFechaGreaterThanEqualAndPagoAcreditadoTrueAndPagoOnline(restaurante, PedidoEntity.EstadoPedido.Confirmado, dto.getFechaHasta(), dto.getFechaDesde(),pagoOnline);
            }

        }
        return mapPedidoConReclamoToDtosManual(menuEntities);
    }

    @Override
    public Boolean existeAgregado(String idRestaurante, String nombreAgragado) {
        Optional<RestauranteEntity> restOpt = restauranteRepository.findByEmail(idRestaurante);
        RestauranteEntity restaurante = restOpt.get();
        return agregadoRepository.existsByRestauranteAndNombre(restaurante, nombreAgragado);
    }

    @Override
    public void rechazarPedido(Integer idPedido, String motivo) throws IOException {
        Optional<PedidoEntity> pedidoOpt = pedidoRepository.findByIdPedido(idPedido);
        PedidoEntity pedidoEntity = pedidoOpt.get();
        if(pedidoEntity.getEstado().equals(PedidoEntity.EstadoPedido.Pendiente)){
            pedidoEntity.setComentarioEstado(motivo);
            pedidoEntity.setEstado(PedidoEntity.EstadoPedido.Rechazado);
        }
        pedidoRepository.save(pedidoEntity);
        emialService.sendEmailRechazarPedido(pedidoEntity.getClienteEmail(), motivo);

        String emailClienteNoti = pedidoEntity.getClienteEmail();
        Optional<ClienteEntity> clienteEntity = clienteRepository.findByEmail(emailClienteNoti);

        String token = clienteEntity.get().getToken();
        if(token != null) {
            notificationervice.sendNotifictionRechazoPedido(token, motivo);
        }
    }

    @Override
    public List<RestaurantesMejorCaliDto> restaurantesMejorVPI(){

        List<RestaurantesMejorCaliDto> dtoList = new ArrayList<>();
        List<RestauranteEntity> restaurantes = restauranteRepository.findByConfirmado(true);

        for(RestauranteEntity r :restaurantes){
            if(!r.getEmail().equals("deleteUserRest")) {
                RestaurantesMejorCaliDto dto = new RestaurantesMejorCaliDto();
                dto.setNombreRestaurante(r.getNombreRestaurante());
                dto.setCalificacion(r.getCalificacionVPI());
                dtoList.add(dto);
            }
        }

        Collections.sort(dtoList,Collections.reverseOrder());

        List<RestaurantesMejorCaliDto> restaurantesFinal = new ArrayList<>();
        Integer contador = 0;

        for(RestaurantesMejorCaliDto re: dtoList){

            if(contador > 9)
                break;
            else {
                restaurantesFinal.add(re);
                contador++;
            }
        }
        return restaurantesFinal;

    }

    @Override
    public List<RestaurantesMejorCaliDto> restaurantesMejorTiempo(){

        List<RestaurantesMejorCaliDto> dtoList = new ArrayList<>();
        List<RestauranteEntity> restaurantes = restauranteRepository.findByConfirmado(true);


        for(RestauranteEntity r :restaurantes){
            if(!r.getEmail().equals("deleteUserRest")) {
                RestaurantesMejorCaliDto dto = new RestaurantesMejorCaliDto();
                dto.setNombreRestaurante(r.getNombreRestaurante());
                Double cantPedidos = Double.valueOf(pedidoRepository.cantPedidos(r.getEmail(), PedidoEntity.EstadoPedido.Confirmado));

                List<PedidoEntity> pedidos = pedidoRepository.findByRestauranteOrderByIdPedidoDesc(r);
                Integer tiempoE = 0;
                Double tiempoPromedio;
                for(PedidoEntity p :pedidos){
                    if(p.getTiempoE()!=null) {
                        tiempoE +=p.getTiempoE();
                    }
                }
                if(cantPedidos!=0.0) {
                    Double t = tiempoE / cantPedidos;
                    tiempoPromedio = new BigDecimal(t).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

                    dto.setCalificacion(tiempoPromedio);
                    dtoList.add(dto);
                }

            }
        }

        Collections.sort(dtoList,Collections.reverseOrder());

        List<RestaurantesMejorCaliDto> restaurantesFinal = new ArrayList<>();
        Integer contador = 0;

        for(RestaurantesMejorCaliDto re: dtoList){

            if(contador > 9)
                break;
            else {
                restaurantesFinal.add(re);
                contador++;
            }
        }
        return restaurantesFinal;

    }

    @Override
    public List<RestaurantesMejorCaliDto> restaurantesMejorCaliCliente(){

        List<RestaurantesMejorCaliDto> dtoList = new ArrayList<>();
        List<RestauranteEntity> restaurantes = restauranteRepository.findByConfirmado(true);


        for(RestauranteEntity r :restaurantes){
            if(!r.getEmail().equals("deleteUserRest")) {
                RestaurantesMejorCaliDto dto = new RestaurantesMejorCaliDto();
                dto.setNombreRestaurante(r.getNombreRestaurante());

                Double cali = obtenerCalificacionGlobal(r.getEmail());
                    dto.setCalificacion(cali);
                    dtoList.add(dto);

            }
        }
        Collections.sort(dtoList,Collections.reverseOrder());

        List<RestaurantesMejorCaliDto> restaurantesFinal = new ArrayList<>();
        Integer contador = 0;

        for(RestaurantesMejorCaliDto re: dtoList){

            if(contador > 9)
                break;
            else {
                restaurantesFinal.add(re);
                contador++;
            }
        }
        return restaurantesFinal;

    }







}
