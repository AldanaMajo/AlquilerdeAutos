package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Rol;
import AlquilerdeAutos.Repositorios.RolRepository;
import AlquilerdeAutos.Servicios.Interfaces.IrolServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServicios implements IrolServicios {

    private final RolRepository rolRepository;

    @Autowired
    public RolServicios(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    @Override
    public Rol buscarPorId(Integer id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
    }

    @Override
    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Rol actualizar(Integer id, Rol rol) {
        Rol existente = buscarPorId(id);
        existente.setNombre(rol.getNombre());
        return rolRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        rolRepository.deleteById(id);
    }
}