package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Alquiler;
import AlquilerdeAutos.Servicios.Interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Alquiler")
public class AlquilerController {

    private final IalquilerServicios alquilerService;
    private final IclienteServicios clienteService;
    private final IvehiculoServicios vehiculoService;
    private final IusuarioServicios usuarioService;
    private final IreservaServicios reservaService;

    @Autowired
    public AlquilerController(IalquilerServicios alquilerService,
                              IclienteServicios clienteService,
                              IvehiculoServicios vehiculoService,
                              IusuarioServicios usuarioService,
                              IreservaServicios reservaService) {
        this.alquilerService = alquilerService;
        this.clienteService = clienteService;
        this.vehiculoService = vehiculoService;
        this.usuarioService = usuarioService;
        this.reservaService = reservaService;
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
                            String.valueOf(alquiler.getId()).contains(texto) ||
                                    (alquiler.getCliente() != null && alquiler.getCliente().getNombre().toLowerCase().contains(texto)) ||
                                    (alquiler.getVehiculo() != null && alquiler.getVehiculo().getPlaca().toLowerCase().contains(texto))
                    )
                    .toList();
        }

        model.addAttribute("alquileres", alquileres);
        model.addAttribute("nuevoAlquiler", new Alquiler());
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("vehiculos", vehiculoService.listar());
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("reservas", reservaService.listar());
        model.addAttribute("estados", Alquiler.EstadoAlquiler.values());
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