package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Usuario;

import java.util.List;

public interface IusuarioServicios {
    List<Usuario> listar();
    Usuario buscarPorId(Integer id);
    Usuario buscarPorGmail(String gmail);
    Usuario guardar(Usuario usuario);
    Usuario actualizar(Integer id, Usuario usuario);
    void eliminar(Integer id);
}