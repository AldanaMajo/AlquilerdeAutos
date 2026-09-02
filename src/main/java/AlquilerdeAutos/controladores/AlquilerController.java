package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Alquiler;
import AlquilerdeAutos.Servicios.Interfaces.IalquilerServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Alquiler")
public class AlquilerController {

    private final IalquilerServicios alquilerService;

    @Autowired
    public AlquilerController(IalquilerServicios alquilerService) {
        this.alquilerService = alquilerService;
    }

    // MOSTRAR INDEX
    @GetMapping("/Index")
    public String index(
            @RequestParam(required = false) String buscar,
            Model model) {

        List<Alquiler> alquileres = alquilerService.listar();

        // BUSCADOR
        if (buscar != null && !buscar.trim().isEmpty()) {

            String texto = buscar.trim().toLowerCase();

            alquileres = alquileres.stream()
                    .filter(alquiler ->
                            String.valueOf(alquiler.getId())
                                    .contains(texto)
                    )
                    .toList();
        }

        model.addAttribute("alquileres", alquileres);
        model.addAttribute("buscar", buscar);

        return "Alquiler/Index";
    }

    // GUARDAR
    @PostMapping("/Guardar")
    public String guardar(@ModelAttribute Alquiler alquiler) {

        alquilerService.guardar(alquiler);

        return "redirect:/Alquiler/Index";
    }

    // EDITAR
    @PostMapping("/Editar")
    public String editar(
            @RequestParam Integer id,
            @ModelAttribute Alquiler alquiler) {

        alquilerService.actualizar(id, alquiler);

        return "redirect:/Alquiler/Index";
    }

    // FINALIZAR
    @PostMapping("/Finalizar")
    public String finalizar(
            @RequestParam Integer id,
            @RequestParam Integer kilometrajeFinal) {

        alquilerService.finalizar(id, kilometrajeFinal);

        return "redirect:/Alquiler/Index";
    }

    // CANCELAR
    @GetMapping("/Cancelar/{id}")
    public String cancelar(@PathVariable Integer id) {

        alquilerService.cancelar(id);

        return "redirect:/Alquiler/Index";
    }

    // ELIMINAR
    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {

        alquilerService.eliminar(id);

        return "redirect:/Alquiler/Index";
    }
}

