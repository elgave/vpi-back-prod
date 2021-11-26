package com.VPI.VPI.Services;


import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Entities.*;
import com.VPI.VPI.Repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.*;

@Service

public class ClienteService implements IClienteService {
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IPedidoRepository pedidoRepository;
    @Autowired
    private IItemRepository itemRepository;
    @Autowired
    private IRestauranteRepository restauranteRepository;
    @Autowired
    private IMenuRepository menuRepository;
    @Autowired
    private IAgregadoRepository agregadoRepository;

    @Autowired
    private  ICalificacionRepository calificacionRepository;

    @Autowired
    private  IClienteService clienteService;

    @Autowired
    private IFavoritoRepository favoritoRepository;

    @Autowired
    private IDireccionRepository direccionRepository;
    @Autowired
    private IReclamoRepository reclamoRepository;

    @Autowired
    private WebSocketService webSocketService;


    private ClienteEntity mapToEntity(NuevoClienteDto clienteDto)  {
        ClienteEntity clienteEntity = modelMapper.map(clienteDto, ClienteEntity.class);
        return clienteEntity;
    }

    private NuevoClienteDto mapToDto(ClienteEntity clienteEntity)  {
        NuevoClienteDto clienteDto = modelMapper.map(clienteEntity, NuevoClienteDto.class);
        return clienteDto;
    }
    private PedidoEntity mapToEntity(AltaPedidoDto altaPedidoDto)  {
        PedidoEntity pedidoEntity = modelMapper.map(altaPedidoDto, PedidoEntity.class);
        return pedidoEntity;
    }

    private ItemEntity mapToEntity(AltaItemDto altaItemDto)  {
        ItemEntity itemEntity = modelMapper.map(altaItemDto, ItemEntity.class);
        return itemEntity;
    }

    private List<ItemEntity> mapToEntitys(List<AltaItemDto> altaItemDto) {
        List<ItemEntity> itemEntities = Arrays.asList(modelMapper.map(altaItemDto, ItemEntity[].class));
        return itemEntities;
    }

    private List<RestauranteDto> mapToDto(List<RestauranteEntity> restauranteEntities) {
        List<RestauranteDto> restauranteDtos = Arrays.asList(modelMapper.map(restauranteEntities, RestauranteDto[].class));
        return restauranteDtos;
    }

    private List<MenuDto> mapToDtos(List<MenuEntity> menuEntity)  {
        List<MenuDto> menusDto = Arrays.asList(modelMapper.map(menuEntity, MenuDto[].class));
        return menusDto;
    }

    private List<AltaAgregadoDto> mapAgregadoToDtos(List<AgregadoEntity> agregadoEntities)  {
        List<AltaAgregadoDto> agregadoDtos = Arrays.asList(modelMapper.map(agregadoEntities, AltaAgregadoDto[].class));
        return agregadoDtos;
    }

    private List<AltaPedidoDto> mapPedidoToDtos(List<PedidoEntity> pedidoEntities)  {
        List<AltaPedidoDto> altaPedidoDtos = Arrays.asList(modelMapper.map(pedidoEntities, AltaPedidoDto[].class));
        return altaPedidoDtos;
    }

    private RestauranteDto mapToDto(RestauranteEntity restauranteEntity)  {
        RestauranteDto restauranteDto = modelMapper.map(restauranteEntity, RestauranteDto.class);
        return restauranteDto;
    }

    private List<DireccionDto> mapDireccionToDtos(List<DireccionEntity> direccionEntities)  {
        List<DireccionDto> direccionDtos = Arrays.asList(modelMapper.map(direccionEntities, DireccionDto[].class));
        return direccionDtos;
    }

