package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

    List<Pago> findByAlquiler_Id(Integer idAlquiler);

    List<Pago> findByMetodo_pago(Pago.MetodoPago metodoPago);
}