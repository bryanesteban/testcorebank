package ec.com.corebank.banquito.repositories;


import org.springframework.data.repository.CrudRepository;



import ec.com.corebank.banquito.models.entities.Movimientos;
import java.util.Optional;
import java.util.List;
import java.time.LocalDate;

public interface MovimientosRepository
    extends CrudRepository<Movimientos,Long> {

        Optional<Movimientos> findByIdmovimiento(Long idMovimiento);

        List<Movimientos> findByFechamovimientoBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
