package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Vehiculo;
import AlquilerdeAutos.Servicios.Interfaces.IvehiculoServicios;
import AlquilerdeAutos.Servicios.Interfaces.ImarcaServicios; // Ajusta según tu interfaz de Marca
import AlquilerdeAutos.Servicios.Interfaces.IcategoriaServicios; // Ajusta según tu interfaz de Categoría
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Vehiculo")
public class VehiculoWebController {

    private final IvehiculoServicios vehiculoService;
    private final ImarcaServicios marcaService;
    private final IcategoriaServicios categoriaService;

    @Autowired
    public VehiculoWebController(IvehiculoServicios vehiculoService,
                                 ImarcaServicios marcaService,
                                 IcategoriaServicios categoriaService) {
        this.vehiculoService = vehiculoService;
        this.marcaService = marcaService;
        this.categoriaService = categoriaService;
    }

    // URL: http://localhost:8080/Vehiculo/Index
    @GetMapping("/Index")
    public String index(@RequestParam(name = "buscar", required = false) String buscar, Model model) {

        if (buscar != null && !buscar.isBlank()) {
            model.addAttribute("vehiculos", vehiculoService.buscarPorPlaca(buscar)); // o tu método de búsqueda
        } else {
            model.addAttribute("vehiculos", vehiculoService.listar());
        }

        // Carga los objetos requeridos por los modales de Bootstrap
        model.addAttribute("nuevoVehiculo", new Vehiculo());
        model.addAttribute("marcas", marcaService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("buscar", buscar);

        // Retorna la vista HTML ubicada en: src/main/resources/templates/Vehiculo/Index.html
        return "Vehiculo/Index";
    }

    @PostMapping("/Guardar")
    public String guardar(@ModelAttribute("nuevoVehiculo") Vehiculo vehiculo) {
        vehiculoService.guardar(vehiculo);
        return "redirect:/Vehiculo/Index";
    }

    @PostMapping("/Editar")
    public String editar(@ModelAttribute Vehiculo vehiculo) {
        vehiculoService.actualizar(vehiculo.getId(), vehiculo);
        return "redirect:/Vehiculo/Index";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        vehiculoService.eliminar(id);
        return "redirect:/Vehiculo/Index";
    }
}