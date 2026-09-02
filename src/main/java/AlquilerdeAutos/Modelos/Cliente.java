package AlquilerdeAutos.Modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El Documento de Identidad es Requerido")
    @Column(name = "Documento_identidad")
    private String documentoIdentidad;

    @NotBlank(message = "El Nombre es Requerido")
    private String nombre;

    @NotBlank(message = "El Apellido es Requerido")
    private String apellido;

    private String telefono;

    @NotBlank(message = "El Email es Requerido")
    @Email(message = "El Email debe ser valido")
    private String email;

    private String direccion;

    @NotBlank(message = "El Numero de Licencia es Requerido")
    @Column(name = "Numero_licencia")
    private String numeroLicencia;

    @NotNull(message = "La Categoria de Licencia es Requerida")
    @ManyToOne
    @JoinColumn(name = "Id_categoria_licencia")
    private CategoriaLicencia categoriaLicencia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public CategoriaLicencia getCategoriaLicencia() {
        return categoriaLicencia;
    }

    public void setCategoriaLicencia(CategoriaLicencia categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }
}