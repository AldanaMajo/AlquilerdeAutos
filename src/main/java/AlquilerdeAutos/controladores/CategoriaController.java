package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Categoria;
import AlquilerdeAutos.Servicios.Interfaces.IcategoriaServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final IcategoriaServicios categoriaService;

    @Autowired
    public CategoriaController(IcategoriaServicios categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(categoriaService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> guardar(@Valid @RequestBody Categoria categoria) {
        Categoria creada = categoriaService.guardar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok(categoriaService.actualizar(id, categoria));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
