package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Categoria;
import AlquilerdeAutos.Modelos.Vehiculo;
import AlquilerdeAutos.Servicios.Interfaces.IcategoriaServicios;
import AlquilerdeAutos.Servicios.Interfaces.ImarcaServicios;
import AlquilerdeAutos.Servicios.Interfaces.IvehiculoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Vehiculo")
public class VehiculoWebController {

    private final IvehiculoServicios vehiculoService;
    private final IcategoriaServicios categoriaService;
    private final ImarcaServicios marcaService;

    @Autowired
    public VehiculoWebController(IvehiculoServicios vehiculoService,
                                  IcategoriaServicios categoriaService,
                                  ImarcaServicios marcaService) {
        this.vehiculoService = vehiculoService;
        this.categoriaService = categoriaService;
        this.marcaService = marcaService;
    }

    @GetMapping("/Index")
    public String index(Model model) {
        model.addAttribute("vehiculos", vehiculoService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("marcas", marcaService.listar());
        model.addAttribute("nuevoVehiculo", new Vehiculo());
        return "Vehiculo/Index";
    }

    @PostMapping("/Guardar")
    public String guardar(@ModelAttribute("nuevoVehiculo") Vehiculo vehiculo) {
        // Respaldo en backend: Si no ingresaron precio, asignar la tarifa base de la categoría seleccionada
        if (vehiculo.getPrecio_por_dia() == null && vehiculo.getCategoria() != null && vehiculo.getCategoria().getId() != null) {
            Categoria cat = categoriaService.buscarPorId(vehiculo.getCategoria().getId());
            vehiculo.setPrecio_por_dia(cat.getTarifa_base_diaria());
        }
        vehiculoService.guardar(vehiculo);
        return "redirect:/Vehiculo/Index";
    }

    @PostMapping("/Editar")
    public String editar(@ModelAttribute Vehiculo vehiculo) {
        if (vehiculo.getPrecio_por_dia() == null && vehiculo.getCategoria() != null && vehiculo.getCategoria().getId() != null) {
            Categoria cat = categoriaService.buscarPorId(vehiculo.getCategoria().getId());
            vehiculo.setPrecio_por_dia(cat.getTarifa_base_diaria());
        }
        vehiculoService.actualizar(vehiculo.getId(), vehiculo);
        return "redirect:/Vehiculo/Index";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        vehiculoService.eliminar(id);
        return "redirect:/Vehiculo/Index";
    }
}