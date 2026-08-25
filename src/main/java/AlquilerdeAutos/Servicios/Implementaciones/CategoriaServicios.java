package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Categoria;
import AlquilerdeAutos.Repositorios.CategoriaRepository;
import AlquilerdeAutos.Servicios.Interfaces.IcategoriaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicios implements IcategoriaServicios {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServicios(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + id));
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizar(Integer id, Categoria categoria) {
        Categoria existente = buscarPorId(id);
        existente.setNombre(categoria.getNombre());
        existente.setTarifa_base_diaria(categoria.getTarifa_base_diaria());
        return categoriaRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        categoriaRepository.deleteById(id);
    }
}
