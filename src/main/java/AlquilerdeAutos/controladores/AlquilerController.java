package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Alquiler;
import AlquilerdeAutos.Servicios.Interfaces.IalquilerServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    private final IalquilerServicios alquilerService;

    @Autowired
    public AlquilerController(IalquilerServicios alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping
    public ResponseEntity<List<Alquiler>> listar() {
        return ResponseEntity.ok(alquilerService.listar());
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Alquiler>> listarPorCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(alquilerService.listarPorCliente(idCliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(alquilerService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Alquiler> guardar(@Valid @RequestBody Alquiler alquiler) {
        Alquiler creado = alquilerService.guardar(alquiler);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Alquiler alquiler) {
        try {
            return ResponseEntity.ok(alquilerService.actualizar(id, alquiler));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizar(@PathVariable Integer id, @RequestParam Integer kilometrajeFinal) {
        try {
            return ResponseEntity.ok(alquilerService.finalizar(id, kilometrajeFinal));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(alquilerService.cancelar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        alquilerService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
