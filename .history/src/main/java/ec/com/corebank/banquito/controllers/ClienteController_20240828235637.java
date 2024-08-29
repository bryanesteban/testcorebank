package ec.com.corebank.banquito.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ec.com.corebank.banquito.models.DTO.ClienteDTO;
import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.services.ServInterface.ClienteServInterface;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(originPatterns = "*")
public class ClienteController {

    @Autowired
    private ClienteServInterface clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        try {
            List<ClienteDTO> clientes = clienteService.findAll();
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable("clienteId") String clienteId) {
        try {
            Optional<ClienteDTO> cliente = clienteService.findByIdClient(clienteId);
            return cliente.map(ResponseEntity::ok)
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody Cliente cliente, BindingResult result) {
        
        try {
            System.out.println("cliente:"+cliente);
            ClienteDTO savedCliente = clienteService.saveClient(cliente);
            return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // if(result.hasErrors()){
        //     return validation(result);
        // }
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable("clienteId") String clienteId, 
                                                     @RequestBody Cliente cliente) {
        try {
            Optional<ClienteDTO> updatedCliente = clienteService.update(cliente, clienteId);
            return updatedCliente.map(ResponseEntity::ok)
                                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("clienteId") String clienteId) {
        try {
            clienteService.remove(clienteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //  private ResponseEntity<?> validation(BindingResult result) {
    //     Map<String, String> errors = new HashMap<>();

    //     result.getFieldErrors().forEach(err -> {
    //         errors.put(err.getField(), "El campo "+err.getField()+" "+err.getDefaultMessage());
    //     });        
    //     return ResponseEntity.badRequest().body(errors);
    // }
}