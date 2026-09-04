package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Comprobante;
import AlquilerdeAutos.Repositorios.ComprobanteRepository;
import AlquilerdeAutos.Repositorios.PagoRepository;
import AlquilerdeAutos.Servicios.Interfaces.IcomprobanteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprobanteServicios implements IcomprobanteServicios {

    private final ComprobanteRepository comprobanteRepository;

    @Autowired
    public ComprobanteServicios(ComprobanteRepository comprobanteRepository) {
        this.comprobanteRepository = comprobanteRepository;
    }
    @Autowired
    private PagoRepository pagoRepository; // Inyecta el repositorio de pagos

    @Override
    public Comprobante guardar(Comprobante comprobante) {
        if (comprobante.getPago() == null || comprobante.getPago().getId() == null) {
            throw new IllegalArgumentException("Debe asociar un ID de Pago válido.");
        }

        // Verificar si el pago realmente existe en la BD
        boolean existePago = pagoRepository.existsById(comprobante.getPago().getId());
        if (!existePago) {
            throw new RuntimeException("El ID de Pago " + comprobante.getPago().getId() + " no existe.");
        }

        return comprobanteRepository.save(comprobante);
    }

    @Override
    public List<Comprobante> obtenerTodos(String buscar) {
        if (buscar != null && !buscar.trim().isEmpty()) {
            return comprobanteRepository.buscarPorTermino(buscar);
        }
        return comprobanteRepository.findAll();
    }

    @Override
    public Comprobante buscarPorId(Integer id) {
        return comprobanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comprobante no encontrado con id: " + id));
    }

    @Override
    public Comprobante buscarPorPago(Integer idPago) {
        return comprobanteRepository.findByPago_Id(idPago)
                .orElseThrow(() -> new RuntimeException("Comprobante no encontrado para el pago: " + idPago));
    }

    @Override
    public Comprobante actualizar(Integer id, Comprobante comprobante) {
        Comprobante existente = buscarPorId(id);
        existente.setSerie(comprobante.getSerie());
        existente.setCorrelativo(comprobante.getCorrelativo());
        existente.setTipo(comprobante.getTipo());
        existente.setSubtotal(comprobante.getSubtotal());
        existente.setImpuesto(comprobante.getImpuesto());
        existente.setTotal(comprobante.getTotal());
        existente.setPago(comprobante.getPago());
        return comprobanteRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        comprobanteRepository.deleteById(id);
    }
}