package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Marca;
import AlquilerdeAutos.Servicios.Interfaces.ImarcaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Marca")
public class MarcaController {

    private final ImarcaServicios marcaService;

    @Autowired
    public MarcaController(ImarcaServicios marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping({"", "/", "/Index"})
    public String index(@RequestParam(name = "buscar", required = false) String buscar, Model model) {
        List<Marca> marcas = marcaService.listar();

        if (buscar != null && !buscar.trim().isEmpty()) {
            marcas = marcas.stream()
                    .filter(m -> m.getNombre().toLowerCase().contains(buscar.toLowerCase()))
                    .collect(Collectors.toList());
            model.addAttribute("buscar", buscar);
        }

        model.addAttribute("marcas", marcas);

        // Evita sobreescribir nuevaMarca si viene devuelto con errores
        if (!model.containsAttribute("nuevaMarca")) {
            model.addAttribute("nuevaMarca", new Marca());
        }

        return "Marca/Index";
    }

    // CORREGIDO: Se cambia de "/Marca/Guardar" a "/Guardar" (la URL final sigue siendo /Marca/Guardar por el @RequestMapping)
    @PostMapping("/Guardar")
    public String guardar(@ModelAttribute("nuevaMarca") Marca marca, Model model) {

        // Validar si el nombre ingresado ya existe
        if (marcaService.existePorNombre(marca.getNombre().trim())) {

            // Volvemos a cargar la lista de marcas para que la tabla de la vista no quede vacía
            model.addAttribute("marcas", marcaService.listar());
            model.addAttribute("nuevaMarca", marca);

            // Mensaje de error para el Modal de Agregar
            model.addAttribute("errorDuplicadoAgregar", "La marca '" + marca.getNombre() + "' ya existe.");

            return "Marca/Index";
        }

        // Si no está duplicada, se guarda
        marcaService.guardar(marca);
        return "redirect:/Marca/Index";
    }

    @PostMapping("/Editar")
    public String editar(@ModelAttribute("marca") Marca marca, Model model, RedirectAttributes redirectAttributes) {

        Marca existente = marcaService.buscarPorId(marca.getId());

        // Validar si cambió el nombre y si el nuevo nombre ya pertenece a otra marca registrada
        if (!existente.getNombre().equalsIgnoreCase(marca.getNombre().trim()) &&
                marcaService.existePorNombre(marca.getNombre().trim())) {

            model.addAttribute("marcas", marcaService.listar());
            model.addAttribute("nuevaMarca", new Marca());
            model.addAttribute("marcaEditarId", marca.getId());
            model.addAttribute("marcaEditarNombre", marca.getNombre());

            // Mensaje de error para el Modal de Editar
            model.addAttribute("errorDuplicadoEditar", "Ya existe otra marca con el nombre '" + marca.getNombre() + "'.");

            return "Marca/Index";
        }

        marcaService.actualizar(marca.getId(), marca);
        redirectAttributes.addFlashAttribute("mensaje", "Marca actualizada con éxito");
        return "redirect:/Marca/Index";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        marcaService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Marca eliminada con éxito");
        return "redirect:/Marca/Index";
    }
}