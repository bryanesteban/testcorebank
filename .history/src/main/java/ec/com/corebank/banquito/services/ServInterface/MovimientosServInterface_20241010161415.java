package ec.com.corebank.banquito.services.ServInterface;

import java.util.List;
import java.util.Optional;

import ec.com.corebank.banquito.models.DTO.MovimientosDTO;
import ec.com.corebank.banquito.models.entities.Movimiento;

public interface MovimientosServInterface {

     List<MovimientosDTO> findAll();

    Optional<MovimientosDTO> findByidMovimiento( Long idmovimiento);
    
    MovimientosDTO saveMovimiento ( MovimientosDTO movimiento);

    Optional<MovimientosDTO> updateMovimiento(Movimiento movimiento, Long idMovimiento);

    void removeMovimiento(Long idMovimiento );

    List<MovimientosDTO> findMovimientosByFechaRange(String fechaInicio, String fechaFin);
}
