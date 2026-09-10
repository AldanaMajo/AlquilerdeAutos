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
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String mensaje,
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
        model.addAttribute("error", error);
        model.addAttribute("mensaje", mensaje);

        return "CategoriaLicencia/Index";
    }

    // GUARDAR
    @PostMapping("/Guardar")
    public String guardar(
            @RequestParam String nombre) {

        String nombreLimpio = nombre.trim();

        // Validar que no exista una categoría con el mismo nombre
        boolean existe = categoriaLicenciaService.listar()
                .stream()
                .anyMatch(categoria ->
                        categoria.getNombre() != null &&
                                categoria.getNombre().trim().equalsIgnoreCase(nombreLimpio)
                );

        if (existe) {
            return "redirect:/CategoriaLicencia/Index?error=duplicado";
        }

        CategoriaLicencia categoria = new CategoriaLicencia();
        categoria.setNombre(nombreLimpio);

        categoriaLicenciaService.guardar(categoria);

        return "redirect:/CategoriaLicencia/Index?mensaje=guardado";
    }

    // EDITAR
    @PostMapping("/Editar")
    public String editar(
            @RequestParam Integer id,
            @RequestParam String nombre) {

        String nombreLimpio = nombre.trim();

        // Validar que no exista otra categoría con el mismo nombre
        boolean existe = categoriaLicenciaService.listar()
                .stream()
                .anyMatch(categoria ->
                        categoria.getId() != id &&
                                categoria.getNombre() != null &&
                                categoria.getNombre().trim().equalsIgnoreCase(nombreLimpio)
                );

        if (existe) {
            return "redirect:/CategoriaLicencia/Index?error=duplicado";
        }

        CategoriaLicencia categoria = new CategoriaLicencia();
        categoria.setNombre(nombreLimpio);

        categoriaLicenciaService.actualizar(id, categoria);

        return "redirect:/CategoriaLicencia/Index?mensaje=actualizado";
    }

    // ELIMINAR
    @GetMapping("/Eliminar/{id}")
    public String eliminar(
            @PathVariable Integer id) {

        categoriaLicenciaService.eliminar(id);

        return "redirect:/CategoriaLicencia/Index?mensaje=eliminado";
    }
}

