package ec.com.corebank.banquito.services.ServInterface;

import java.util.List;
import java.util.Optional;

import ec.com.corebank.banquito.models.DTO.ClienteDTO;
import ec.com.corebank.banquito.models.entities.Cliente;

public interface ClienteServInterface {

    List<ClienteDTO> findAll();

    Optional<ClienteDTO> findByIdClient( Cliente cliente);
    
    ClienteDTO saveClient ( Cliente cliente);

    Optional<ClienteDTO> update(Cliente cliente, String clienteId);

    void remove(String clienteId );
}
