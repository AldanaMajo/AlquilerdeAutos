package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Pago;
import AlquilerdeAutos.Repositorios.PagoRepository;
import AlquilerdeAutos.Servicios.Interfaces.IpagoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServicios implements IpagoServicios {

    private final PagoRepository pagoRepository;

    @Autowired
    public PagoServicios(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    @Override
    public List<Pago> listarPorAlquiler(Integer idAlquiler) {
        return pagoRepository.findByAlquiler_Id(idAlquiler);
    }

    @Override
    public Pago buscarPorId(Integer id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id));
    }

    @Override
    public Pago guardar(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago actualizar(Integer id, Pago pago) {
        Pago existente = buscarPorId(id);
        existente.setMonto(pago.getMonto());
        existente.setMetodo_pago(pago.getMetodo_pago());
        existente.setAlquiler(pago.getAlquiler());
        return pagoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        pagoRepository.deleteById(id);
    }
}
