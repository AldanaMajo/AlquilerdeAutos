package AlquilerdeAutos.Servicios.Implementaciones;

import AlquilerdeAutos.Modelos.Factura;
import AlquilerdeAutos.Modelos.Pago;
import AlquilerdeAutos.Repositorios.FacturaRepository;
import AlquilerdeAutos.Servicios.Interfaces.IFacturaServicios;
import AlquilerdeAutos.Servicios.Interfaces.IPagoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class FacturaServicios implements IFacturaServicios {

    private static final BigDecimal TASA_IVA = new BigDecimal("0.13");

    private final FacturaRepository facturaRepository;
    private final IPagoServicios pagoServicios;

    @Autowired
    public FacturaServicios(FacturaRepository facturaRepository, IPagoServicios pagoServicios) {
        this.facturaRepository = facturaRepository;
        this.pagoServicios = pagoServicios;
    }

    @Override
    public List<Factura> listar() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura buscarPorId(Integer id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));
    }

    @Override
    public Factura emitir(Integer idPago) {
        Pago pago = pagoServicios.buscarPorId(idPago);

        BigDecimal total = pago.getMonto();
        BigDecimal subtotal = total.divide(BigDecimal.ONE.add(TASA_IVA), 2, RoundingMode.HALF_UP);
        BigDecimal iva = total.subtract(subtotal);

        Factura factura = new Factura();
        factura.setPago(pago);
        factura.setNumeroFactura("FAC-" + System.currentTimeMillis());
        factura.setSubtotal(subtotal);
        factura.setIva(iva);
        factura.setTotal(total);

        return facturaRepository.save(factura);
    }

    @Override
    public void eliminar(Integer id) {
        buscarPorId(id);
        facturaRepository.deleteById(id);
    }
}