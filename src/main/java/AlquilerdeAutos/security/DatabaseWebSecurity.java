package AlquilerdeAutos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {

    @Bean
    public UserDetailsManager customUsers(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        // 1. Obtener los datos del usuario para la autenticación
        users.setUsersByUsernameQuery(
                "SELECT Email, Password_hash, Activo FROM usuario WHERE Email = ?"
        );

        // 2. Obtener los permisos/roles usando 'Id_rol' y 'Id'
        users.setAuthoritiesByUsernameQuery(
                "SELECT u.Email, r.Nombre FROM usuario u " +
                        "INNER JOIN rol r ON r.Id = u.Id_rol " +
                        "WHERE u.Email = ?"
        );

        return users;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Permite el acceso público al login, al error y a los recursos estáticos
                        .requestMatchers("/login", "/error", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")               // Ruta GET que renderiza Home/formLogin
                        .loginProcessingUrl("/login")       // Ruta POST donde el formulario HTML envía los datos
                        .defaultSuccessUrl("/Home/index", true) // Redirección tras un inicio de sesión exitoso
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}