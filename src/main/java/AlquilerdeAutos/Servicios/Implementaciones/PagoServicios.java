package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Alquiler;
import AlquilerdeAutos.Modelos.Pago;
import AlquilerdeAutos.Repositorios.PagoRepository;
import AlquilerdeAutos.Servicios.Interfaces.IAlquilerServicios;
import AlquilerdeAutos.Servicios.Interfaces.IPagoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PagoServicios implements IPagoServicios {

    private final PagoRepository pagoRepository;
    private final IAlquilerServicios alquilerServicios;

    @Autowired
    public PagoServicios(PagoRepository pagoRepository, IAlquilerServicios alquilerServicios) {
        this.pagoRepository = pagoRepository;
        this.alquilerServicios = alquilerServicios;
    }

    @Override
    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago buscarPorId(Integer id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id));
    }

    @Override
    public Pago registrarPago(Integer idAlquiler, String metodoPago, BigDecimal monto) {
        Alquiler alquiler = alquilerServicios.buscarPorId(idAlquiler);

        Pago pago = new Pago();
        pago.setAlquiler(alquiler);
        pago.setMetodoPago(metodoPago);
        pago.setMonto(monto);
        pago.setEstado("Pagado");

        return pagoRepository.save(pago);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        pagoRepository.deleteById(id);
    }
}