package ec.com.corebank.banquito.models.DTO;

import ec.com.corebank.banquito.models.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private String identificacion;
    private String nombre;
    private String direccion;
    private String telefono;
    private String contrasena;
    private Boolean estado;

    
    public static ClienteDTO build(Cliente cliente){

        if( cliente == null){
            throw new RuntimeException("Debe pasar el entity Cliente!");
        }

        return new ClienteDTO(cliente.getIdentificacion(),
                              cliente.getNombre(),
                              cliente.getDireccion(),
                              cliente.getTelefono(),
                              cliente.getContrasena(),
                              cliente.getEstado());

    }

    

}
