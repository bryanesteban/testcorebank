package ec.com.corebank.banquito.repositories;


import org.springframework.data.repository.CrudRepository;

import ec.com.corebank.banquito.models.entities.Persona;
import java.util.List;
import java.util.Optional;


public interface PersonaRepository
    extends CrudRepository<Persona,Long> {
        
        
        List<Persona> findByIdentificacion(String identification);
        Optional<Client> findByIdentification(String identification);
}
