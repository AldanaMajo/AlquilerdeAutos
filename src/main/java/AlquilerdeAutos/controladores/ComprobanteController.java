package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Comprobante;
import AlquilerdeAutos.Servicios.Interfaces.IcomprobanteServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteController {

    private final IcomprobanteServicios comprobanteService;

    @Autowired
    public ComprobanteController(IcomprobanteServicios comprobanteService) {
        this.comprobanteService = comprobanteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(comprobanteService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pago/{idPago}")
    public ResponseEntity<?> buscarPorPago(@PathVariable Integer idPago) {
        try {
            return ResponseEntity.ok(comprobanteService.buscarPorPago(idPago));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Comprobante> guardar(@Valid @RequestBody Comprobante comprobante) {
        Comprobante creado = comprobanteService.guardar(comprobante);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Comprobante comprobante) {
        try {
            return ResponseEntity.ok(comprobanteService.actualizar(id, comprobante));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        comprobanteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
