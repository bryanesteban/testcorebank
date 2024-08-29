package ec.com.corebank.banquito.repositories;

import org.springframework.data.repository.CrudRepository;

import ec.com.corebank.banquito.models.entities.Persona;
import java.util.List;


public interface PersonaRepository
    extends CrudRepository<Persona,Long> {

        Optional<Persona>  fList<Persona> findByIdentificacion(String identificacion);
}
