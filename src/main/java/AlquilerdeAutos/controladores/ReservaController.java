package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Reserva;
import AlquilerdeAutos.Modelos.Cliente;
import AlquilerdeAutos.Modelos.Vehiculo;
import AlquilerdeAutos.Servicios.Interfaces.IreservaServicios;
import AlquilerdeAutos.Servicios.Interfaces.IclienteServicios;
import AlquilerdeAutos.Servicios.Interfaces.IvehiculoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Reserva")
public class ReservaController {

    private final IreservaServicios reservaService;
    private final IclienteServicios clienteService;
    private final IvehiculoServicios vehiculoService;

    @Autowired
    public ReservaController(
            IreservaServicios reservaService,
            IclienteServicios clienteService,
            IvehiculoServicios vehiculoService) {

        this.reservaService = reservaService;
        this.clienteService = clienteService;
        this.vehiculoService = vehiculoService;
    }

    @GetMapping("/Index")
    public String index(Model model) {
        model.addAttribute("reservas", reservaService.listar());
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("vehiculos", vehiculoService.listar());
        model.addAttribute("nuevaReserva", new Reserva());

        return "Reserva/Index";
    }

    @PostMapping("/Guardar")
    public String guardar(@ModelAttribute Reserva reserva) {
        if (reserva.getId() != null) {
            reservaService.actualizar(reserva.getId(), reserva);
        } else {
            reservaService.guardar(reserva);
        }

        return "redirect:/Reserva/Index";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        reservaService.eliminar(id);
        return "redirect:/Reserva/Index";
    }
}

