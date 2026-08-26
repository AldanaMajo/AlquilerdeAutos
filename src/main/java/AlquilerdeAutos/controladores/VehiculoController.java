package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Vehiculo;
import AlquilerdeAutos.Servicios.Interfaces.IvehiculoServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final IvehiculoServicios vehiculoService;

    @Autowired
    public VehiculoController(IvehiculoServicios vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public ResponseEntity<List<Vehiculo>> listar() {
        return ResponseEntity.ok(vehiculoService.listar());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Vehiculo>> listarDisponibles() {
        return ResponseEntity.ok(vehiculoService.listarDisponibles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(vehiculoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<?> buscarPorPlaca(@PathVariable String placa) {
        try {
            return ResponseEntity.ok(vehiculoService.buscarPorPlaca(placa));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Vehiculo> guardar(@Valid @RequestBody Vehiculo vehiculo) {
        Vehiculo creado = vehiculoService.guardar(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Vehiculo vehiculo) {
        try {
            return ResponseEntity.ok(vehiculoService.actualizar(id, vehiculo));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id, @RequestParam Vehiculo.EstadoVehiculo nuevoEstado) {
        try {
            return ResponseEntity.ok(vehiculoService.cambiarEstado(id, nuevoEstado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
