package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Comprobante;

public interface IcomprobanteServicios {
    Comprobante buscarPorId(Integer id);
    Comprobante buscarPorPago(Integer idPago);
    Comprobante guardar(Comprobante comprobante);
    Comprobante actualizar(Integer id, Comprobante comprobante);
    void eliminar(Integer id);
}
