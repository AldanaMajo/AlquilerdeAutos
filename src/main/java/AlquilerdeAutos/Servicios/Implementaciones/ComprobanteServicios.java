package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Comprobante;
import AlquilerdeAutos.Modelos.Pago;
import AlquilerdeAutos.Repositorios.ComprobanteRepository;
import AlquilerdeAutos.Servicios.Interfaces.IcomprobanteServicios;
import AlquilerdeAutos.Servicios.Interfaces.IpagoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class ComprobanteServicios implements IcomprobanteServicios {

    private static final BigDecimal TASA_IMPUESTO = new BigDecimal("0.13"); // ejemplo: 13% IVA

    private final ComprobanteRepository comprobanteRepository;
    private final IpagoServicios pagoService;

    @Autowired
    public ComprobanteServicios(ComprobanteRepository comprobanteRepository, IpagoServicios pagoService) {
        this.comprobanteRepository = comprobanteRepository;
        this.pagoService = pagoService;
    }

    @Override
    public Optional<Comprobante> obtenerPorId(Integer id) {
        return comprobanteRepository.findById(id);
    }

    @Override
    public Optional<Comprobante> obtenerPorPago(Integer idPago) {
        return comprobanteRepository.findByPago_IdPago(idPago);
    }

    @Override
    public Comprobante emitir(Integer idPago, String serie, Comprobante.TipoComprobante tipo) {
        Pago pago = pagoService.obtenerPorId(idPago)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado con id: " + idPago));

        BigDecimal total = pago.getMonto();
        BigDecimal subtotal = total.divide(BigDecimal.ONE.add(TASA_IMPUESTO), 2, RoundingMode.HALF_UP);
        BigDecimal impuesto = total.subtract(subtotal);

        Comprobante comprobante = new Comprobante();
        comprobante.setSerie(serie);
        comprobante.setCorrelativo(generarCorrelativo());
        comprobante.setTipo(tipo);
        comprobante.setSubtotal(subtotal);
        comprobante.setImpuesto(impuesto);
        comprobante.setTotal(total);
        comprobante.setPago(pago);

        return comprobanteRepository.save(comprobante);
    }

    @Override
    public void eliminar(Integer id) {
        comprobanteRepository.deleteById(id);
    }

    private String generarCorrelativo() {
        long total = comprobanteRepository.count() + 1;
        return String.format("%08d", total);
    }
}