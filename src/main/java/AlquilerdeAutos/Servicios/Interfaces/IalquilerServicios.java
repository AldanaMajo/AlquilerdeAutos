package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Alquiler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IalquilerServicios {

    List<Alquiler> listar();

    Optional<Alquiler> obtenerPorId(Integer id);

    Alquiler iniciar(Integer idReserva, LocalDate fechaEntrega, BigDecimal precioDia);

    Alquiler finalizar(Integer idAlquiler, LocalDate fechaDevolucion);

    void eliminar(Integer id);
}