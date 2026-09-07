package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Cliente;
import AlquilerdeAutos.Repositorios.AlquilerRepository;
import AlquilerdeAutos.Servicios.Interfaces.IcategorialicenciaServicios;
import AlquilerdeAutos.Servicios.Interfaces.IclienteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Cliente")
public class ClienteController {

    private final IclienteServicios clienteService;
    private final IcategorialicenciaServicios categoriaService;
    private final AlquilerRepository alquilerRepository;

    @Autowired
    public ClienteController(IclienteServicios clienteService,
                             IcategorialicenciaServicios categoriaService,
                             AlquilerRepository alquilerRepository) {
        this.clienteService = clienteService;
        this.categoriaService = categoriaService;
        this.alquilerRepository = alquilerRepository;
    }

    @GetMapping("/Index")
    public String index(@RequestParam(name = "buscar", required = false) String buscar, Model model) {
        model.addAttribute("clientes", clienteService.buscarPorFiltro(buscar));
        model.addAttribute("buscar", buscar);
        model.addAttribute("nuevoCliente", new Cliente());
        model.addAttribute("categorias", categoriaService.listar());

        return "Cliente/Index";
    }

    @PostMapping("/Guardar")
    public String guardar(@ModelAttribute("nuevoCliente") Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/Cliente/Index";
    }

    @PostMapping("/Editar")
    public String editar(@ModelAttribute Cliente cliente) {
        clienteService.actualizar(cliente.getId(), cliente);
        return "redirect:/Cliente/Index";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            boolean tieneAlquileres = alquilerRepository.existsByClienteId(id);

            if (tieneAlquileres) {
                redirectAttributes.addFlashAttribute("error",
                        "No se puede eliminar este cliente porque tiene alquileres registrados.");
                return "redirect:/Cliente/Index";
            }

            clienteService.eliminar(id);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Ocurrió un error al eliminar el cliente.");
        }

        return "redirect:/Cliente/Index";
    }
}