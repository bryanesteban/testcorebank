package ec.com.corebank.banquito.services.ServInterface;

import java.util.List;
import java.util.Optional;

import ec.com.corebank.banquito.models.DTO.ClienteDTO;
import ec.com.corebank.banquito.models.entities.Cliente;

public interface ClienteSerInterface {

    List<ClienteDTO> findAll();

    Optional<ClienteDTO> findByIdClient( String idPersona);
    
    ClienteDTO saveClient ( Cliente cliente);

    Optional<ClienteDTO> update(Cliente cliente, String idPersona);

    void remove(String clienteId );
}
