package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Comprobante;
import AlquilerdeAutos.Repositorios.ComprobanteRepository;
import AlquilerdeAutos.Servicios.Interfaces.IcomprobanteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprobanteServicios implements IcomprobanteServicios {

    private final ComprobanteRepository comprobanteRepository;

    @Autowired
    public ComprobanteServicios(ComprobanteRepository comprobanteRepository) {
        this.comprobanteRepository = comprobanteRepository;
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
    public Comprobante guardar(Comprobante comprobante) {
        return comprobanteRepository.save(comprobante);
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
