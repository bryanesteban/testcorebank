package ec.com.corebank.banquito.services.ServicesImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.corebank.banquito.models.DTO.ClienteDTO;
import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.repositories.ClienteRepository;
import ec.com.corebank.banquito.services.ServInterface.ClienteServInterface;

@Service
public class ClienteServiceImp implements ClienteServInterface {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> findAll() {
        List <Cliente> clientes = clienteRepository.findByStatusTrue();

        return clientes
                .stream()
                .map(u -> ClienteDTO.build(u))
                .collect(Collectors.toList());
        
    }

    @Override
    public Optional<ClienteDTO> findByIdClient(Cliente cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByIdClient'");
    }

    @Override
    public ClienteDTO saveClient(Cliente cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveClient'");
    }

    @Override
    public Optional<ClienteDTO> update(Cliente cliente, String clienteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void remove(String clienteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
    

}
