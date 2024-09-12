package ec.com.corebank.banquito.services.ServicesImp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.corebank.banquito.filters.EncryptionService;
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


    @Autowired
    private EncryptionService encryptServ;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
      
        List <Cliente> clientes = new ArrayList<>();
        
      try {
        clientes = clienteRepository.findByEstadoTrue();

       } catch (Exception e) {
            e.printStackTrace();
       }
       

       return clientes
                .stream()
                .map(u -> ClienteDTO.build(decryptCliente(u)))
                .collect(Collectors.toList());
        
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> findByIdClient(String clienteId) {
        
        Optional<ClienteDTO> clienteDTO;
    
        try {
            clienteDTO = clienteRepository
                    .findByClienteid(clienteId)
                    .map(u -> {
                       return ClienteDTO.build(decryptCliente(u));
                    });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar el cliente con ID: " + clienteId + " - " + e.getMessage());
        }
        
        return clienteDTO;
    }

    @Override
    @Transactional
    public ClienteDTO saveClient(Cliente cliente) {
        try {
            Optional <Persona> personaValidation = personaRepository.findByIdentificacion(cliente.getIdentificacion());
            
            if(!personaValidation.isPresent()){
                
                return ClienteDTO.build(decryptCliente(clienteRepository.save(encryptCliente(cliente))));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error el cliente ya existe: " + e.getMessage());
        }

    }

    @Override
    @Transactional
    public Optional<ClienteDTO> update(Cliente cliente, String clienteId) {
        try {
            //Busca cliente
            Optional<Cliente> clienteOpt = clienteRepository.findByClienteid(clienteId);
            if (clienteOpt.isPresent()) {
                Cliente existingCliente = decryptCliente(clienteOpt.get());
                // Actualiza los campos del cliente existente con los valores del nuevo cliente.
                existingCliente.setNombre(cliente.getNombre());
                existingCliente.setGenero(cliente.getGenero());
                existingCliente.setEdad(cliente.getEdad());
                existingCliente.setIdentificacion(cliente.getIdentificacion());
                existingCliente.setDireccion(cliente.getDireccion());
                existingCliente.setTelefono(cliente.getTelefono());
                existingCliente.setContrasena(cliente.getContrasena());
                existingCliente.setEstado(cliente.getEstado());
                // Guarda los cambios
                clienteRepository.save(encryptCliente(existingCliente));
                return Optional.of(ClienteDTO.build(decryptCliente(existingCliente)));
            } else {
                return Optional.empty(); // O lanza una excepción indicando que el cliente no fue encontrado.
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el cliente con ID: " + clienteId + " - " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void remove(String clienteId) {
        try {
            Optional<Cliente> clienteOpt = clienteRepository.findByClienteid(clienteId);
            if (clienteOpt.isPresent()) {
                clienteRepository.delete(clienteOpt.get());
            } else {
                
                throw new RuntimeException("Cliente con ID: " + clienteId + " no encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar el cliente con ID: " + clienteId + " - " + e.getMessage());
        }
    }
    

    //Encripta clientes 
    public Cliente  encryptCliente (Cliente cliente){
        Cliente clienteenc = new Cliente();
        try {
            clienteenc.setIdPersona(cliente.getIdPersona());
            clienteenc.setIdentificacion(cliente.getIdentificacion());
            clienteenc.setNombre(encryptServ.encrypt(cliente.getNombre()));
            clienteenc.setTelefono(encryptServ.encrypt(cliente.getTelefono()));
            clienteenc.setDireccion(encryptServ.encrypt(cliente.getDireccion()));
            clienteenc.setContrasena(cliente.getContrasena());
            clienteenc.setEstado(cliente.getEstado());
            clienteenc.setGenero(cliente.getGenero());
            clienteenc.setEdad(cliente.getEdad());
            clienteenc.setClienteid(cliente.getClienteid());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clienteenc;

    } 
    //Desencripta clientes
    public Cliente decryptCliente (Cliente cliente) {
        Cliente clientedec = new Cliente();
        try {
            clientedec.setIdentificacion(cliente.getIdentificacion());
            clientedec.setNombre(encryptServ.decrypt(cliente.getNombre()));
            clientedec.setTelefono(encryptServ.decrypt(cliente.getTelefono()));
            clientedec.setDireccion(encryptServ.decrypt(cliente.getDireccion()));
            clientedec.setContrasena(cliente.getContrasena());
            clientedec.setEstado(cliente.getEstado());
            clientedec.setGenero(cliente.getGenero());
            clientedec.setEdad(cliente.getEdad());
            clientedec.setClienteid(cliente.getClienteid());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientedec;
    }

}
