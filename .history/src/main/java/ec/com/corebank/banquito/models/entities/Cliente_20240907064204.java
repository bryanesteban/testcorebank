package ec.com.corebank.banquito.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "CLIENTE")
public class Cliente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENTEID")
    private Long clienteid;

    @NotBlank
    @Column(name = "CONTRASENA", length = 30, nullable = false)
    @Size(min = 4, max = 30)
    private String contrasena;

    @NotBlank
    @Column(name = "ESTADO", length = 20, nullable = false)
    private Boolean estado; // Esto es un String porque en la base de datos es varchar(20)

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "IDPERSONA", referencedColumnName = "IDPERSONA")
    private Persona persona;
    public Cliente() {}

    public Cliente(
        @NotBlank @Size(min = 4, max = 30) String nombre,
        @NotBlank @Size(min = 4, max = 20) String genero,
        @NotBlank Integer edad,
        @NotBlank @Size(min = 4, max = 20) String identificacion,
        @NotBlank @Size(min = 4, max = 60) String direccion,
        @NotBlank @Size(min = 4, max = 60) String telefono,
        @NotBlank @Size(min = 4, max = 30) String contrasena,
        @NotBlank @Size(min = 4, max = 20) Boolean estado) {

        super(nombre, genero, edad, identificacion, direccion, telefono);
        this.contrasena = contrasena;
        this.estado = estado;
    }


    public Cliente(
        @NotBlank @Size(min = 4, max = 20) String identificacion,
        @NotBlank @Size(min = 4, max = 30) String nombre,
        @NotBlank @Size(min = 4, max = 60) String direccion,
        @NotBlank @Size(min = 4, max = 60) String telefono,
        @NotBlank @Size(min = 4, max = 30) String contrasena,
        @NotBlank @Size(min = 4, max = 20) Boolean estado

        ) {

        super(nombre, identificacion, direccion, telefono);
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public Long getClienteid() {
        return clienteid;
    }

    public void setClienteid(Long clienteid) {
        this.clienteid = clienteid;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    

    // Getters y setters
}