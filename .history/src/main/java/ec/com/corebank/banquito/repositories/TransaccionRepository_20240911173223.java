package ec.com.corebank.banquito.repositories;

import org.springframework.data.repository.CrudRepository;


import ec.com.corebank.banquito.models.entities.Transaccion;
import java.util.List;


public interface TransaccionRepository  extends CrudRepository<Transaccion,Long>  {

     List<Transaccion> findByDescripcion(String descripcion);



}
