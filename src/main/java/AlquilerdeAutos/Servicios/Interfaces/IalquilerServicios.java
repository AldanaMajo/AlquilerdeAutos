package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Alquiler;

import java.util.List;

public interface IalquilerServicios {
    List<Alquiler> listar();
    List<Alquiler> listarPorCliente(Integer idCliente);
    Alquiler buscarPorId(Integer id);
    Alquiler guardar(Alquiler alquiler);
    Alquiler actualizar(Integer id, Alquiler alquiler);
    Alquiler finalizar(Integer id, Integer kilometrajeFinal);
    Alquiler cancelar(Integer id);
    void eliminar(Integer id);
}
