package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Usuario;
import AlquilerdeAutos.Servicios.Interfaces.IrolServicios; // Asegúrate de tener la interfaz de Roles
import AlquilerdeAutos.Servicios.Interfaces.IusuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Usuario")
public class UsuarioController {

    private final IusuarioServicios usuarioService;
    private final IrolServicios rolService; // Para cargar el selector de roles en los modales

    @Autowired
    public UsuarioController(IusuarioServicios usuarioService, IrolServicios rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }


    @GetMapping("/Index")
    public String index(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("roles", rolService.listar());
        model.addAttribute("nuevoUsuario", new Usuario());
        return "Usuario/Index"; // Ajusta la ruta a tu carpeta de vistas
    }

    /*@PostMapping("/Guardar")
    public String guardar(@ModelAttribute("nuevoUsuario") Usuario usuario, RedirectAttributes redirect) {
        try {
            usuarioService.guardar(usuario);
            redirect.addFlashAttribute("success", "Usuario creado con éxito");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/Usuario/Index";
    }*/
    @PostMapping("/Guardar")
    public String guardar(@ModelAttribute("nuevoUsuario") Usuario usuario, RedirectAttributes redirect) {
        try {
            usuarioService.guardar(usuario);
            redirect.addFlashAttribute("success", "Usuario creado con éxito");
        } catch (Exception e) {
            // Imprime el error en la consola de tu IDE (IntelliJ / Eclipse / VS Code)
            e.printStackTrace();
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/Usuario/Index";
    }

    @PostMapping("/Editar")
    public String editar(@ModelAttribute Usuario usuario, RedirectAttributes redirect) {
        try {
            usuarioService.actualizar(usuario.getId(), usuario);
            redirect.addFlashAttribute("success", "Usuario actualizado correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/Usuario/Index";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirect) {
        try {
            usuarioService.eliminar(id);
            redirect.addFlashAttribute("success", "Usuario eliminado correctamente");
        } catch (Exception e) {
            redirect.addFlashAttribute("error", "Error al eliminar usuario");
        }
        return "redirect:/Usuario/Index";
    }
}