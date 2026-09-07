package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Categoria;
import AlquilerdeAutos.Servicios.Interfaces.IcategoriaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Categoria")
public class CategoriaController {

    private final IcategoriaServicios categoriaService;

    @Autowired
    public CategoriaController(IcategoriaServicios categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/Index")
    public String index(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("nuevaCategoria", new Categoria());
        return "Categoria/Index"; // Ruta a tu archivo HTML dentro de templates
    }

    @PostMapping("/Guardar")
    public String guardar(@ModelAttribute("nuevaCategoria") Categoria categoria,
                          RedirectAttributes redirectAttributes) {
        if (categoriaService.existePorNombre(categoria.getNombre())) {
            redirectAttributes.addFlashAttribute("errorDuplicado",
                    "Ya existe una categoría registrada con el nombre: " + categoria.getNombre());
            return "redirect:/Categoria/Index";
        }
        categoriaService.guardar(categoria);
        redirectAttributes.addFlashAttribute("exito", "Categoría guardada exitosamente.");
        return "redirect:/Categoria/Index";
    }

    @PostMapping("/Editar")
    public String editar(@ModelAttribute Categoria categoria,
                         RedirectAttributes redirectAttributes) {
        Categoria existente = categoriaService.buscarPorId(categoria.getId());

        // Si cambió de nombre y el nuevo nombre ya pertenece a otra categoría
        if (!existente.getNombre().equalsIgnoreCase(categoria.getNombre().trim())
                && categoriaService.existePorNombre(categoria.getNombre())) {
            redirectAttributes.addFlashAttribute("errorDuplicado",
                    "No se puede actualizar: el nombre '" + categoria.getNombre() + "' ya está en uso.");
            return "redirect:/Categoria/Index";
        }

        categoriaService.actualizar(categoria.getId(), categoria);
        redirectAttributes.addFlashAttribute("exito", "Categoría actualizada correctamente.");
        return "redirect:/Categoria/Index";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        categoriaService.eliminar(id);
        redirectAttributes.addFlashAttribute("exito", "Categoría eliminada.");
        return "redirect:/Categoria/Index";
    }
}