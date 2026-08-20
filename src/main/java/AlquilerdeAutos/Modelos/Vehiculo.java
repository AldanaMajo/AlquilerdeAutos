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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    public enum EstadoVehiculo {
        DISPONIBLE, ALQUILADO, MANTENIMIENTO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "La Placa es Requerida")
    private String Placa;

    @NotBlank(message = "El Modelo es Requerido")
    private String Modelo;

    @NotNull(message = "El Anio es Requerido")
    private Integer Anio;

    @NotBlank(message = "El Color es Requerido")
    private String Color;

    @NotNull(message = "El Precio por Dia es Requerido")
    @Positive(message = "El Precio por Dia debe ser mayor a 0")
    @Column(name = "Precio_por_dia")
    private BigDecimal Precio_por_dia;

    @NotNull(message = "El Estado es Requerido")
    @Enumerated(EnumType.STRING)
    private EstadoVehiculo Estado = EstadoVehiculo.DISPONIBLE;

    @NotNull(message = "La Marca es Requerida")
    @ManyToOne
    @JoinColumn(name = "Id_marca")
    private Marca marca;

    @NotNull(message = "La Categoria es Requerida")
    @ManyToOne
    @JoinColumn(name = "Id_categoria")
    private Categoria categoria;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public Integer getAnio() {
        return Anio;
    }

    public void setAnio(Integer anio) {
        Anio = anio;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public BigDecimal getPrecio_por_dia() {
        return Precio_por_dia;
    }

    public void setPrecio_por_dia(BigDecimal precio_por_dia) {
        Precio_por_dia = precio_por_dia;
    }

    public EstadoVehiculo getEstado() {
        return Estado;
    }

    public void setEstado(EstadoVehiculo estado) {
        Estado = estado;
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
}