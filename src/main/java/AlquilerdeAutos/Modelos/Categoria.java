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
    private Integer Id;

    @NotBlank(message = "El Nombre es Requerido")
    private String Nombre;

    @NotNull(message = "La Tarifa Base Diaria es Requerida")
    @Positive(message = "La Tarifa Base Diaria debe ser mayor a 0")
    @Column(name = "Tarifa_base_diaria")
    private BigDecimal Tarifa_base_diaria;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public BigDecimal getTarifa_base_diaria() {
        return Tarifa_base_diaria;
    }

    public void setTarifa_base_diaria(BigDecimal tarifa_base_diaria) {
        Tarifa_base_diaria = tarifa_base_diaria;
    }
}