    private List<PedidoDto> mapPedidoDetalleToDtos(List<PedidoEntity> pedidoEntities)  {
        List<PedidoDto> pedidoDtos = Arrays.asList(modelMapper.map(pedidoEntities, PedidoDto[].class));
        return pedidoDtos;
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
            pedidoDto.setDireccion(p.getDireccion());
            pedidoDto.setTiempoE(p.getTiempoE());
            pedidoDto.setComentarioEstado(p.getComentarioEstado());


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
                reclamoDto.setTipo(reclamos.get(i).getTipo());
                reclamoDto.setComentario(reclamos.get(i).getComentario());
                reclamoDto.setFecha(reclamos.get(i).getFecha());
                reclamoDtos.add(reclamoDto);
            }
            pedidoDto.setReclamos(reclamoDtos);

            pedidoDtos.add(pedidoDto);
        }
        return pedidoDtos;
    }




    public ClienteEntity createCliente(NuevoClienteDto clienteDto ) {
        ClienteEntity cliente= mapToEntity(clienteDto);

        String pass = cliente.getPassWord();
        String passEncript = passwordEncoder.encode(pass);

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setEmail(cliente.getEmail());
        clienteEntity.setPassWord(passEncript);
        clienteEntity.setRol(ClienteEntity.Rol.Cliente);

        Date currentSqlDate = new Date(System.currentTimeMillis());
        clienteEntity.setFecha(currentSqlDate);
        clienteEntity.setNombre(cliente.getNombre());
        clienteEntity.setApellido(cliente.getApellido());
        clienteEntity.setBloqueado(false);
        clienteEntity.setCelular(cliente.getCelular());
        clienteEntity.setFoto(cliente.getFoto());

        return clienteRepository.save(clienteEntity);
    }

    @Override
    public boolean isBloqueado(String email) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findByEmail(email);
        ClienteEntity cliente = clienteOpt.get();
        return cliente.getBloqueado();
    }

    public PedidoEntity altaPedido(AltaPedidoDto altaPedidoDto, String idRest, String idCliente ) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findByEmail(idCliente);
        ClienteEntity clienteEntity = clienteOpt.get();
        Optional<RestauranteEntity> restOpt = restauranteRepository.findByEmail(idRest);
        RestauranteEntity restauranteEntity = restOpt.get();

        PedidoEntity pedido= mapToEntity(altaPedidoDto);
        List<ItemEntity> items= mapToEntitys(altaPedidoDto.getItems());



        PedidoEntity pedidoEntity = new PedidoEntity();
        for (int i=0;i<items.size();i++) {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setNombre(items.get(i).getNombre());
            itemEntity.setCantidad(items.get(i).getCantidad());
            itemEntity.setPrecio(items.get(i).getPrecio());
            pedidoEntity.addItem(itemEntity);

        }

        pedidoEntity.setPagoOnline(pedido.getPagoOnline());
        pedidoEntity.setEstado(PedidoEntity.EstadoPedido.Pendiente);
        pedidoEntity.setPagoAcreditado(pedido.getPagoAcreditado());
        pedidoEntity.setComentario(pedido.getComentario());
        pedidoEntity.setPrecioTotal(pedido.getPrecioTotal());
        Date currentSqlDate = new Date(System.currentTimeMillis());
        pedidoEntity.setFecha(currentSqlDate);
        pedidoEntity.setRestaurante(restauranteEntity);
        pedidoEntity.setCliente(clienteEntity);
        pedidoEntity.setDireccion(pedido.getDireccion());

        notifyFrontend();

        return pedidoRepository.save(pedidoEntity);


    }

    @Override
    public List<RestauranteDto> getRestaurantes() {
        List<RestauranteDto> restauranteDtos = mapToDto(restauranteRepository.findByConfirmadoTrueAndAbiertoTrueAndBloqueadoFalse());
        return restauranteDtos;
    }

    @Override
    public List<MenuDto> getMenusResturante(String nomRestaurante) {
        List<MenuDto> menusDto = mapToDtos(menuRepository.menusRestaurante(nomRestaurante));
        return menusDto;
    }

    @Override
    public List<AltaAgregadoDto> agragadosByMenu(Integer idMenu) {
        Optional<MenuEntity> menuOpt = menuRepository.findByIdMenu(idMenu);
        MenuEntity menu = menuOpt.get();
        List<AltaAgregadoDto> agregadoDtos = mapAgregadoToDtos(agregadoRepository.findByMenus(menu));
        return agregadoDtos;
    }

    public void agregarFavorito(String idCliente, String idRestaurante){

        FavoritoEntity favorito = new FavoritoEntity();

        favorito.setCliente_id(idCliente);
        favorito.setRestaurante_id(idRestaurante);

        favoritoRepository.save(favorito);
    }
    // rafa empieza
    public void agregarTokenMobile(String idCliente, String token){

        Optional<ClienteEntity> cliente = clienteRepository.findByEmail(idCliente);
        ClienteEntity clienteEntity = cliente.get();
        clienteEntity.setToken(token);
        clienteRepository.save(clienteEntity);
    }
    // rafa termina

    public void agregarDireccion (DireccionDto direccion){

        Optional<ClienteEntity> cliente = clienteRepository.findByEmail(direccion.getIdCliente());

        DireccionEntity dire = new DireccionEntity();
        dire.setApto(direccion.getApto());
        dire.setNombre(direccion.getNombre());
        dire.setBarrio(direccion.getBarrio());
        dire.setCalle(direccion.getCalle());
        dire.setEsquina(direccion.getEsquina());
        dire.setNumero(direccion.getNumero());
        dire.setPrincipal(direccion.getPrincipal());
        dire.setCliente(cliente.get());
        if(direccion.getPrincipal()){
            Optional<ClienteEntity> clienteOPt = clienteRepository.findByEmail(direccion.getIdCliente());
            ClienteEntity clienteEntity = clienteOPt.get();
            List<DireccionEntity> direccionEntities = direccionRepository.findByCliente(clienteEntity);
            for (int i=0;i<direccionEntities.size();i++) {
                DireccionEntity direccionEntity = new DireccionEntity();
                if(direccionEntities.get(i).getPrincipal()){
                    direccionEntities.get(i).setPrincipal(false);
                }
            }
            
        }

        direccionRepository.save(dire);

    }

    @Override
    public ReclamoEntity altaReclamo(ReclamoDto reclamoDto) {
        ReclamoEntity reclamoEntity = new ReclamoEntity();
        reclamoEntity.setComentario(reclamoDto.getComentario());
        Date currentSqlDate = new Date(System.currentTimeMillis());
        reclamoEntity.setFecha(currentSqlDate);
        reclamoEntity.setEstado(ReclamoEntity.EstadoReclamo.Pendiente);

        Optional<PedidoEntity> pedidoOPt = pedidoRepository.findByIdPedido(reclamoDto.getPedido());
        PedidoEntity pedidoEntity = pedidoOPt.get();
        reclamoEntity.setPedido(pedidoEntity);
        reclamoEntity.setTipo(reclamoDto.getTipo());


        return reclamoRepository.save(reclamoEntity);
    }

    @Override
    public RestauranteDto getDatosRestaurante(String nomRestaurante) {
        Optional<RestauranteEntity> restauranteOPt = restauranteRepository.findByNombreRestaurante(nomRestaurante);
        RestauranteEntity restaurante = restauranteOPt.get();
        RestauranteDto restauranteDto = mapToDto(restaurante);
        return restauranteDto;
    }

    @Override
    public List<DireccionDto> getDirecciones(String email) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findByEmail(email);
        ClienteEntity clienteEntity = clienteOpt.get();
        List<DireccionDto> direccioDtos = mapDireccionToDtos(direccionRepository.findByCliente(clienteEntity));
        return direccioDtos;
    }

    public List<UsuarioDto> buscarClientes(Boolean bloqueado, String busqueda){

        List<UsuarioDto> clienteBusquedaDtoList = new ArrayList<>();

        List<ClienteEntity> clientes = new ArrayList<>();
        if(bloqueado){

            clientes = clienteRepository.findByBloqueadoTrueAndEmailIgnoreCaseContainingOrBloqueadoTrueAndNombreIgnoreCaseContainingOrBloqueadoTrueAndCelularIgnoreCaseContaining(busqueda,busqueda,busqueda);

        }
        if(!bloqueado){
            clientes = clienteRepository.findByBloqueadoFalseAndEmailIgnoreCaseContainingOrBloqueadoFalseAndNombreIgnoreCaseContainingOrBloqueadoFalseAndCelularIgnoreCaseContaining(busqueda,busqueda,busqueda);
        }

        for(ClienteEntity c: clientes){

            UsuarioDto cli = new UsuarioDto();

            cli.setEmail(c.getEmail());
            cli.setBloqueado(c.getBloqueado());
            cli.setCelular(c.getCelular());
            cli.setNombre( c.getNombre());

            cli.setFechaCreacion(c.getFecha());
            cli.setCalificacionGlobal(obtenerCalificacionGlobal(c.getEmail()));

            clienteBusquedaDtoList.add(cli);
        }

        return clienteBusquedaDtoList;
    }

    public Double obtenerCalificacionGlobal(String idCliente){

        Double calificacionGlobal = 0.0;
        try {
            List<CalificacionEntity> calificaciones = clienteRepository.obtenerMisCalificaciones(idCliente);

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

    @Override
    public List<PedidoDto> getPedidos(String email) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findByEmail(email);
        ClienteEntity cliente = clienteOpt.get();
        List<PedidoDto> pedidoDtos = mapPedidoConReclamoToDtosManual(pedidoRepository.findByClienteOrderByIdPedidoDesc(cliente));

        return pedidoDtos;
    }
    public void calificarRestaurante(CalificacionDto calificacionDto){

        CalificacionEntity calificacion = new CalificacionEntity();

        List<CalificacionEntity> cal = calificacionRepository.obtenerCalificacionCliente(calificacionDto.getIdCliente(),calificacionDto.getIdRestaurante());

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

    @Override
    public boolean isFavorito(FavoritoDto favoritoDto) {

        List<FavoritoEntity> favoritoEntityList = favoritoRepository.obtenerFavoritoCliente(favoritoDto.getIdCliente(),favoritoDto.getIdRestaurante());

        return favoritoEntityList.stream().count() > 0;
    }

    @Override
    public Integer cantDirecciones(String email) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findByEmail(email);
        ClienteEntity cliente = clienteOpt.get();
        return  direccionRepository.cantDirecciones(cliente);
    }

    public void eliminarCalificacionRestaurante(CalificacionDto calificacionDto){

        List<CalificacionEntity> cal = calificacionRepository.obtenerCalificacionCliente(calificacionDto.getIdCliente(),calificacionDto.getIdRestaurante());

        calificacionRepository.delete(cal.get(0));

    }

    public void eliminarDireccion(Integer id){

        Optional <DireccionEntity> dir = direccionRepository.findById(id);

        direccionRepository.delete(dir.get());
    }


    public  void modificarDireccion (ModificarDireccionDto modificarDireccionDto){

        Optional<DireccionEntity> dir = direccionRepository.findById(modificarDireccionDto.getId());

        if(modificarDireccionDto.getPrincipal()){

            Optional<ClienteEntity> cli = clienteRepository.findByEmail(modificarDireccionDto.getIdCliente());
            List<DireccionEntity> direcciones = direccionRepository.findByCliente(cli.get());

            for(DireccionEntity d : direcciones){
                d.setPrincipal(false);

                direccionRepository.save(d);
            }

            dir.get().setPrincipal(true);

        }
        else{
            dir.get().setPrincipal(false);
        }

        dir.get().setNumero(modificarDireccionDto.getNumero());
        dir.get().setEsquina(modificarDireccionDto.getEsquina());
        dir.get().setCalle(modificarDireccionDto.getCalle());
        dir.get().setBarrio(modificarDireccionDto.getBarrio());
        dir.get().setApto(modificarDireccionDto.getApto());

        direccionRepository.save(dir.get());

    }

    public ClienteDto obtenerMisDatos(String email){

        Optional<ClienteEntity> clienteEntity = clienteRepository.findByEmail(email);

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setFoto(clienteEntity.get().getFoto());
        clienteDto.setNombre(clienteEntity.get().getNombre());
        clienteDto.setCelular(clienteEntity.get().getCelular());
        clienteDto.setApellido(clienteEntity.get().getApellido());

        return  clienteDto;
    }

    public Boolean puedeCalificar(CalificacionDto calificacionDto){

        Optional<RestauranteEntity> restaurante = restauranteRepository.findByEmail(calificacionDto.getIdRestaurante());
        Optional<ClienteEntity> cliente = clienteRepository.findByEmail(calificacionDto.getIdCliente());
        List<PedidoEntity> pedidos = pedidoRepository.findByRestauranteAndCliente(restaurante.get(),cliente.get());

        if (pedidos.stream().count() > 0){
            return true;
        }else
            return  false;
    }

    public  Integer obtenerCalificacionRestaurante(CalificacionDto calificacionDto){

        Integer calificacion = 0;
        List<CalificacionEntity> cal = calificacionRepository.obtenerCalificacionCliente(calificacionDto.getIdCliente(),calificacionDto.getIdRestaurante());

        if(cal.stream().count() > 0) {
            calificacion = cal.get(0).getPuntuacion();
        }

        return  calificacion;
    }

    public  void eliminarMiCuenta(String idCliente){

        Optional<ClienteEntity> deleteUser = clienteRepository.findByEmail("deleteUser");
        Optional<ClienteEntity> clienteEntity = clienteRepository.findByEmail(idCliente);

        List<PedidoEntity> pedidoEntityList = pedidoRepository.findByClienteOrderByIdPedidoDesc(clienteEntity.get());

        if(pedidoEntityList.stream().count() >0 ){

            for(PedidoEntity p: pedidoEntityList){

                p.setCliente(deleteUser.get());

                pedidoRepository.save(p);
            }

        }

        calificacionRepository.borrarCalificacionesHaciaCliente(idCliente);


        clienteRepository.delete(clienteEntity.get());


    }

    @Override
    public void quitarFavorito(String idCliente, String idRestaurante) {
        List<FavoritoEntity> favoritos = new ArrayList<>();
        favoritos = favoritoRepository.obtenerFavoritoCliente(idCliente, idRestaurante);

        for(FavoritoEntity f: favoritos){
            favoritoRepository.delete(f);
        }
    }

    @Override
    public List<RestauranteDto> restaurantesFavoritos(String idCliente) {
        List<RestauranteDto> dtos = mapToDto(restauranteRepository.restaurantesFavoritos(idCliente));
        return dtos;
    }

    @Override
    public List<RestauranteDto> buscarRestaurantesAbiertos(String busqueda) {
        List<RestauranteDto> restauranteDtos = mapToDto(restauranteRepository.findByConfirmadoTrueAndAbiertoTrueAndBloqueadoFalseAndEmailIgnoreCaseContainingOrConfirmadoTrueAndAbiertoTrueAndBloqueadoFalseAndNombreRestauranteIgnoreCaseContainingOrConfirmadoTrueAndAbiertoTrueAndBloqueadoFalseAndCelularIgnoreCaseContaining(busqueda,busqueda,busqueda));
        return restauranteDtos;
    }

    @Override
    public boolean existsDireccion(String idCliente, String nombre) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findByEmail(idCliente);
        ClienteEntity cliente = clienteOpt.get();
        return direccionRepository.existsByClienteAndNombre(cliente,nombre);
    }

    private void notifyFrontend(){
        final String entityTopic = "pedidos";
        if (entityTopic == null){

            return;
        }

        webSocketService.sendMessage(entityTopic);
    }


}
