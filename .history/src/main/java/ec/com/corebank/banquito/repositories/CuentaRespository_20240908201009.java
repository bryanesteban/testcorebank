package ec.com.corebank.banquito.repositories;
import org.springframework.data.repository.CrudRepository;

import ec.com.corebank.banquito.models.entities.Cuenta;
import java.util.List;

public interface CuentaRespository 
     extends CrudRepository<Cuenta,String>{


}
