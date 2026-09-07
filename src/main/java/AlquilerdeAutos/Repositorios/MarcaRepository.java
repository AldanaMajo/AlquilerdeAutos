package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    boolean existsByNombreIgnoreCase(String nombre);
    Optional<Marca> findByNombreIgnoreCase(String nombre);
}