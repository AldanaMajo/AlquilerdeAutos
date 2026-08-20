package AlquilerdeAutos.Modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotBlank(message = "El Nombre es Requerido")
    private String Nombre;

    @NotBlank(message = "El Apellido es Requerido")
    private String Apellido;

    @NotBlank(message = "El Email es Requerido")
    @Email(message = "El Email debe ser valido")
    private String Email;

    @NotBlank(message = "El Password es Requerido")
    @Column(name = "Password_hash")
    private String Password_hash;

    private Boolean Activo = true;

    @Column(name = "Fecha_creacion", updatable = false)
    private LocalDateTime Fecha_creacion;

    @NotNull(message = "El Rol es Requerido")
    @ManyToOne
    @JoinColumn(name = "Id_rol")
    private Rol rol;

    @PrePersist
    protected void onCreate() {
        if (Fecha_creacion == null) {
            Fecha_creacion = LocalDateTime.now();
        }
    }

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

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword_hash() {
        return Password_hash;
    }

    public void setPassword_hash(String password_hash) {
        Password_hash = password_hash;
    }

    public Boolean getActivo() {
        return Activo;
    }

    public void setActivo(Boolean activo) {
        Activo = activo;
    }

    public LocalDateTime getFecha_creacion() {
        return Fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        Fecha_creacion = fecha_creacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
