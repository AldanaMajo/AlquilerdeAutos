package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByDocumentoIdentidad(String documentoIdentidad);

    Optional<Cliente> findByNumeroLicencia(String numeroLicencia);

    boolean existsByDocumentoIdentidad(String documentoIdentidad);

    // Consulta para la barra de búsqueda en el Index.html
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR LOWER(c.apellido) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR c.documentoIdentidad LIKE CONCAT('%', :filtro, '%')")
    List<Cliente> buscarPorFiltro(@Param("filtro") String filtro);
}