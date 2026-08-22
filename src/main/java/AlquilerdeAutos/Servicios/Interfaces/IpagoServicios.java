package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Pago;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IpagoServicios {

    List<Pago> listar();

    Optional<Pago> obtenerPorId(Integer id);

    Pago registrarPago(Integer idAlquiler, String metodoPago, BigDecimal monto);

    void eliminar(Integer id);
}