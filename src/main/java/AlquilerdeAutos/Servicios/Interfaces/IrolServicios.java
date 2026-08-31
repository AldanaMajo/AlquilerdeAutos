package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Rol;
import java.util.List;

public interface IrolServicios {
    List<Rol> listar();
    Rol buscarPorId(Integer id);
    Rol guardar(Rol rol);
    Rol actualizar(Integer id, Rol rol);
    void eliminar(Integer id);
    List<Rol> buscarPorNombre(String nombre);
}