package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Rol;
import AlquilerdeAutos.Servicios.Interfaces.IrolServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    // VISTA PRINCIPAL
    @GetMapping("/Index")
    public String index(@RequestParam(name = "buscar", required = false) String buscar, Model model) {
        List<Rol> roles;

        if (buscar != null && !buscar.trim().isEmpty()) {
            roles = rolService.buscarPorNombre(buscar);
        } else {
            roles = rolService.listar();
        }

        model.addAttribute("roles", roles);
        model.addAttribute("buscar", buscar);
        model.addAttribute("nuevoRol", new Rol());

        return "Rol/Index";
    }

    // PROCESAR CREACIÓN
    @PostMapping("/Guardar")
    public String guardarFormulario(@Valid @ModelAttribute("nuevoRol") Rol rol, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", rolService.listar());
            return "Rol/Index";
        }
        rolService.guardar(rol);
        return "redirect:/Rol/Index";
    }

    // PROCESAR EDICIÓN DESDE LA VISTA
    @PostMapping("/Editar")
    public String editarFormulario(@ModelAttribute("rol") Rol rol) {
        rolService.actualizar(rol.getId(), rol);
        return "redirect:/Rol/Index";
    }

    // PROCESAR ELIMINACIÓN DESDE LA VISTA
    @GetMapping("/Eliminar/{id}")
    public String eliminarFormulario(@PathVariable Integer id) {
        rolService.eliminar(id);
        return "redirect:/Rol/Index";
    }

    // ==========================================
    // ENDPOINTS API REST (Para consumo JSON/AJAX)
    // ==========================================

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