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
    private Integer id; // Cambiado Id -> id

    @NotBlank(message = "El Nombre es Requerido")
    private String nombre; // Cambiado Nombre -> nombre

    @NotBlank(message = "El Apellido es Requerido")
    private String apellido; // Cambiado Apellido -> apellido

    @NotBlank(message = "El Email es Requerido")
    @Email(message = "El Email debe ser valido")
    private String email; // Cambiado Email -> email

    @NotBlank(message = "El Password es Requerido")
    @Column(name = "Password_hash")
    private String password_hash; // Cambiado Password_hash -> password_hash

    private Boolean activo = true; // Cambiado Activo -> activo

    @Column(name = "Fecha_creacion", updatable = false)
    private LocalDateTime fecha_creacion; // Cambiado Fecha_creacion -> fecha_creacion

    @NotNull(message = "El Rol es Requerido")
    @ManyToOne
    @JoinColumn(name = "Id_rol")
    private Rol rol;

    @PrePersist
    protected void onCreate() {
        if (fecha_creacion == null) {
            fecha_creacion = LocalDateTime.now();
        }
    }

    // Getters y Setters corregidos
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}