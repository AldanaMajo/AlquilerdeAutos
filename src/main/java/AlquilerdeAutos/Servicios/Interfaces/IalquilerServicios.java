package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Alquiler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IalquilerServicios {
    List<Alquiler> listar();
    Alquiler buscarPorId(Integer id);
    Alquiler iniciar(Integer idReserva, LocalDate fechaEntrega, BigDecimal precioDia);
    Alquiler finalizar(Integer idAlquiler, LocalDate fechaDevolucion);
    void eliminar(Integer id);
}