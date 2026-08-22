package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Reserva;

import java.util.List;

public interface IreservaServicios {
    List<Reserva> listar();
    Reserva buscarPorId(Integer id);
    Reserva crear(Reserva reserva);
    Reserva actualizar(Integer id, Reserva reserva);
    Reserva cancelar(Integer id);
    void eliminar(Integer id);
}