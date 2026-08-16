package AlquilerdeAutos.Modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "Vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdVehiculo")
    private Integer idVehiculo;

    @ManyToOne
    @JoinColumn(name = "IdMarca", nullable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "IdCategoria", nullable = false)
    private Categoria categoria;

    @NotBlank(message = "El modelo es requerido")
    @Column(name = "Modelo", length = 100, nullable = false)
    private String modelo;

    @NotNull(message = "El año es requerido")
    @Column(name = "Anio", nullable = false)
    private Integer anio;

    @NotBlank(message = "La placa es requerida")
    @Column(name = "Placa", length = 20, unique = true, nullable = false)
    private String placa;

    @Column(name = "Color", length = 30)
    private String color;

    @Column(name = "Transmision", length = 20)
    private String transmision;

    @Column(name = "Combustible", length = 20)
    private String combustible;

    @Column(name = "Capacidad")
    private Integer capacidad;

    @Column(name = "PrecioDia", precision = 10, scale = 2)
    private BigDecimal precioDia;

    @Column(name = "Imagen", length = 255)
    private String imagen;

    @Column(name = "Estado", length = 20)
    private String estado = "Disponible";

    public Vehiculo() {
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public BigDecimal getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(BigDecimal precioDia) {
        this.precioDia = precioDia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}