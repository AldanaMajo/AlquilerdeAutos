package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Alquiler;
import AlquilerdeAutos.Modelos.Reserva;
import AlquilerdeAutos.Repositorios.AlquilerRepository;
import AlquilerdeAutos.Repositorios.ReservaRepository;
import AlquilerdeAutos.Servicios.Interfaces.IalquilerServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlquilerServicios implements IalquilerServicios {

    private final AlquilerRepository alquilerRepository;
    private final ReservaRepository reservaRepository; // 1. Agregado

    @Autowired
    public AlquilerServicios(AlquilerRepository alquilerRepository, ReservaRepository reservaRepository) { // 2. Agregado
        this.alquilerRepository = alquilerRepository;
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<Alquiler> listar() {
        return alquilerRepository.findAll();
    }

    @Override
    public List<Alquiler> listarPorCliente(Integer idCliente) {
        return alquilerRepository.findByCliente_Id(idCliente);
    }

    @Override
    public Alquiler buscarPorId(Integer id) {
        return alquilerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado con id: " + id));
    }

    @Override
    public Alquiler guardar(Alquiler alquiler) {
        if (alquiler.getFecha_inicio() == null) {
            alquiler.setFecha_inicio(LocalDateTime.now());
        }
        if (alquiler.getEstado() == null) {
            alquiler.setEstado(Alquiler.EstadoAlquiler.EN_PROCESO);
        }

        // 3. Validación de la Reserva para evitar el error TransientPropertyValueException
        if (alquiler.getReserva() != null && alquiler.getReserva().getId() != null) {
            Integer reservaId = alquiler.getReserva().getId();
            Reserva reservaBD = reservaRepository.findById(reservaId).orElse(null);
            alquiler.setReserva(reservaBD);
        } else {
            alquiler.setReserva(null);
        }

        return alquilerRepository.save(alquiler);
    }

    @Override
    public Alquiler actualizar(Integer id, Alquiler alquiler) {
        Alquiler existente = buscarPorId(id);
        existente.setFecha_fin_programada(alquiler.getFecha_fin_programada());
        existente.setKilometraje_inicial(alquiler.getKilometraje_inicial());
        existente.setPrecio_diario_aplicado(alquiler.getPrecio_diario_aplicado());
        existente.setCliente(alquiler.getCliente());
        existente.setVehiculo(alquiler.getVehiculo());
        existente.setUsuario(alquiler.getUsuario());

        // Manejo de Reserva al actualizar
        if (alquiler.getReserva() != null && alquiler.getReserva().getId() != null) {
            Integer reservaId = alquiler.getReserva().getId();
            Reserva reservaBD = reservaRepository.findById(reservaId).orElse(null);
            existente.setReserva(reservaBD);
        } else {
            existente.setReserva(null);
        }

        return alquilerRepository.save(existente);
    }

    @Override
    public Alquiler finalizar(Integer id, Integer kilometrajeFinal) {
        Alquiler alquiler = buscarPorId(id);

        if (alquiler.getEstado() != Alquiler.EstadoAlquiler.EN_PROCESO) {
            throw new RuntimeException("El alquiler no esta en proceso");
        }

        LocalDateTime ahora = LocalDateTime.now();
        long horas = Duration.between(alquiler.getFecha_inicio(), ahora).toHours();
        long dias = Math.max(1, (long) Math.ceil(horas / 24.0));

        alquiler.setFecha_devolucion_real(ahora);
        alquiler.setKilometraje_final(kilometrajeFinal);
        alquiler.setMonto_total(alquiler.getPrecio_diario_aplicado().multiply(BigDecimal.valueOf(dias)));
        alquiler.setEstado(Alquiler.EstadoAlquiler.FINALIZADO);

        return alquilerRepository.save(alquiler);
    }

    @Override
    public Alquiler cancelar(Integer id) {
        Alquiler alquiler = buscarPorId(id);
        alquiler.setEstado(Alquiler.EstadoAlquiler.CANCELADO);
        return alquilerRepository.save(alquiler);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        alquilerRepository.deleteById(id);
    }
}