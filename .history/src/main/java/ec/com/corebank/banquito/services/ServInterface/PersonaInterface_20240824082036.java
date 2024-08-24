package ec.com.corebank.banquito.services.ServInterface;

import java.util.List;
import java.util.Optional;

import ec.com.corebank.banquito.models.DTO.PersonaDTO;
import ec.com.corebank.banquito.models.entities.Persona;

public interface PersonaInterface {

    List<PersonaDTO> findAll();

    Optional<PersonaDTO> findByIdClient( String idPersona);
    
    PersonaDTO saveClient ( Persona persona);

    Optional<PersonaDTO> update(Persona persona, String idPersona);

    void remove(String idPersona );
}
