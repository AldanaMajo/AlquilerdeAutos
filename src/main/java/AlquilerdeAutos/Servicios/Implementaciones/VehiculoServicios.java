package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Vehiculo;
import AlquilerdeAutos.Repositorios.VehiculoRepository;
import AlquilerdeAutos.Servicios.Interfaces.IvehiculoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServicios implements IvehiculoServicios {

    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoServicios(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public List<Vehiculo> listar() {
        return vehiculoRepository.findAll();
    }

    @Override
    public List<Vehiculo> listarDisponibles() {
        return vehiculoRepository.findByEstado(Vehiculo.EstadoVehiculo.DISPONIBLE);
    }

    @Override
    public Vehiculo buscarPorId(Integer id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con id: " + id));
    }

    @Override
    public Vehiculo buscarPorPlaca(String placa) {
        return vehiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con placa: " + placa));
    }

    @Override
    public Vehiculo guardar(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo actualizar(Integer id, Vehiculo vehiculo) {
        Vehiculo existente = buscarPorId(id);
        existente.setPlaca(vehiculo.getPlaca());
        existente.setModelo(vehiculo.getModelo());
        existente.setAnio(vehiculo.getAnio());
        existente.setColor(vehiculo.getColor());
        existente.setPrecio_por_dia(vehiculo.getPrecio_por_dia());
        existente.setMarca(vehiculo.getMarca());
        existente.setCategoria(vehiculo.getCategoria());
        return vehiculoRepository.save(existente);
    }

    @Override
    public Vehiculo cambiarEstado(Integer id, Vehiculo.EstadoVehiculo nuevoEstado) {
        Vehiculo existente = buscarPorId(id);
        existente.setEstado(nuevoEstado);
        return vehiculoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        vehiculoRepository.deleteById(id);
    }
}
