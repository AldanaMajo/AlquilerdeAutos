package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {

    List<Alquiler> findByCliente_Id(Integer idCliente);

    List<Alquiler> findByVehiculoId(Integer idVehiculo);

    List<Alquiler> findByUsuarioId(Integer idUsuario);

    List<Alquiler> findByEstado(Alquiler.EstadoAlquiler estado);
}