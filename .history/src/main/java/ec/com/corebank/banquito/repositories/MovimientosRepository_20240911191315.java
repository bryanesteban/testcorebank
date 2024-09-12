package ec.com.corebank.banquito.repositories;

import org.hibernate.mapping.List;
import org.springframework.data.repository.CrudRepository;


import ec.com.corebank.banquito.models.entities.Movimientos;
import java.util.Optional;

public interface MovimientosRepository
    extends CrudRepository<Movimientos,Long> {

        Optional<Movimientos> findByIdmovimiento(Long idMovimiento);

        List<Movimientos> findByFechaBetween(startDate, endDate);
}
