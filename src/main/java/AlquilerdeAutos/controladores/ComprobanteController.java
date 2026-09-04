package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Comprobante;
import AlquilerdeAutos.Servicios.Interfaces.IcomprobanteServicios;
import AlquilerdeAutos.Servicios.Interfaces.IpagoServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Comprobante")
public class ComprobanteController {

    private final IcomprobanteServicios comprobanteService;
    private final IpagoServicios pagoService;

    @Autowired
    public ComprobanteController(IcomprobanteServicios comprobanteService, IpagoServicios pagoService) {
        this.comprobanteService = comprobanteService;
        this.pagoService = pagoService;
    }

    @GetMapping("/Index")
    public String index(@RequestParam(name = "buscar", required = false) String buscar, Model model) {
        model.addAttribute("comprobantes", comprobanteService.obtenerTodos(buscar));
        model.addAttribute("nuevoComprobante", new Comprobante());
        model.addAttribute("tiposComprobante", Comprobante.TipoComprobante.values());
        model.addAttribute("pagos", pagoService.listar()); // Carga la lista de pagos usando tu método listar()
        model.addAttribute("buscar", buscar);
        return "Comprobante/Index";
    }

    @PostMapping("/Guardar")
    public String guardar(@Valid @ModelAttribute("nuevoComprobante") Comprobante comprobante,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("comprobantes", comprobanteService.obtenerTodos(null));
            model.addAttribute("tiposComprobante", Comprobante.TipoComprobante.values());
            model.addAttribute("pagos", pagoService.listar());
            return "Comprobante/Index";
        }
        comprobanteService.guardar(comprobante);
        return "redirect:/Comprobante/Index";
    }

    @PostMapping("/Editar")
    public String actualizar(@Valid @ModelAttribute Comprobante comprobante) {
        comprobanteService.actualizar(comprobante.getId(), comprobante);
        return "redirect:/Comprobante/Index";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        comprobanteService.eliminar(id);
        return "redirect:/Comprobante/Index";
    }
}