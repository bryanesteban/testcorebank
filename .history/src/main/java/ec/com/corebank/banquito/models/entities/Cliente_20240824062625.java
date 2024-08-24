package ec.com.corebank.banquito.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Cliente")
public class Cliente extends Persona{


    @NotBlank
    @Column(name = "clienteid", unique = true)
    @Size(min = 4, max = 20)
    private String clienteid;

    @NotBlank
    @Column(name = "contrasena")
    @Size(min = 4, max = 30)
    private String contrasena;

    @NotBlank
    @Column(name = "estado")
    private Boolean estado;

    public Cliente() {
    }

     // Constructor con parámetros
     public Cliente(Persona persona, String clienteid, String contrasena, Boolean estado) {
     super(persona.getNombre(),
           persona.getGenero(),
           persona.getEdad(),
           persona.getIdentificacion(),
           persona.getDireccion(),
           persona.getTelefono());

     this.clienteid = clienteid;
     this.contrasena = contrasena;
     this.estado = estado;
     }

     public Cliente( String clienteid, String contrasena, Boolean estado) {
   
        this.clienteid = clienteid;
        this.contrasena = contrasena;
        this.estado = estado;
        }

    public String getClienteid() {
        return clienteid;
    }

    public void setClienteid(String clienteid) {
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


    // @ManyToMany
    // @JoinTable(
    //     name = "users_roles",
    //     joinColumns = @JoinColumn (name="user_id"),
    //     inverseJoinColumns = @JoinColumn(name="role_id"),
    //     uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})

}
