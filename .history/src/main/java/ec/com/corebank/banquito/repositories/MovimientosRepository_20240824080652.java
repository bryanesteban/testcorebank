package ec.com.corebank.banquito.repositories;

import org.springframework.data.repository.CrudRepository;

import ec.com.corebank.banquito.models.entities.Movimientos;

public interface MovimientosRepository
    extends CrudRepository<Movimientos,Long> {

}
