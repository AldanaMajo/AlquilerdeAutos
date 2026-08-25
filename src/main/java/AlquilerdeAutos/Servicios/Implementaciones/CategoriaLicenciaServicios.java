package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.CategoriaLicencia;
import AlquilerdeAutos.Repositorios.CategoriaLicenciaRepository;
import AlquilerdeAutos.Servicios.Interfaces.IcategoriaLicenciaServicios;
import AlquilerdeAutos.Servicios.Interfaces.IcategorialicenciaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaLicenciaServicios implements IcategoriaLicenciaServicios {

    private final CategoriaLicenciaRepository categoriaLicenciaRepository;

    @Autowired
    public CategoriaLicenciaServicios(CategoriaLicenciaRepository categoriaLicenciaRepository) {
        this.categoriaLicenciaRepository = categoriaLicenciaRepository;
    }

    @Override
    public List<CategoriaLicencia> listar() {
        return categoriaLicenciaRepository.findAll();
    }

    @Override
    public CategoriaLicencia buscarPorId(Integer id) {
        return categoriaLicenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria de licencia no encontrada con id: " + id));
    }

    @Override
    public CategoriaLicencia guardar(CategoriaLicencia categoriaLicencia) {
        return categoriaLicenciaRepository.save(categoriaLicencia);
    }

    @Override
    public CategoriaLicencia actualizar(Integer id, CategoriaLicencia categoriaLicencia) {
        CategoriaLicencia existente = buscarPorId(id);
        existente.setNombre(categoriaLicencia.getNombre());
        return categoriaLicenciaRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        categoriaLicenciaRepository.deleteById(id);
    }
}
