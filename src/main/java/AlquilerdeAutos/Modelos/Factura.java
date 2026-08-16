package AlquilerdeAutos.Modelos;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFactura")
    private Integer idFactura;

    @ManyToOne
    @JoinColumn(name = "IdPago", nullable = false)
    private Pago pago;

    @Column(name = "NumeroFactura", length = 30, unique = true)
    private String numeroFactura;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Subtotal", precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "IVA", precision = 10, scale = 2)
    private BigDecimal iva;

    @Column(name = "Total", precision = 10, scale = 2)
    private BigDecimal total;

    public Factura() {
    }

    @PrePersist
    public void prePersist() {
        if (this.fecha == null) {
            this.fecha = LocalDateTime.now();
        }
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}