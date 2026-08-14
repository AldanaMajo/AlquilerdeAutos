package AlquilerdeAutos.Modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;


@Entity
@Table(name ="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer Id;

    @NotBlank(message = "El Nombre es Requerido" )
    private String Nombre;

    @NotBlank(message = "El Apellido es Requerido" )
    private String Apellido;

    @NotBlank(message = "El Gmail es Requerido" )
    private String Gmail;

    @NotBlank(message = "El Contrasena es Requerido" )
    private String Contrasena;

    private LocalDateTime FechaDeRegristro;

    @ManyToOne
    @JoinColumn(name = "IdRol")
    private Rol rol;

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

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public LocalDateTime getFechaDeRegristro() {
        return FechaDeRegristro;
    }

    public void setFechaDeRegristro(LocalDateTime fechaDeRegristro) {
        FechaDeRegristro = fechaDeRegristro;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
