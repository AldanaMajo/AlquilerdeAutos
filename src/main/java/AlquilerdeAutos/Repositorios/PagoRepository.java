package AlquilerdeAutos.Repositorios;

import AlquilerdeAutos.Modelos.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

    List<Pago> findByAlquiler_Id(Integer idAlquiler);

    List<Pago> findByMetodoPago(Pago.MetodoPago metodoPago);
}