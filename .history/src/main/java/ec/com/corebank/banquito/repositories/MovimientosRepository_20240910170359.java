package ec.com.corebank.banquito.repositories;

import org.springframework.data.repository.CrudRepository;

import ec.com.corebank.banquito.models.DTO.MovimientosDTO;
import ec.com.corebank.banquito.models.entities.Movimientos;
import java.util.Optional;

public interface MovimientosRepository
    extends CrudRepository<Movimientos,Long> {

        Optional<MovimientosDTO> findByIdMovimiento(String idMovimiento);
}
