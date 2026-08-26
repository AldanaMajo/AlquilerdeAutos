package AlquilerdeAutos.controladores;

import AlquilerdeAutos.Modelos.Rol;
import AlquilerdeAutos.Servicios.Interfaces.IrolServicios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RolControllerTest {

    @Mock
    private IrolServicios rolService;

    private RolController rolController;

    @BeforeEach
    void setUp() {
        rolController = new RolController(rolService);
    }

    @Test
    void listarDebeRetornarListaDeRoles() {

        Rol rol = mock(Rol.class);
        List<Rol> roles = List.of(rol);

        when(rolService.listar()).thenReturn(roles);

        ResponseEntity<List<Rol>> respuesta = rolController.listar();

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(roles, respuesta.getBody());

        verify(rolService, times(1)).listar();
    }

    @Test
    void buscarPorIdDebeRetornarRol() {

        Integer id = 1;
        Rol rol = mock(Rol.class);

        when(rolService.buscarPorId(id)).thenReturn(rol);

        ResponseEntity<?> respuesta = rolController.buscarPorId(id);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(rol, respuesta.getBody());

        verify(rolService, times(1)).buscarPorId(id);
    }

    @Test
    void buscarPorIdDebeRetornar404CuandoNoExiste() {

        Integer id = 99;

        when(rolService.buscarPorId(id))
                .thenThrow(new RuntimeException("Rol no encontrado"));

        ResponseEntity<?> respuesta = rolController.buscarPorId(id);

        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());

        verify(rolService, times(1)).buscarPorId(id);
    }

    @Test
    void guardarDebeRetornarRolCreado() {

        Rol rol = mock(Rol.class);
        Rol creado = mock(Rol.class);

        when(rolService.guardar(rol)).thenReturn(creado);

        ResponseEntity<Rol> respuesta = rolController.guardar(rol);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertEquals(creado, respuesta.getBody());

        verify(rolService, times(1)).guardar(rol);
    }

    @Test
    void actualizarDebeRetornarRolActualizado() {

        Integer id = 1;
        Rol rol = mock(Rol.class);
        Rol actualizado = mock(Rol.class);

        when(rolService.actualizar(id, rol)).thenReturn(actualizado);

        ResponseEntity<?> respuesta = rolController.actualizar(id, rol);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(actualizado, respuesta.getBody());

        verify(rolService, times(1)).actualizar(id, rol);
    }

    @Test
    void actualizarDebeRetornar404CuandoNoExiste() {

        Integer id = 99;
        Rol rol = mock(Rol.class);

        when(rolService.actualizar(id, rol))
                .thenThrow(new RuntimeException("Rol no encontrado"));

        ResponseEntity<?> respuesta = rolController.actualizar(id, rol);

        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());

        verify(rolService, times(1)).actualizar(id, rol);
    }

    @Test
    void eliminarDebeRetornar204() {

        Integer id = 1;

        doNothing().when(rolService).eliminar(id);

        ResponseEntity<Void> respuesta = rolController.eliminar(id);

        assertEquals(HttpStatus.NO_CONTENT, respuesta.getStatusCode());
        assertNull(respuesta.getBody());

        verify(rolService, times(1)).eliminar(id);
    }
}