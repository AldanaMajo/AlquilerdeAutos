package AlquilerdeAutos.Modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "El Documento de Identidad es Requerido")
    @Column(name = "Documento_identidad")
    private String Documento_identidad;


    @NotBlank(message = "El Nombre es Requerido")
    private String Nombre;

    @NotBlank(message = "El Apellido es Requerido")
    private String Apellido;

    private String Telefono;

    @NotBlank(message = "El Email es Requerido")
    @Email(message = "El Email debe ser valido")
    private String Email;

    private String Direccion;

    @NotBlank(message = "El Numero de Licencia es Requerido")
    @Column(name = "Numero_licencia")
    private String NumeroLicencia;

    @NotNull(message = "La Categoria de Licencia es Requerida")
    @ManyToOne
    @JoinColumn(name = "Id_categoria_licencia")
    private CategoriaLicencia categoriaLicencia;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDocumentoIdentidad() {
        return Documento_identidad;
    }

    public void setDocumentoIdentidad(String documento_identidad) {
        Documento_identidad = documento_identidad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public CategoriaLicencia getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public String getNumeroLicencia() {
        return NumeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        NumeroLicencia = numeroLicencia;
    }

    public void setCategoriaLicencia(CategoriaLicencia categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }
}