package AlquilerdeAutos.Modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El Nombre es Requerido")
    @Column(name = "nombre")
    private String nombre;

    @NotNull(message = "La Tarifa Base Diaria es Requerida")
    @Positive(message = "La Tarifa Base Diaria debe ser mayor a 0")
    @Column(name = "tarifa_base_diaria")
    private BigDecimal tarifaBaseDiaria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getTarifaBaseDiaria() {
        return tarifaBaseDiaria;
    }

    public void setTarifaBaseDiaria(BigDecimal tarifaBaseDiaria) {
        this.tarifaBaseDiaria = tarifaBaseDiaria;
    }
}