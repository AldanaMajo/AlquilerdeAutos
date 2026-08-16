package AlquilerdeAutos.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCliente")
    private Integer idCliente;

    @ManyToOne
    @JoinColumn(name = "IdUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "DUI", length = 10, unique = true)
    private String dui;

    @Column(name = "Licencia", length = 30, unique = true)
    private String licencia;

    @Column(name = "Telefono", length = 20)
    private String telefono;

    @Column(name = "Direccion", length = 200)
    private String direccion;

    public Cliente() {
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}