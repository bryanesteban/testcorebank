package ec.com.corebank.banquito.services.ServicesImp;

import java.util.List;
import java.util.Optional;

import ec.com.corebank.banquito.models.DTO.ClienteDTO;
import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.services.ServInterface.ClienteServInterface;

public class ClienteServiceImp implements ClienteServInterface {

    @Override
    public List<ClienteDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
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
