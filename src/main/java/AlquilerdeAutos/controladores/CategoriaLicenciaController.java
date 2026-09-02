package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.CategoriaLicencia;
import AlquilerdeAutos.Servicios.Interfaces.IcategorialicenciaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/CategoriaLicencia")
public class CategoriaLicenciaController {

    private final IcategorialicenciaServicios categoriaLicenciaService;

    @Autowired
    public CategoriaLicenciaController(
            IcategorialicenciaServicios categoriaLicenciaService) {
        this.categoriaLicenciaService = categoriaLicenciaService;
    }

    // MOSTRAR INDEX
    @GetMapping("/Index")
    public String index(
            @RequestParam(required = false) String buscar,
            Model model) {

        List<CategoriaLicencia> categorias =
                categoriaLicenciaService.listar();

        // Filtrar por nombre si se realizó una búsqueda
        if (buscar != null && !buscar.trim().isEmpty()) {
            String texto = buscar.trim().toLowerCase();

            categorias = categorias.stream()
                    .filter(categoria ->
                            categoria.getNombre() != null &&
                                    categoria.getNombre()
                                            .toLowerCase()
                                            .contains(texto))
                    .toList();
        }

        model.addAttribute("categorias", categorias);
        model.addAttribute("buscar", buscar);

        return "CategoriaLicencia/Index";
    }

    // GUARDAR
    @PostMapping("/Guardar")
    public String guardar(
            @RequestParam String nombre) {

        CategoriaLicencia categoria = new CategoriaLicencia();
        categoria.setNombre(nombre);

        categoriaLicenciaService.guardar(categoria);

        return "redirect:/CategoriaLicencia/Index";
    }

    // EDITAR
    @PostMapping("/Editar")
    public String editar(
            @RequestParam Integer id,
            @RequestParam String nombre) {

        CategoriaLicencia categoria = new CategoriaLicencia();
        categoria.setNombre(nombre);

        categoriaLicenciaService.actualizar(id, categoria);

        return "redirect:/CategoriaLicencia/Index";
    }

    // ELIMINAR
    @GetMapping("/Eliminar/{id}")
    public String eliminar(
            @PathVariable Integer id) {

        categoriaLicenciaService.eliminar(id);

        return "redirect:/CategoriaLicencia/Index";
    }
}