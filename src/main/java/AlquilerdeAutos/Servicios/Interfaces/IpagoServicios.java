package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Pago;

import java.math.BigDecimal;
import java.util.List;

public interface IpagoServicios {
    List<Pago> listar();
    Pago buscarPorId(Integer id);
    Pago registrarPago(Integer idAlquiler, String metodoPago, BigDecimal monto);
    void eliminar(Integer id);
}