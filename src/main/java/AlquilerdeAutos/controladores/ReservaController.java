package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Reserva;
import AlquilerdeAutos.Servicios.Interfaces.IreservaServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final IreservaServicios reservaService;

    @Autowired
    public ReservaController(IreservaServicios reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listar() {
        return ResponseEntity.ok(reservaService.listar());
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Reserva>> listarPorCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(reservaService.listarPorCliente(idCliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(reservaService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable String codigo) {
        try {
            return ResponseEntity.ok(reservaService.buscarPorCodigo(codigo));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Reserva> guardar(@Valid @RequestBody Reserva reserva) {
        Reserva creada = reservaService.guardar(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Reserva reserva) {
        try {
            return ResponseEntity.ok(reservaService.actualizar(id, reserva));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<?> confirmar(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(reservaService.confirmar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(reservaService.cancelar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
