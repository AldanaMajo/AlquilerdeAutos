package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Comprobante;

import java.util.Optional;

public interface IcomprobanteServicios {

    Optional<Comprobante> obtenerPorId(Integer id);

    Optional<Comprobante> obtenerPorPago(Integer idPago);

    Comprobante emitir(Integer idPago, String serie, Comprobante.TipoComprobante tipo);

    void eliminar(Integer id);
}