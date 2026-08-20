package AlquilerdeAutos.Modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "comprobantes")
public class Comprobante {

    public enum TipoComprobante {
        FACTURA, BOLETA, RECIBO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "La Serie es Requerida")
    private String Serie;

    @NotBlank(message = "El Correlativo es Requerido")
    private String Correlativo;

    @NotNull(message = "El Tipo es Requerido")
    @Enumerated(EnumType.STRING)
    private TipoComprobante Tipo;

    @Column(name = "Fecha_emision", updatable = false)
    private LocalDateTime Fecha_emision;

    @NotNull(message = "El Subtotal es Requerido")
    private BigDecimal Subtotal;

    @NotNull(message = "El Impuesto es Requerido")
    private BigDecimal Impuesto;

    @NotNull(message = "El Total es Requerido")
    private BigDecimal Total;

    @NotNull(message = "El Pago es Requerido")
    @OneToOne
    @JoinColumn(name = "Id_pago")
    private Pago pago;

    @PrePersist
    protected void onCreate() {
        if (Fecha_emision == null) {
            Fecha_emision = LocalDateTime.now();
        }
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String serie) {
        Serie = serie;
    }

    public String getCorrelativo() {
        return Correlativo;
    }

    public void setCorrelativo(String correlativo) {
        Correlativo = correlativo;
    }

    public TipoComprobante getTipo() {
        return Tipo;
    }

    public void setTipo(TipoComprobante tipo) {
        Tipo = tipo;
    }

    public LocalDateTime getFecha_emision() {
        return Fecha_emision;
    }

    public void setFecha_emision(LocalDateTime fecha_emision) {
        Fecha_emision = fecha_emision;
    }

    public BigDecimal getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        Subtotal = subtotal;
    }

    public BigDecimal getImpuesto() {
        return Impuesto;
    }

    public void setImpuesto(BigDecimal impuesto) {
        Impuesto = impuesto;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}