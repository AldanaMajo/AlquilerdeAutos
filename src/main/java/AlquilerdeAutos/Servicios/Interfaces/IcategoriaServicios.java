package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Categoria;

import java.util.List;

public interface IcategoriaServicios {
    List<Categoria> listar();
    Categoria buscarPorId(Integer id);
    Categoria guardar(Categoria categoria);
    Categoria actualizar(Integer id, Categoria categoria);
    void eliminar(Integer id);
}