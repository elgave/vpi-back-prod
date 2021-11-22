package com.VPI.VPI.Services;

import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Entities.AgregadoEntity;
import com.VPI.VPI.Entities.MenuEntity;
import com.VPI.VPI.Entities.RestauranteEntity;

import java.io.IOException;
import java.util.List;

public interface IRestauranteService {
    List<RestauranteEntity> getAllClientes();
    RestauranteEntity createRestaurante(NuevoRestauranteDto restauranteDto);
    MenuEntity createMenu(NuevoMenuDto menuDto, String idRestaurante);
    boolean existsMenu(String nombre,String idRestaurante);
    Boolean existsByNombreRestaurante(String  nombreRestaurante);
    Boolean existsByNroHabilitacion(String  nroHabilitacion);
    boolean isBloqueado(String email);
    boolean isConfirmado(String email);
    AgregadoEntity altaAgregado(AltaAgregadoDto agregadoDto, String idRestaurante);
    List<RestauranteDto> findByConfirmado(Boolean confirmado);
    void deleteByEmail(String email,String motivo);
    List<PedidoDto> pedidoByRestaurante(String idRestaurante);
    Boolean abrirCerrar (AbiertoDto abiertoDto);
    void pagoEnEfectivo(Integer idPedido);
    void confirmarPedido(Integer idPedido, Integer tiempoE) throws IOException;
    void aceptarReclamo(Integer idReclamo, String estado) throws IOException;
    List<UsuarioDto> buscarRestaurantes(Boolean bloqueado, String busqueda);
    Double obtenerCalificacionGlobal(String idRestaurante);
    void rechazarReclamo(Integer idReclamo, String motivo) throws IOException;
    List<PedidoDto> pedidosPendienteRestaurante(String idRestaurante);
    List<ReclamoDto> listarReclamos(BusquedaReclamoDto busquedaReclamoDto);
    Boolean isAbierto(String idRestaurante);
    List<ReclamoDto> reclamosConFiltro(ReclamosFiltroDto dto);
    List<PedidoDto> pedidosSinPendienteRestaurante(String idRestaurante);
    void calificarCliente(CalificacionDto calificacionDto);
    void eliminarCalificacionCliente(CalificacionDto calificacionDto);
    void eliminarAgregado(Integer id);
    void modificarAgregado(ModificacionAgregadoDto modificacionAgregadoDto);

    List<MenuDto> listMenus(String email);

    List<AltaAgregadoDto> listarAgregados(String idRestaurante);

    void eliminarMenu(Integer idMenu);
    RestauranteDto obtenerMisDatos(String email);
    void asociarAgregados(AsociarAgregadoDto asociarAgregadoDto);

    List<AltaAgregadoDto> agregadosQueNoEstanEnElMenu(RestMenuDto restMenuDto);

    Integer obtenerCalificacionCliente(CalificacionDto calificacionDto);
    void eliminarCuenta(String idRestaurante);

    void modificarMenu(MenuDto menuDto);
    List<AltaAgregadoDto> agragadosByMenu(Integer idMenu);

    void quitarAgregados(QuitarAgregadoDto quitarAgregadoDto);


    List<RestaurantesMasVentasDto> restaurantesMasVentas();

    List<RestauranteDto> restConPromo();
    List<MenuDto> menuCatagorias(String idRestaurante, String categoria);

    List<PedidoDto> balance(BalanceDto dto);
    Boolean existeAgregado(String idRestaurante, String nombreAgragado);
    void rechazarPedido(Integer idPedido, String motivo) throws IOException;
    List<RestaurantesMejorCaliDto> restaurantesMejorVPI();
    List<RestaurantesMejorCaliDto> restaurantesMejorTiempo();
    List<RestaurantesMejorCaliDto> restaurantesMejorCaliCliente();

}
