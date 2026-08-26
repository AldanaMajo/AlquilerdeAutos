package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Integer> {

    Optional<Comprobante> findByPago_Id(Integer idPago);

    Optional<Comprobante> findBySerieAndCorrelativo(String serie, String correlativo);
}