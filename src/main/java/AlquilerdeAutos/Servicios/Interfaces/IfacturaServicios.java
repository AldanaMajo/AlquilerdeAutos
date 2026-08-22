package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Factura;

import java.util.List;

public interface IfacturaServicios {
    List<Factura> listar();
    Factura buscarPorId(Integer id);
    Factura emitir(Integer idPago);
    void eliminar(Integer id);
}