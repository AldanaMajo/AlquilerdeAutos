package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Marca;
import AlquilerdeAutos.Servicios.Interfaces.ImarcaServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    private final ImarcaServicios marcaService;

    @Autowired
    public MarcaController(ImarcaServicios marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<Marca>> listar() {
        return ResponseEntity.ok(marcaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(marcaService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Marca> guardar(@Valid @RequestBody Marca marca) {
        Marca creada = marcaService.guardar(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Marca marca) {
        try {
            return ResponseEntity.ok(marcaService.actualizar(id, marca));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        marcaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
