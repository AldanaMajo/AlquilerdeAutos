package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    Optional<Reserva> findByCodigoReserva(String codigoReserva);

    List<Reserva> findByCliente_Id(Integer idCliente);

    List<Reserva> findByVehiculoId(Integer idVehiculo);

    List<Reserva> findByEstado(Reserva.EstadoReserva estado);
}