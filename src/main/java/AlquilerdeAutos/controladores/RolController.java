package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Rol;
import AlquilerdeAutos.Servicios.Interfaces.IrolServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; // <-- CAMBIAR IMPORT DE RESTCONTROLLER A CONTROLLER
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Rol")
public class RolController {

    private final IrolServicios rolService;

    @Autowired
    public RolController(IrolServicios rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/Index")
    public String index() {
        return "Rol/Index";
    }


    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Rol>> listar() {
        return ResponseEntity.ok(rolService.listar());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(rolService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Rol> guardar(@Valid @RequestBody Rol rol) {
        Rol creado = rolService.guardar(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Rol rol) {
        try {
            return ResponseEntity.ok(rolService.actualizar(id, rol));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rolService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}