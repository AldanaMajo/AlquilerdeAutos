package AlquilerdeAutos.controladores;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/Home")
public class HomeController {
    @GetMapping("/index")
    public String index() {

        return "Home/index";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "Home/formLogin"; // Nombre de la plantilla HTML del Login
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/login?logout";
    }
}