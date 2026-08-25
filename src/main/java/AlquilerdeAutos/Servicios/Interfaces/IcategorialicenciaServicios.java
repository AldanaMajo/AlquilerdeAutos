package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.CategoriaLicencia;

import java.util.List;

public interface IcategorialicenciaServicios {
    List<CategoriaLicencia> listar();
    CategoriaLicencia buscarPorId(Integer id);
    CategoriaLicencia guardar(CategoriaLicencia categoriaLicencia);
    CategoriaLicencia actualizar(Integer id, CategoriaLicencia categoriaLicencia);
    void eliminar(Integer id);
}
