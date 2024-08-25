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
        
        Optional<ClienteDTO> clienteDTO;
    
        try {
            clienteDTO = clienteRepository
                    .findByClienteid(clienteId)
                    .map(ClienteDTO::build);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar el cliente con ID: " + clienteId + " - " + e.getMessage());
        }
        
        return clienteDTO;
    }

    @Override
    public ClienteDTO saveClient(Cliente cliente) {
    
        try {
            Persona persona = new Persona(
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getGenero(),
                    cliente.getEdad(),
                    cliente.getIdentificacion(),
                    cliente.getDireccion(),
                    cliente.getTelefono()
            );
            
            persona = personaRepository.save(persona);
            cliente.setPersona(persona);
            return ClienteDTO.build(clienteRepository.save(cliente));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el cliente: " + e.getMessage());
        }

    }

    @Override
    public Optional<ClienteDTO> update(Cliente cliente, String clienteId) {
        Optional<Cliente> existingClienteOpt = clienteRepository.findByClienteid(clienteId);

        if (existingClienteOpt.isPresent()) {
            Cliente existingCliente = existingClienteOpt.get();
            
            // Actualizar datos del cliente
            existingCliente.setClienteid(cliente.getClienteid());
            existingCliente.setContrasena(cliente.getContrasena());
            existingCliente.setEstado(cliente.getEstado());

            // Actualizar datos de la persona asociada
            Persona persona = existingCliente.getPersona();
            if (persona != null) {
                persona.setNombre(cliente.getNombre());
                persona.setGenero(cliente.getGenero());
                persona.setEdad(cliente.getEdad());
                persona.setIdentificacion(cliente.getIdentificacion());
                persona.setDireccion(cliente.getDireccion());
                persona.setTelefono(cliente.getTelefono());
                personaRepository.save(persona);
            }

            Cliente updatedCliente = clienteRepository.save(existingCliente);
            return Optional.of(ClienteDTO.build(updatedCliente));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void remove(String clienteId) {
        Optional<Cliente> clienteOpt = clienteRepository.findByClienteid(clienteId);

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            clienteRepository.delete(cliente);
            // También se puede eliminar la persona asociada si es necesario
            if (cliente.getPersona() != null) {
                personaRepository.delete(cliente.getPersona());
            }
        } else {
            throw new RuntimeException("Cliente no encontrado para el ID: " + clienteId);
        }
    }
    

}
