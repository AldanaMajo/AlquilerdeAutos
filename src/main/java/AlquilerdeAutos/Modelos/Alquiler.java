package AlquilerdeAutos.Modelos;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Alquiler")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAlquiler")
    private Integer idAlquiler;

    @ManyToOne
    @JoinColumn(name = "IdReserva", nullable = false)
    private Reserva reserva;

    @Column(name = "FechaEntrega")
    private LocalDate fechaEntrega;

    @Column(name = "FechaDevolucion")
    private LocalDate fechaDevolucion;

    @Column(name = "Dias")
    private Integer dias;

    @Column(name = "PrecioDia", precision = 10, scale = 2)
    private BigDecimal precioDia;

    @Column(name = "Total", precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "Estado", length = 20)
    private String estado = "Activo";

    public Alquiler() {
    }

    public Integer getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(Integer idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public BigDecimal getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(BigDecimal precioDia) {
        this.precioDia = precioDia;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}