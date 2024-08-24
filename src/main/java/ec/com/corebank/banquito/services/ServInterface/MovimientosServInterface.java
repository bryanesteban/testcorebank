package ec.com.corebank.banquito.services.ServInterface;

import java.util.List;
import java.util.Optional;

import ec.com.corebank.banquito.models.DTO.MovimientosDTO;
import ec.com.corebank.banquito.models.entities.Movimientos;

public interface MovimientosServInterface {

     List<MovimientosDTO> findAll();

    Optional<MovimientosDTO> findByNCuenta( Movimientos movimiento);
    
    MovimientosDTO saveCuenta ( Movimientos movimiento);

    Optional<MovimientosDTO> updateCuenta(Movimientos movimiento, String idMovimiento);

    void removeCuenta(String idMovimiento );
}
