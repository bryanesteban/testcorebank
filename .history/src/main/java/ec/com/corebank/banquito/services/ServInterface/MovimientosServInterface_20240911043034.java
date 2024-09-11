package ec.com.corebank.banquito.services.ServInterface;

import java.util.List;
import java.util.Optional;

import ec.com.corebank.banquito.models.DTO.MovimientosDTO;
import ec.com.corebank.banquito.models.entities.Movimientos;

public interface MovimientosServInterface {

     List<MovimientosDTO> findAll();

    MovimientosDTO findByidMovimiento( String idmovimiento);
    
    MovimientosDTO saveMovimiento ( MovimientosDTO movimiento);

    Optional<MovimientosDTO> updateMovimiento(Movimientos movimiento, String idMovimiento);

    void removeMovimiento(String idMovimiento );
}
