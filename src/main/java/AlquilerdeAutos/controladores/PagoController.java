package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Alquiler;
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
    public PagoController(
            IpagoServicios pagoService,
            IalquilerServicios alquilerService) {

        this.pagoService = pagoService;
        this.alquilerService = alquilerService;
    }

    // =========================================================
    // LISTAR PAGOS
    // =========================================================
    @GetMapping("/Index")
    public String index(Model model) {

        Pago nuevoPago = new Pago();

        // IMPORTANTE:
        // Creamos el objeto Alquiler para que
        // th:field="*{alquiler.id}" pueda funcionar.
        nuevoPago.setAlquiler(new Alquiler());

        model.addAttribute("pagos", pagoService.listar());
        model.addAttribute("nuevoPago", nuevoPago);
        model.addAttribute("metodosPago", Pago.MetodoPago.values());
        model.addAttribute("alquileres", alquilerService.listar());

        return "Pago/Index";
    }

    // =========================================================
    // GUARDAR PAGO
    // =========================================================
    @PostMapping("/Guardar")
    public String guardar(
            @Valid @ModelAttribute("nuevoPago") Pago pago,
            BindingResult result,
            Model model) {

        System.out.println("======================================");
        System.out.println("       INTENTANDO GUARDAR PAGO");
        System.out.println("======================================");

        System.out.println("Monto: " + pago.getMonto());
        System.out.println("Método: " + pago.getMetodoPago());

        Integer idAlquiler = null;

        if (pago.getAlquiler() != null) {
            idAlquiler = pago.getAlquiler().getId();
        }

        System.out.println("ID Alquiler recibido: " + idAlquiler);

        // =====================================================
        // BUSCAR EL ALQUILER REAL
        // =====================================================
        if (idAlquiler == null) {

            result.rejectValue(
                    "alquiler",
                    "error.pago",
                    "Debe seleccionar un alquiler."
            );

            System.out.println("ERROR: No se recibió el ID del alquiler.");

        } else {

            try {

                Alquiler alquiler = alquilerService.buscarPorId(idAlquiler);

                pago.setAlquiler(alquiler);

                System.out.println(
                        "Alquiler encontrado correctamente: "
                                + alquiler.getId()
                );

            } catch (Exception e) {

                result.rejectValue(
                        "alquiler",
                        "error.pago",
                        "El alquiler seleccionado no existe."
                );

                System.out.println(
                        "ERROR buscando alquiler: "
                                + e.getMessage()
                );
            }
        }

        // =====================================================
        // MOSTRAR ERRORES DE VALIDACIÓN
        // =====================================================
        if (result.hasErrors()) {

            System.out.println("======================================");
            System.out.println("      ERRORES AL GUARDAR EL PAGO");
            System.out.println("======================================");

            result.getFieldErrors().forEach(error -> {

                System.out.println(
                        "Campo: "
                                + error.getField()
                                + " | Error: "
                                + error.getDefaultMessage()
                );

            });

            model.addAttribute("pagos", pagoService.listar());
            model.addAttribute("nuevoPago", pago);
            model.addAttribute(
                    "metodosPago",
                    Pago.MetodoPago.values()
            );
            model.addAttribute(
                    "alquileres",
                    alquilerService.listar()
            );

            return "Pago/Index";
        }

        // =====================================================
        // GUARDAR EN BASE DE DATOS
        // =====================================================
        try {

            Pago pagoGuardado = pagoService.guardar(pago);

            System.out.println("======================================");
            System.out.println("       PAGO GUARDADO CORRECTAMENTE");
            System.out.println("======================================");

            System.out.println(
                    "ID del pago: "
                            + pagoGuardado.getId()
            );

            System.out.println(
                    "Monto: "
                            + pagoGuardado.getMonto()
            );

            System.out.println(
                    "Alquiler: "
                            + pagoGuardado.getAlquiler().getId()
            );

        } catch (Exception e) {

            System.out.println("======================================");
            System.out.println("          ERROR AL GUARDAR");
            System.out.println("======================================");

            e.printStackTrace();

            model.addAttribute("pagos", pagoService.listar());
            model.addAttribute("nuevoPago", pago);
            model.addAttribute(
                    "metodosPago",
                    Pago.MetodoPago.values()
            );
            model.addAttribute(
                    "alquileres",
                    alquilerService.listar()
            );

            return "Pago/Index";
        }

        return "redirect:/Pago/Index";
    }

    // =========================================================
    // EDITAR PAGO
    // =========================================================
    @PostMapping("/Editar")
    public String actualizar(
            @Valid @ModelAttribute Pago pago,
            BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/Pago/Index";
        }

        if (pago.getAlquiler() != null
                && pago.getAlquiler().getId() != null) {

            Alquiler alquiler =
                    alquilerService.buscarPorId(
                            pago.getAlquiler().getId()
                    );

            pago.setAlquiler(alquiler);
        }

        pagoService.actualizar(
                pago.getId(),
                pago
        );

        return "redirect:/Pago/Index";
    }

    // =========================================================
    // ELIMINAR PAGO
    // =========================================================
    @GetMapping("/Eliminar/{id}")
    public String eliminar(
            @PathVariable Integer id) {

        pagoService.eliminar(id);

        return "redirect:/Pago/Index";
    }
}