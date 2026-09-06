package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Usuario;
import AlquilerdeAutos.Servicios.Interfaces.IrolServicios;
import AlquilerdeAutos.Servicios.Interfaces.IusuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // IMPORTANTE
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Usuario")
public class UsuarioController {

    private final IusuarioServicios usuarioService;
    private final IrolServicios rolService;
    private final PasswordEncoder passwordEncoder; // INYECCIÓN DEL ENCRIPTADOR

    @Autowired
    public UsuarioController(IusuarioServicios usuarioService, IrolServicios rolService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/Index")
    public String index(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("roles", rolService.listar());
        model.addAttribute("nuevoUsuario", new Usuario());
        return "Usuario/Index";
    }

    @PostMapping("/Guardar")
    public String guardar(@ModelAttribute("nuevoUsuario") Usuario usuario, RedirectAttributes redirect) {
        try {
            // ENCRIPTACIÓN DE LA CONTRASEÑA ANTES DE GUARDAR EN BASE DE DATOS
            String passwordEncriptada = passwordEncoder.encode(usuario.getPassword_hash());
            usuario.setPassword_hash(passwordEncriptada);

            usuarioService.guardar(usuario);
            redirect.addFlashAttribute("success", "Usuario creado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/Usuario/Index";
    }

    @PostMapping("/Editar")
    public String editar(@ModelAttribute Usuario usuario, RedirectAttributes redirect) {
        try {
            // Si en editar se actualiza la clave, también debe encriptarse antes de enviar
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