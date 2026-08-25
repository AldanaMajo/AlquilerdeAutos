package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Pago;

import java.util.List;

public interface IpagoServicios {
    List<Pago> listar();
    List<Pago> listarPorAlquiler(Integer idAlquiler);
    Pago buscarPorId(Integer id);
    Pago guardar(Pago pago);
    Pago actualizar(Integer id, Pago pago);
    void eliminar(Integer id);
}
