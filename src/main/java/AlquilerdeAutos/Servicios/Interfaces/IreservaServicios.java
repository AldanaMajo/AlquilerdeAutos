package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Reserva;

import java.util.List;

public interface IreservaServicios {
    List<Reserva> listar();
    List<Reserva> listarPorCliente(Integer idCliente);
    Reserva buscarPorId(Integer id);
    Reserva buscarPorCodigo(String codigoReserva);
    Reserva guardar(Reserva reserva);
    Reserva actualizar(Integer id, Reserva reserva);
    Reserva confirmar(Integer id);
    Reserva cancelar(Integer id);
    void eliminar(Integer id);
}
