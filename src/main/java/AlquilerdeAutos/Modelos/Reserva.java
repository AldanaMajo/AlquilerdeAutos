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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {

    public enum EstadoReserva {
        PENDIENTE, CONFIRMADA, CANCELADA, COMPLETADA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "El Codigo de Reserva es Requerido")
    @Column(name = "Codigo_reserva")
    private String Codigo_reserva;

    @Column(name = "Fecha_solicitud", updatable = false)
    private LocalDateTime Fecha_solicitud;

    @NotNull(message = "La Fecha de Inicio es Requerida")
    @Column(name = "Fecha_inicio")
    private LocalDate Fecha_inicio;

    @NotNull(message = "La Fecha de Fin es Requerida")
    @Column(name = "Fecha_fin")
    private LocalDate Fecha_fin;

    @NotNull(message = "El Precio Diario Acordado es Requerido")
    @Positive(message = "El Precio Diario Acordado debe ser mayor a 0")
    @Column(name = "Precio_diario_acordado")
    private BigDecimal Precio_diario_acordado;

    @NotNull(message = "El Estado es Requerido")
    @Enumerated(EnumType.STRING)
    private EstadoReserva Estado = EstadoReserva.PENDIENTE;

    @NotNull(message = "El Cliente es Requerido")
    @ManyToOne
    @JoinColumn(name = "Id_cliente")
    private Cliente cliente;

    @NotNull(message = "El Vehiculo es Requerido")
    @ManyToOne
    @JoinColumn(name = "Id_vehiculo")
    private Vehiculo vehiculo;

    @PrePersist
    protected void onCreate() {
        if (Fecha_solicitud == null) {
            Fecha_solicitud = LocalDateTime.now();
        }
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCodigoReserva() {
        return Codigo_reserva;
    }

    public void setCodigoReserva(String codigo_reserva) {
        Codigo_reserva = codigo_reserva;
    }

    public LocalDateTime getFecha_solicitud() {
        return Fecha_solicitud;
    }

    public void setFecha_solicitud(LocalDateTime fecha_solicitud) {
        Fecha_solicitud = fecha_solicitud;
    }

    public LocalDate getFecha_inicio() {
        return Fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        Fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return Fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        Fecha_fin = fecha_fin;
    }

    public BigDecimal getPrecio_diario_acordado() {
        return Precio_diario_acordado;
    }

    public void setPrecio_diario_acordado(BigDecimal precio_diario_acordado) {
        Precio_diario_acordado = precio_diario_acordado;
    }

    public EstadoReserva getEstado() {
        return Estado;
    }

    public void setEstado(EstadoReserva estado) {
        Estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}