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

    // MOSTRAR INDEX
    @GetMapping("/Index")
    public String index(Model model) {

        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("nuevaCategoria", new Categoria());

        return "Categoria/Index";
    }

    // GUARDAR
    @PostMapping("/Guardar")
    public String guardar(
            @ModelAttribute("nuevaCategoria") Categoria categoria,
            RedirectAttributes redirectAttributes) {

        // Validar nombre
        if (categoria.getNombre() == null ||
                categoria.getNombre().trim().isEmpty()) {

            redirectAttributes.addFlashAttribute(
                    "errorDuplicado",
                    "El nombre de la categoría es obligatorio."
            );

            return "redirect:/Categoria/Index";
        }

        // Limpiar espacios
        String nombre = categoria.getNombre().trim();

        categoria.setNombre(nombre);

        // Verificar duplicado
        if (categoriaService.existePorNombre(nombre)) {

            redirectAttributes.addFlashAttribute(
                    "errorDuplicado",
                    "Ya existe una categoría registrada con el nombre: " + nombre
            );

            return "redirect:/Categoria/Index";
        }

        // Guardar
        categoriaService.guardar(categoria);

        redirectAttributes.addFlashAttribute(
                "exito",
                "Categoría guardada exitosamente."
        );

        return "redirect:/Categoria/Index";
    }

    // EDITAR
    @PostMapping("/Editar")
    public String editar(
            @ModelAttribute Categoria categoria,
            RedirectAttributes redirectAttributes) {

        if (categoria.getId() == null) {

            redirectAttributes.addFlashAttribute(
                    "errorDuplicado",
                    "No se recibió el ID de la categoría."
            );

            return "redirect:/Categoria/Index";
        }

        Categoria existente =
                categoriaService.buscarPorId(categoria.getId());

        if (categoria.getNombre() == null ||
                categoria.getNombre().trim().isEmpty()) {

            redirectAttributes.addFlashAttribute(
                    "errorDuplicado",
                    "El nombre de la categoría es obligatorio."
            );

            return "redirect:/Categoria/Index";
        }

        String nuevoNombre = categoria.getNombre().trim();

        boolean cambioNombre =
                !existente.getNombre()
                        .trim()
                        .equalsIgnoreCase(nuevoNombre);

        if (cambioNombre &&
                categoriaService.existePorNombre(nuevoNombre)) {

            redirectAttributes.addFlashAttribute(
                    "errorDuplicado",
                    "No se puede actualizar: el nombre '"
                            + nuevoNombre
                            + "' ya está en uso."
            );

            return "redirect:/Categoria/Index";
        }

        categoria.setNombre(nuevoNombre);

        categoriaService.actualizar(
                categoria.getId(),
                categoria
        );

        redirectAttributes.addFlashAttribute(
                "exito",
                "Categoría actualizada correctamente."
        );

        return "redirect:/Categoria/Index";
    }

    // ELIMINAR
    @GetMapping("/Eliminar/{id}")
    public String eliminar(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        categoriaService.eliminar(id);

        redirectAttributes.addFlashAttribute(
                "exito",
                "Categoría eliminada."
        );

        return "redirect:/Categoria/Index";
    }
}
