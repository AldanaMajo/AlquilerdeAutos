package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    Optional<Reserva> findByCodigo_reserva(String codigoReserva);

    List<Reserva> findByCliente_Id(Integer idCliente);

    List<Reserva> findByVehiculo_Id(Integer idVehiculo);

    List<Reserva> findByEstado(Reserva.EstadoReserva estado);
}