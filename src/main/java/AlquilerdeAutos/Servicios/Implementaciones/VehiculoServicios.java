package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Vehiculo;
import AlquilerdeAutos.Repositorios.VehiculoRepository;
import AlquilerdeAutos.Servicios.Interfaces.IVehiculoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServicios implements IVehiculoServicios {

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
    public List<Vehiculo> listarPorEstado(String estado) {
        return vehiculoRepository.findByEstado(estado);
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
        existente.setMarca(vehiculo.getMarca());
        existente.setCategoria(vehiculo.getCategoria());
        existente.setModelo(vehiculo.getModelo());
        existente.setAnio(vehiculo.getAnio());
        existente.setPlaca(vehiculo.getPlaca());
        existente.setColor(vehiculo.getColor());
        existente.setTransmision(vehiculo.getTransmision());
        existente.setCombustible(vehiculo.getCombustible());
        existente.setCapacidad(vehiculo.getCapacidad());
        existente.setPrecioDia(vehiculo.getPrecioDia());
        existente.setImagen(vehiculo.getImagen());
        return vehiculoRepository.save(existente);
    }

    @Override
    public Vehiculo cambiarEstado(Integer id, String nuevoEstado) {
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