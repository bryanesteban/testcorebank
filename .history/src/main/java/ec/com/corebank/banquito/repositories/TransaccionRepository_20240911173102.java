package ec.com.corebank.banquito.repositories;

import org.springframework.data.repository.CrudRepository;


import ec.com.corebank.banquito.models.entities.Transaccion;

public interface TransaccionRepository  extends CrudRepository<Transaccion,Long>  {

}
