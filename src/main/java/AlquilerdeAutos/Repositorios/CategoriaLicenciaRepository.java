package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.CategoriaLicencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaLicenciaRepository extends JpaRepository<CategoriaLicencia, Integer> {

    Optional<CategoriaLicencia> findByNombre(String nombre);
}