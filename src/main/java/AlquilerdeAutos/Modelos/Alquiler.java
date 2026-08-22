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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "alquileres")
public class Alquiler {

    public enum EstadoAlquiler {
        EN_PROCESO, FINALIZADO, CANCELADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotNull(message = "La Fecha de Inicio es Requerida")
    @Column(name = "Fecha_inicio")
    private LocalDateTime Fecha_inicio;

    @NotNull(message = "La Fecha de Fin Programada es Requerida")
    @Column(name = "Fecha_fin_programada")
    private LocalDateTime Fecha_fin_programada;

    @Column(name = "Fecha_devolucion_real")
    private LocalDateTime Fecha_devolucion_real;

    @NotNull(message = "El Kilometraje Inicial es Requerido")
    @Column(name = "Kilometraje_inicial")
    private Integer Kilometraje_inicial;

    @Column(name = "Kilometraje_final")
    private Integer Kilometraje_final;

    @NotNull(message = "El Precio Diario Aplicado es Requerido")
    @Positive(message = "El Precio Diario Aplicado debe ser mayor a 0")
    @Column(name = "Precio_diario_aplicado")
    private BigDecimal Precio_diario_aplicado;

    @Column(name = "Monto_total")
    private BigDecimal Monto_total;

    @NotNull(message = "El Estado es Requerido")
    @Enumerated(EnumType.STRING)
    private EstadoAlquiler Estado = EstadoAlquiler.EN_PROCESO;

    @OneToOne
    @JoinColumn(name = "Id_reserva")
    private Reserva reserva;

    @NotNull(message = "El Cliente es Requerido")
    @ManyToOne
    @JoinColumn(name = "Id_cliente")
    private Cliente cliente;

    @NotNull(message = "El Vehiculo es Requerido")
    @ManyToOne
    @JoinColumn(name = "Id_vehiculo")
    private Vehiculo vehiculo;

    @NotNull(message = "El Usuario es Requerido")
    @ManyToOne
    @JoinColumn(name = "Id_usuario")
    private Usuario usuario;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public LocalDateTime getFecha_inicio() {
        return Fecha_inicio;
    }

    public void setFecha_inicio(LocalDateTime fecha_inicio) {
        Fecha_inicio = fecha_inicio;
    }

    public LocalDateTime getFecha_fin_programada() {
        return Fecha_fin_programada;
    }

    public void setFecha_fin_programada(LocalDateTime fecha_fin_programada) {
        Fecha_fin_programada = fecha_fin_programada;
    }

    public LocalDateTime getFecha_devolucion_real() {
        return Fecha_devolucion_real;
    }

    public void setFecha_devolucion_real(LocalDateTime fecha_devolucion_real) {
        Fecha_devolucion_real = fecha_devolucion_real;
    }

    public Integer getKilometraje_inicial() {
        return Kilometraje_inicial;
    }

    public void setKilometraje_inicial(Integer kilometraje_inicial) {
        Kilometraje_inicial = kilometraje_inicial;
    }

    public Integer getKilometraje_final() {
        return Kilometraje_final;
    }

    public void setKilometraje_final(Integer kilometraje_final) {
        Kilometraje_final = kilometraje_final;
    }

    public BigDecimal getPrecio_diario_aplicado() {
        return Precio_diario_aplicado;
    }

    public void setPrecio_diario_aplicado(BigDecimal precio_diario_aplicado) {
        Precio_diario_aplicado = precio_diario_aplicado;
    }

    public BigDecimal getMonto_total() {
        return Monto_total;
    }

    public void setMonto_total(BigDecimal monto_total) {
        Monto_total = monto_total;
    }

    public EstadoAlquiler getEstado() {
        return Estado;
    }

    public void setEstado(EstadoAlquiler estado) {
        Estado = estado;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}