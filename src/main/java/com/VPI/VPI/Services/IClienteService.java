package com.VPI.VPI.Services;

import com.VPI.VPI.Dtos.*;
import com.VPI.VPI.Entities.ClienteEntity;
import com.VPI.VPI.Entities.PedidoEntity;
import com.VPI.VPI.Entities.ReclamoEntity;

import java.util.List;

public interface IClienteService {

    ClienteEntity createCliente(NuevoClienteDto clienteDto);
    boolean isBloqueado(String email);
    PedidoEntity altaPedido(AltaPedidoDto altaPedidoDto, String idRest, String idCliente);
    List<RestauranteDto> getRestaurantes();
    List<MenuDto> getMenusResturante(String nomRestaurante);
    List<AltaAgregadoDto> agragadosByMenu(Integer idMenu);
    void agregarFavorito(String idCliente, String idRestaurante);
    void agregarTokenMobile(String idCliente, String token); // rafa
    void agregarDireccion(DireccionDto direccion);
    ReclamoEntity altaReclamo(ReclamoDto reclamoDto);
    RestauranteDto getDatosRestaurante(String nomRestaurante);
    List<DireccionDto> getDirecciones(String email);
    List<UsuarioDto> buscarClientes(Boolean bloqueado, String busqueda);
    Double obtenerCalificacionGlobal(String idCliente);
    List<PedidoDto> getPedidos(String email);
    void calificarRestaurante(CalificacionDto calificacionDto);
    boolean isFavorito(FavoritoDto favoritoDto);
    Integer cantDirecciones(String email);
    void eliminarCalificacionRestaurante(CalificacionDto calificacionDto);
    void eliminarDireccion(Integer id);
    void modificarDireccion(ModificarDireccionDto modificarDireccionDto);
    ClienteDto obtenerMisDatos(String email);
    Boolean puedeCalificar(CalificacionDto calificacionDto);
    Integer obtenerCalificacionRestaurante(CalificacionDto calificacionDto);
    void eliminarMiCuenta(String idCliente);
    void quitarFavorito(String idCliente, String idRestaurante);
    List<RestauranteDto> restaurantesFavoritos(String idCliente);
    List<RestauranteDto> buscarRestaurantesAbiertos(String busqueda);
    boolean existsDireccion(String idCliente, String nombre);




}
