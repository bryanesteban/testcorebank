package ec.com.corebank.banquito.models.entities;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
     public Cliente(String nombre, String genero, int edad, String identificacion, String direccion, String telefono,
     String clienteid, String contrasena, Boolean estado) {
     super(nombre, genero, edad, identificacion, direccion, telefono);
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


}
