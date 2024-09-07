package ec.com.corebank.banquito.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Cliente")
public class Cliente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clienteid")
    private Long clienteid;

    @NotBlank
    @Column(name = "contrasena")
    @Size(min = 4, max = 30)
    private String contrasena;


    @Column(name = "estado")
    private Boolean estado;

    @OneToOne()
    @JoinColumn(name = "IDPERSONA", referencedColumnName = "IDPERSONA")
    private Persona persona;

    

    public Cliente() {
    }

    public Cliente(
        @NotBlank @Size(min = 4, max = 30) String nombre,
        @NotBlank @Size(min = 4, max = 20) String genero,
        @NotBlank int edad,
        @NotBlank @Size(min = 4, max = 20) String identificacion,
        @NotBlank @Size(min = 4, max = 60) String direccion,
        @NotBlank @Size(min = 4, max = 60) String telefono,
        Long clienteid,
        @NotBlank @Size(min = 4, max = 30) String contrasena,
        Boolean estado) {

        super(nombre, genero, edad, identificacion, direccion, telefono);
        this.clienteid = clienteid;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    // Getters and Setters

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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}