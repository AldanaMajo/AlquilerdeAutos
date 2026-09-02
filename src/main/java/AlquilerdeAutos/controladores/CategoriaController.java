package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Categoria;
import AlquilerdeAutos.Servicios.Interfaces.IcategoriaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String guardar(@ModelAttribute("nuevaCategoria") Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/Categoria/Index";
    }

    @PostMapping("/Editar")
    public String editar(@ModelAttribute Categoria categoria) {
        categoriaService.actualizar(categoria.getId(), categoria);
        return "redirect:/Categoria/Index";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
        return "redirect:/Categoria/Index";
    }
}