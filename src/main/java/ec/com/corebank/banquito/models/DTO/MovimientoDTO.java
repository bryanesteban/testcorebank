package ec.com.corebank.banquito.models.DTO;

import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.models.entities.Cuenta;
import ec.com.corebank.banquito.models.entities.Movimiento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDTO {

    private long idmovimiento;
    private LocalDate fechaMovimiento ;
    private String cliente;
    private String numerocuenta;
    private String tipomovimiento;
    private String saldoInicial;
    private boolean estado;
    private String valor;
    private String saldo;
    


    public static MovimientoDTO build (Cliente cliente, Cuenta cuenta, Movimiento movimiento)
    {
        if(cliente == null || cuenta == null || movimiento == null  ){
            throw new RuntimeException("Error en cunsulta de datos, Datos insuficientes");
        }

    

        return new MovimientoDTO(movimiento.getIdmovimiento(),
                                    movimiento.getFechamovimiento(),
                                    cliente.getNombre(),
                                    cuenta.getNumerocuenta(),
                                    movimiento.getTipomovimiento(),
                                    cuenta.getSaldoinicial(),
                                    cuenta.isEstado(),
                                    movimiento.getValor(),
                                    movimiento.getSaldo()
        );

    }

}
