package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Reserva;
import AlquilerdeAutos.Servicios.Interfaces.IreservaServicios;
import AlquilerdeAutos.Servicios.Interfaces.IclienteServicios;
import AlquilerdeAutos.Servicios.Interfaces.IvehiculoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
            // Edición: mantiene su código de reserva original
            reservaService.actualizar(reserva.getId(), reserva);
        } else {
            // Creación: Genera el código automáticamente (Ejemplo: RES-8A2F1C)
            if (reserva.getCodigoReserva() == null || reserva.getCodigoReserva().isBlank()) {
                String codigoGenerado = "RES-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
                reserva.setCodigoReserva(codigoGenerado);
            }
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