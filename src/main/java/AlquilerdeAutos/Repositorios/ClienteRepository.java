package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByDocumento_identidad(String documentoIdentidad);

    Optional<Cliente> findByNumero_licencia(String numeroLicencia);

    boolean existsByDocumento_identidad(String documentoIdentidad);
}