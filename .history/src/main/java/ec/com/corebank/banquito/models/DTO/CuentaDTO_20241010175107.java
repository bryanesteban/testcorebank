package ec.com.corebank.banquito.models.DTO;

import ec.com.corebank.banquito.models.entities.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {

    private String numeroCuenta;
    private String clienteId;
    private String tipoCuenta;
    private String saldoinicial;
    private boolean estado;

    public static CuentaDTO build(Cuenta cuenta){

        if( cuenta == null){
            throw new RuntimeException("Debe pasar el entity Cuenta!");
        }

        return new CuentaDTO(cuenta.getNumerocuenta(),
                             cuenta.getCliente().getClienteid(),
                             cuenta.getTipocuenta(),
                             cuenta.getSaldoinicial(),
                             cuenta.isEstado()

        );
    }
    


}
