package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByDocumentoIdentidad(String documentoIdentidad);

    Optional<Cliente> findByNumeroLicencia(String numeroLicencia);

    boolean existsByDocumentoIdentidad(String documentoIdentidad);
}