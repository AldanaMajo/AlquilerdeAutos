package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Cliente;
import AlquilerdeAutos.Servicios.Interfaces.IcategorialicenciaServicios;
import AlquilerdeAutos.Servicios.Interfaces.IclienteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Cliente")
public class ClienteController {

    private final IclienteServicios clienteService;
    private final IcategorialicenciaServicios categoriaService;

    @Autowired
    public ClienteController(IclienteServicios clienteService, IcategorialicenciaServicios categoriaService) {
        this.clienteService = clienteService;
        this.categoriaService = categoriaService;
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
    public String eliminar(@PathVariable("id") Integer id) {
        clienteService.eliminar(id);
        return "redirect:/Cliente/Index";
    }
}