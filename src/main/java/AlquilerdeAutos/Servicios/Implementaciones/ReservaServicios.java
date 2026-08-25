package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Reserva;
import AlquilerdeAutos.Repositorios.ReservaRepository;
import AlquilerdeAutos.Servicios.Interfaces.IreservaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaServicios implements IreservaServicios {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaServicios(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<Reserva> listar() {
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> listarPorCliente(Integer idCliente) {
        return reservaRepository.findByCliente_Id(idCliente);
    }

    @Override
    public Reserva buscarPorId(Integer id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con id: " + id));
    }

    @Override
    public Reserva buscarPorCodigo(String codigoReserva) {
        return reservaRepository.findByCodigo_reserva(codigoReserva)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con codigo: " + codigoReserva));
    }

    @Override
    public Reserva guardar(Reserva reserva) {
        if (reserva.getCodigo_reserva() == null || reserva.getCodigo_reserva().isBlank()) {
            reserva.setCodigo_reserva(generarCodigoReserva());
        }
        if (reserva.getEstado() == null) {
            reserva.setEstado(Reserva.EstadoReserva.PENDIENTE);
        }
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva actualizar(Integer id, Reserva reserva) {
        Reserva existente = buscarPorId(id);
        existente.setFecha_inicio(reserva.getFecha_inicio());
        existente.setFecha_fin(reserva.getFecha_fin());
        existente.setPrecio_diario_acordado(reserva.getPrecio_diario_acordado());
        existente.setCliente(reserva.getCliente());
        existente.setVehiculo(reserva.getVehiculo());
        return reservaRepository.save(existente);
    }

    @Override
    public Reserva confirmar(Integer id) {
        Reserva reserva = buscarPorId(id);
        reserva.setEstado(Reserva.EstadoReserva.CONFIRMADA);
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva cancelar(Integer id) {
        Reserva reserva = buscarPorId(id);
        reserva.setEstado(Reserva.EstadoReserva.CANCELADA);
        return reservaRepository.save(reserva);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        reservaRepository.deleteById(id);
    }

    private String generarCodigoReserva() {
        return "RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
