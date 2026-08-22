package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Reserva;
import AlquilerdeAutos.Repositorios.ReservaRepository;
import AlquilerdeAutos.Servicios.Interfaces.IReservaServicios;
import AlquilerdeAutos.Servicios.Interfaces.IVehiculoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServicios implements IReservaServicios {

    private final ReservaRepository reservaRepository;
    private final IVehiculoServicios vehiculoServicios;

    @Autowired
    public ReservaServicios(ReservaRepository reservaRepository, IVehiculoServicios vehiculoServicios) {
        this.reservaRepository = reservaRepository;
        this.vehiculoServicios = vehiculoServicios;
    }

    @Override
    public List<Reserva> listar() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva buscarPorId(Integer id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con id: " + id));
    }

    @Override
    public Reserva crear(Reserva reserva) {
        var vehiculo = vehiculoServicios.buscarPorId(reserva.getVehiculo().getIdVehiculo());

        if (!"Disponible".equalsIgnoreCase(vehiculo.getEstado())) {
            throw new RuntimeException("El vehiculo no esta disponible para reservar");
        }

        reserva.setEstado("Pendiente");
        Reserva guardada = reservaRepository.save(reserva);

        vehiculoServicios.cambiarEstado(vehiculo.getIdVehiculo(), "Reservado");

        return guardada;
    }

    @Override
    public Reserva actualizar(Integer id, Reserva reserva) {
        Reserva existente = buscarPorId(id);
        existente.setCliente(reserva.getCliente());
        existente.setVehiculo(reserva.getVehiculo());
        existente.setFechaInicio(reserva.getFechaInicio());
        existente.setFechaFin(reserva.getFechaFin());
        return reservaRepository.save(existente);
    }

    @Override
    public Reserva cancelar(Integer id) {
        Reserva existente = buscarPorId(id);
        existente.setEstado("Cancelada");
        vehiculoServicios.cambiarEstado(existente.getVehiculo().getIdVehiculo(), "Disponible");
        return reservaRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        reservaRepository.deleteById(id);
    }
}