package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.CategoriaLicencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoriaLicenciaRepository extends JpaRepository<CategoriaLicencia, Integer> {

    Optional<CategoriaLicencia> findByNombre(String nombre);
}