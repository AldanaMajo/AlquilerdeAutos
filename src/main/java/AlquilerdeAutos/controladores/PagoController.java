package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Pago;
import AlquilerdeAutos.Servicios.Interfaces.IalquilerServicios;
import AlquilerdeAutos.Servicios.Interfaces.IpagoServicios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Pago")
public class PagoController {

    private final IpagoServicios pagoService;
    private final IalquilerServicios alquilerService;

    @Autowired
    public PagoController(IpagoServicios pagoService, IalquilerServicios alquilerService) {
        this.pagoService = pagoService;
        this.alquilerService = alquilerService;
    }

    @GetMapping("/Index")
    public String index(Model model) {
        model.addAttribute("pagos", pagoService.listar());
        model.addAttribute("nuevoPago", new Pago());
        model.addAttribute("metodosPago", Pago.MetodoPago.values());
        model.addAttribute("alquileres", alquilerService.listar());
        return "Pago/Index";
    }

    @PostMapping("/Guardar")
    public String guardar(@Valid @ModelAttribute("nuevoPago") Pago pago, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pagos", pagoService.listar());
            model.addAttribute("metodosPago", Pago.MetodoPago.values());
            model.addAttribute("alquileres", alquilerService.listar());
            return "Pago/Index";
        }
        pagoService.guardar(pago);
        return "redirect:/Pago/Index";
    }

    @PostMapping("/Editar")
    public String actualizar(@Valid @ModelAttribute Pago pago) {
        pagoService.actualizar(pago.getId(), pago);
        return "redirect:/Pago/Index";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        pagoService.eliminar(id);
        return "redirect:/Pago/Index";
    }
}