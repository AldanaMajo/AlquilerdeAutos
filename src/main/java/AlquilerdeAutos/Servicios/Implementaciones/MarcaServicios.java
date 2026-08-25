package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Marca;
import AlquilerdeAutos.Repositorios.MarcaRepository;
import AlquilerdeAutos.Servicios.Interfaces.ImarcaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaServicios implements ImarcaServicios {

    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaServicios(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Override
    public List<Marca> listar() {
        return marcaRepository.findAll();
    }

    @Override
    public Marca buscarPorId(Integer id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con id: " + id));
    }

    @Override
    public Marca guardar(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Override
    public Marca actualizar(Integer id, Marca marca) {
        Marca existente = buscarPorId(id);
        existente.setNombre(marca.getNombre());
        return marcaRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        marcaRepository.deleteById(id);
    }
}
