package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Marca;

import java.util.List;

public interface ImarcaServicios {
    List<Marca> listar();
    Marca buscarPorId(Integer id);
    Marca guardar(Marca marca);
    Marca actualizar(Integer id, Marca marca);
    void eliminar(Integer id);
}