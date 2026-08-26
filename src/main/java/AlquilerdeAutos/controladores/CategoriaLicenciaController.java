package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.CategoriaLicencia;
import AlquilerdeAutos.Servicios.Interfaces.IcategorialicenciaServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias-licencia")
public class CategoriaLicenciaController {

    private final IcategorialicenciaServicios categoriaLicenciaService;

    @Autowired
    public CategoriaLicenciaController(IcategorialicenciaServicios categorialicenciaService) {
        this.categoriaLicenciaService = categorialicenciaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaLicencia>> listar() {
        return ResponseEntity.ok(categoriaLicenciaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(categoriaLicenciaService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CategoriaLicencia> guardar(@Valid @RequestBody CategoriaLicencia categoriaLicencia) {
        CategoriaLicencia creada = categoriaLicenciaService.guardar(categoriaLicencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody CategoriaLicencia categoriaLicencia) {
        try {
            return ResponseEntity.ok(categoriaLicenciaService.actualizar(id, categoriaLicencia));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        categoriaLicenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
