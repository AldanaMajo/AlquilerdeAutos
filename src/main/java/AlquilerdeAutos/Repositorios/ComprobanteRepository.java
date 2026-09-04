package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Integer> {

    Optional<Comprobante> findByPago_Id(Integer idPago);

    Optional<Comprobante> findBySerieAndCorrelativo(String serie, String correlativo);

    @Query("SELECT c FROM Comprobante c WHERE " +
            "LOWER(c.Serie) LIKE LOWER(CONCAT('%', :buscar, '%')) OR " +
            "LOWER(c.Correlativo) LIKE LOWER(CONCAT('%', :buscar, '%')) OR " +
            "LOWER(CAST(c.Tipo AS string)) LIKE LOWER(CONCAT('%', :buscar, '%'))")
    List<Comprobante> buscarPorTermino(@Param("buscar") String buscar);
}