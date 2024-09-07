package ec.com.corebank.banquito.models.DTO;

import ec.com.corebank.banquito.models.entities.Cliente;

public class ClienteDTO {

    private String identificacion;
    private String nombre;
    private String direccion;
    private String telefono;
    private String contrasena;
    private Boolean estado;


    



    



    public ClienteDTO(String identificacion, String nombre, String direccion, String telefono, String contrasena,
            Boolean estado) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.estado = estado;
    }



    public String getIdentificacion() {
        return identificacion;
    }



    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getDireccion() {
        return direccion;
    }



    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }



    public String getTelefono() {
        return telefono;
    }



    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }




  
    public static ClienteDTO build(Cliente cliente){

        if( cliente == null){
            throw new RuntimeException("Debe pasar el entity Cliente!");
        }

        return new ClienteDTO(cliente.getIdentificacion(), cliente.getNombre(), cliente.getDireccion(), cliente.getTelefono(), cliente.getContrasena(), cliente.getEstado());

    }

    

}
