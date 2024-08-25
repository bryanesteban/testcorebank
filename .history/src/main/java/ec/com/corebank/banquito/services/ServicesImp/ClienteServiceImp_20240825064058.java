package ec.com.corebank.banquito.services.ServicesImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.corebank.banquito.models.DTO.ClienteDTO;
import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.models.entities.Persona;
import ec.com.corebank.banquito.repositories.ClienteRepository;
import ec.com.corebank.banquito.repositories.PersonaRepository;
import ec.com.corebank.banquito.services.ServInterface.ClienteServInterface;

@Service
public class ClienteServiceImp implements ClienteServInterface {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<ClienteDTO> findAll() {
      List <Cliente> clientes = null;
        
      try {
        clientes = clienteRepository.findByEstadoTrue();

       } catch (Exception e) {
            e.printStackTrace();
       }
       

       return clientes
                .stream()
                .map(u -> ClienteDTO.build(u))
                .collect(Collectors.toList());
        
    }

    @Override
    public Optional<ClienteDTO> findByIdClient(String clienteId) {
        
        



        return clienteRepository
                .findByClienteid(clienteId)
                .map( u -> ClienteDTO.build(u));
    }

    @Override
    public ClienteDTO saveClient(Cliente cliente) {
    
        Persona persona = new Persona (
            cliente.getId(),
            cliente.getNombre(),
            cliente.getGenero(),
            cliente.getEdad(),
            cliente.getIdentificacion(),
            cliente.getDireccion(),
            cliente.getTelefono()
        );


        return ClienteDTO.build(clienteRepository.save(cliente));
        
        persona = personaRepository.save(persona);
        cliente.setPersona(persona);
        return clienteRepository.save(cliente);

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
