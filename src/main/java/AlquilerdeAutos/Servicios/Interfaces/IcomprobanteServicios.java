package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Comprobante;
import java.util.List;

public interface IcomprobanteServicios {
    List<Comprobante> obtenerTodos(String buscar);
    Comprobante buscarPorId(Integer id);
    Comprobante buscarPorPago(Integer idPago);
    Comprobante guardar(Comprobante comprobante);
    Comprobante actualizar(Integer id, Comprobante comprobante);
    void eliminar(Integer id);
}
