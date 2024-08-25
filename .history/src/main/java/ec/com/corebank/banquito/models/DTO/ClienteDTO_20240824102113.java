package ec.com.corebank.banquito.models.DTO;

import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.models.entities.Persona;

public class ClienteDTO {

    private String clienteid;
    private String contrasena;
    private Boolean estado;

    private Persona persona;


    
    public ClienteDTO(String clienteid, String contrasena, Boolean estado) {
        this.clienteid = clienteid;
        this.contrasena = contrasena;
        this.estado = estado;
    }



    public ClienteDTO(String clienteid, String contrasena, Boolean estado, Persona persona) {
        this.clienteid = clienteid;
        this.contrasena = contrasena;
        this.estado = estado;
        this.persona = persona;
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



    public Persona getPersona() {
        return persona;
    }



    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public static ClienteDTO build(Cliente cliente, Persona persona){

        if( cliente == null){
            throw new RuntimeException("Debe pasar el entity Cliente!");
        }

        return new  ClienteDTO(cliente.getClienteid(),
                               cliente.getContrasena(),
                               cliente.getEstado(),
                               persona);

    }

    public static ClienteDTO build(Cliente cliente){

        if( cliente == null){
            throw new RuntimeException("Debe pasar el entity Cliente!");
        }

        return new  ClienteDTO(cliente.getClienteid(),
                               cliente.getContrasena(),
                               cliente.getEstado());

    }

    

}
