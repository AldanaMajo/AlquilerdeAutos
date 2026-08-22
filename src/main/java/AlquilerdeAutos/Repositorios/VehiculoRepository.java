package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    Optional<Vehiculo> findByPlaca(String placa);

    List<Vehiculo> findByEstado(Vehiculo.EstadoVehiculo estado);

    List<Vehiculo> findByMarca_Id(Integer idMarca);

    List<Vehiculo> findByCategoria_Id(Integer idCategoria);
}
