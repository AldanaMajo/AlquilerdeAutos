package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Cliente;

import java.util.List;

public interface IclienteServicios {
    List<Cliente> listar();
    Cliente buscarPorId(Integer id);
    Cliente buscarPorDocumento(String documentoIdentidad);
    Cliente guardar(Cliente cliente);
    Cliente actualizar(Integer id, Cliente cliente);
    void eliminar(Integer id);
}
