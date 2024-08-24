package ec.com.corebank.banquito.services.ServInterface;

import java.util.List;

import ec.com.corebank.banquito.models.DTO.PersonaDTO;
import ec.com.corebank.banquito.models.entities.Persona;

public interface PersonaInterface {

    List<PersonaDTO> findAll();

    Optional<ClientDTO> findByIdentificaction( String identification);
    List<ClientDTO> findByNameOrLast_name(String nameValue);
    
    PersonaDTO saveClient ( Persona client);

    Optional<PersonaDTO> update(Persona client, String idPersona);

    void remove(String identification );
}
