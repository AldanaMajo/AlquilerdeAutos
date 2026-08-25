package AlquilerdeAutos.Servicios.Interfaces;

import AlquilerdeAutos.Modelos.Vehiculo;

import java.util.List;

public interface IvehiculoServicios {
    List<Vehiculo> listar();
    List<Vehiculo> listarDisponibles();
    Vehiculo buscarPorId(Integer id);
    Vehiculo buscarPorPlaca(String placa);
    Vehiculo guardar(Vehiculo vehiculo);
    Vehiculo actualizar(Integer id, Vehiculo vehiculo);
    Vehiculo cambiarEstado(Integer id, Vehiculo.EstadoVehiculo nuevoEstado);
    void eliminar(Integer id);
}
