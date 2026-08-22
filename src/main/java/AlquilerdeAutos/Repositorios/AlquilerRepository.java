package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {

    List<Alquiler> findByCliente_Id(Integer idCliente);

    List<Alquiler> findByVehiculo_Id(Integer idVehiculo);

    List<Alquiler> findByUsuario_Id(Integer idUsuario);

    List<Alquiler> findByEstado(Alquiler.EstadoAlquiler estado);
}