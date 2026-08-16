package AlquilerdeAutos.Modelos;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPago")
    private Integer idPago;

    @ManyToOne
    @JoinColumn(name = "IdAlquiler", nullable = false)
    private Alquiler alquiler;

    @Column(name = "FechaPago")
    private LocalDateTime fechaPago;

    @Column(name = "MetodoPago", length = 20)
    private String metodoPago;

    @Column(name = "Monto", precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "Estado", length = 20)
    private String estado = "Pendiente";

    public Pago() {
    }

    @PrePersist
    public void prePersist() {
        if (this.fechaPago == null) {
            this.fechaPago = LocalDateTime.now();
        }
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}