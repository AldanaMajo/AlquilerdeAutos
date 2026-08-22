package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Alquiler;
import AlquilerdeAutos.Modelos.Pago;
import AlquilerdeAutos.Repositorios.PagoRepository;
import AlquilerdeAutos.Servicios.Interfaces.IalquilerServicios;
import AlquilerdeAutos.Servicios.Interfaces.IpagoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PagoServicios implements IpagoServicios {

    private final PagoRepository pagoRepository;
    private final IalquilerServicios alquilerServicios;

    @Autowired
    public PagoServicios(PagoRepository pagoRepository, IalquilerServicios alquilerServicios) {
        this.pagoRepository = pagoRepository;
        this.alquilerServicios = alquilerServicios;
    }

    @Override
    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    @Override
    public Optional<Pago> obtenerPorId(Integer id) {
        return pagoRepository.findById(id);
    }

    @Override
    public Pago registrarPago(Integer idAlquiler, String metodoPago, BigDecimal monto) {
        Alquiler alquiler = alquilerServicios.obtenerPorId(idAlquiler)
                .orElseThrow(() -> new IllegalArgumentException("Alquiler no encontrado con id: " + idAlquiler));

        Pago pago = new Pago();
        pago.setAlquiler(alquiler);
        pago.setMetodoPago(metodoPago);
        pago.setMonto(monto);
        pago.setEstado("Pagado");

        return pagoRepository.save(pago);
    }

    @Override
    public void eliminar(Integer id) {
        pagoRepository.deleteById(id);
    }
}