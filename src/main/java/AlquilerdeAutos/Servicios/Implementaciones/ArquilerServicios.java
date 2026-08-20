package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Alquiler;
import AlquilerdeAutos.Modelos.Reserva;
import AlquilerdeAutos.Repositorios.AlquilerRepository;
import AlquilerdeAutos.Servicios.Interfaces.IAlquilerServicios;
import AlquilerdeAutos.Servicios.Interfaces.IReservaServicios;
import AlquilerdeAutos.Servicios.Interfaces.IVehiculoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AlquilerServicios implements IAlquilerServicios {

    private final AlquilerRepository alquilerRepository;
    private final IReservaServicios reservaServicios;
    private final IVehiculoServicios vehiculoServicios;

    @Autowired
    public AlquilerServicios(AlquilerRepository alquilerRepository,
                             IReservaServicios reservaServicios,
                             IVehiculoServicios vehiculoServicios) {
        this.alquilerRepository = alquilerRepository;
        this.reservaServicios = reservaServicios;
        this.vehiculoServicios = vehiculoServicios;
    }

    @Override
    public List<Alquiler> listar() {
        return alquilerRepository.findAll();
    }

    @Override
    public Alquiler buscarPorId(Integer id) {
        return alquilerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado con id: " + id));
    }

    @Override
    public Alquiler iniciar(Integer idReserva, LocalDate fechaEntrega, BigDecimal precioDia) {
        Reserva reserva = reservaServicios.buscarPorId(idReserva);

        Alquiler alquiler = new Alquiler();
        alquiler.setReserva(reserva);
        alquiler.setFechaEntrega(fechaEntrega);
        alquiler.setPrecioDia(precioDia);
        alquiler.setEstado("Activo");

        Alquiler guardado = alquilerRepository.save(alquiler);

        vehiculoServicios.cambiarEstado(reserva.getVehiculo().getIdVehiculo(), "Alquilado");

        return guardado;
    }

    @Override
    public Alquiler finalizar(Integer idAlquiler, LocalDate fechaDevolucion) {
        Alquiler alquiler = buscarPorId(idAlquiler);

        long dias = ChronoUnit.DAYS.between(alquiler.getFechaEntrega(), fechaDevolucion);
        if (dias <= 0) {
            dias = 1;
        }

        alquiler.setFechaDevolucion(fechaDevolucion);
        alquiler.setDias((int) dias);
        alquiler.setTotal(alquiler.getPrecioDia().multiply(BigDecimal.valueOf(dias)));
        alquiler.setEstado("Finalizado");

        Alquiler guardado = alquilerRepository.save(alquiler);

        vehiculoServicios.cambiarEstado(alquiler.getReserva().getVehiculo().getIdVehiculo(), "Disponible");

        return guardado;
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        alquilerRepository.deleteById(id);
    }
}