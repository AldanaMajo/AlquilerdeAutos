package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Cliente;
import AlquilerdeAutos.Servicios.Interfaces.IclienteServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final IclienteServicios clienteService;

    @Autowired
    public ClienteController(IclienteServicios clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(clienteService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<?> buscarPorDocumento(@PathVariable String documento) {
        try {
            return ResponseEntity.ok(clienteService.buscarPorDocumento(documento));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Cliente cliente) {
        try {
            Cliente creado = clienteService.guardar(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(clienteService.actualizar(id, cliente));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
