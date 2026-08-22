package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComprobanteRepository extends JpaRepository<Comprobante, Integer> {

    Optional<Comprobante> findByPago_Id(Integer idPago);

    Optional<Comprobante> findBySerieAndCorrelativo(String serie, String correlativo);
}