package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    Optional<Vehiculo> findByPlaca(String placa);

    List<Vehiculo> findByEstado(Vehiculo.EstadoVehiculo estado);

    List<Vehiculo> findByMarcaId(Integer idMarca);

    List<Vehiculo> findByCategoriaId(Integer idCategoria);
}
