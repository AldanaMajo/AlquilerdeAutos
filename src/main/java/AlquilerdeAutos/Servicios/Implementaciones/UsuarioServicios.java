package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Usuario;
import AlquilerdeAutos.Repositorios.UsuarioRepository;
import AlquilerdeAutos.Servicios.Interfaces.IUsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServicios implements IUsuarioServicios {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServicios(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public Usuario buscarPorGmail(String gmail) {
        return usuarioRepository.findByGmail(gmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con gmail: " + gmail));
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        if (usuario.getFechaDeRegristro() == null) {
            usuario.setFechaDeRegristro(LocalDateTime.now());
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Integer id, Usuario usuario) {
        Usuario existente = buscarPorId(id);
        existente.setNombre(usuario.getNombre());
        existente.setApellido(usuario.getApellido());
        existente.setGmail(usuario.getGmail());
        if (usuario.getContrasena() != null && !usuario.getContrasena().isBlank()) {
            existente.setContrasena(usuario.getContrasena());
        }
        existente.setRol(usuario.getRol());
        return usuarioRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        usuarioRepository.deleteById(id);
    }
}