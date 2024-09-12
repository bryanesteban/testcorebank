import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.models.entities.Persona;
import ec.com.corebank.banquito.repositories.ClienteRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@SpringBootTest  // Configura el contexto de Spring Boot
@DataJpaTest  // Configura el entorno para pruebas JPA
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ClienteRepositoryIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testSaveAndFindCliente() {
        // Crear un Cliente de prueba
        Cliente cliente = new Cliente();
        cliente.setClienteid("100100");
        cliente.setContrasena("sasa1234");
        cliente.setEstado(true);
        
        Persona persona = new Persona("Adal Montes", "Masculino", 35, "1744347526", "Tumbaco", "09528395821");
        cliente.setPersona(persona);
        
        // Guardar el Cliente
        clienteRepository.save(cliente);
        
        // Buscar el Cliente
        Optional<Cliente> foundCliente = clienteRepository.findByClienteid(cliente.getClienteid());
        
        // Verificar los resultados
        assertTrue(foundCliente.isPresent());
        assertEquals(cliente.getClienteid(), foundCliente.get().getClienteid());
    }
}
