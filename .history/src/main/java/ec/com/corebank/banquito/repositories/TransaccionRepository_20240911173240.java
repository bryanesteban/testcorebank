package ec.com.corebank.banquito.repositories;

import org.apache.el.stream.Optional;
import org.springframework.data.repository.CrudRepository;


import ec.com.corebank.banquito.models.entities.Transaccion;
import java.util.List;


public interface TransaccionRepository  extends CrudRepository<Transaccion,Long>  {

     Optional<Transaccion> findByDescripcion(String descripcion);



}
