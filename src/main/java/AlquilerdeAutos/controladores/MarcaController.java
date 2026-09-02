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
        model.addAttribute("nuevaMarca", new Marca());
        return "Marca/Index"; // O la ruta donde guardes tu vista de Marca
    }

    @PostMapping("/Guardar")
    public String guardar(@ModelAttribute("nuevaMarca") Marca marca, RedirectAttributes redirectAttributes) {
        marcaService.guardar(marca);
        redirectAttributes.addFlashAttribute("mensaje", "Marca guardada con éxito");
        return "redirect:/Marca/Index";
    }

    @PostMapping("/Editar")
    public String editar(@ModelAttribute("marca") Marca marca, RedirectAttributes redirectAttributes) {
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