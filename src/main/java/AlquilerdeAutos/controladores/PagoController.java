package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Pago;
import AlquilerdeAutos.Servicios.Interfaces.IpagoServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final IpagoServicios pagoService;

    @Autowired
    public PagoController(IpagoServicios pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listar() {
        return ResponseEntity.ok(pagoService.listar());
    }

    @GetMapping("/alquiler/{idAlquiler}")
    public ResponseEntity<List<Pago>> listarPorAlquiler(@PathVariable Integer idAlquiler) {
        return ResponseEntity.ok(pagoService.listarPorAlquiler(idAlquiler));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(pagoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pago> guardar(@Valid @RequestBody Pago pago) {
        Pago creado = pagoService.guardar(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Pago pago) {
        try {
            return ResponseEntity.ok(pagoService.actualizar(id, pago));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
