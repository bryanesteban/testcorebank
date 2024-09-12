package ec.com.corebank.banquito.repositories;


import org.springframework.data.repository.CrudRepository;



import ec.com.corebank.banquito.models.entities.Movimientos;
import java.util.Optional;
import java.util.List;

public interface MovimientosRepository
    extends CrudRepository<Movimientos,Long> {

        Optional<Movimientos> findByIdmovimiento(Long idMovimiento);

        List<Movimientos> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
