package AlquilerdeAutos.Modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
public class Pago {

    public enum MetodoPago {
        EFECTIVO, TARJETA_CREDITO, TARJETA_DEBITO, TRANSFERENCIA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotNull(message = "El Monto es Requerido")
    @Positive(message = "El Monto debe ser mayor a 0")
    private BigDecimal Monto;

    @Column(name = "Fecha_pago", updatable = false)
    private LocalDateTime Fecha_pago;

    @NotNull(message = "El Metodo de Pago es Requerido")
    @Enumerated(EnumType.STRING)
    @Column(name = "Metodo_pago")
    private MetodoPago Metodo_pago;

    @NotNull(message = "El Alquiler es Requerido")
    @ManyToOne
    @JoinColumn(name = "Id_alquiler")
    private Alquiler alquiler;

    @PrePersist
    protected void onCreate() {
        if (Fecha_pago == null) {
            Fecha_pago = LocalDateTime.now();
        }
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public BigDecimal getMonto() {
        return Monto;
    }

    public void setMonto(BigDecimal monto) {
        Monto = monto;
    }

    public LocalDateTime getFecha_pago() {
        return Fecha_pago;
    }

    public void setFecha_pago(LocalDateTime fecha_pago) {
        Fecha_pago = fecha_pago;
    }

    public MetodoPago getMetodo_pago() {
        return Metodo_pago;
    }

    public void setMetodo_pago(MetodoPago metodo_pago) {
        Metodo_pago = metodo_pago;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }
}