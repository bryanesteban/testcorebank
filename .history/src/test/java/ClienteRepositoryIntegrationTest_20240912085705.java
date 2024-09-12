package ec.com.corebank.banquito;

import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.models.entities.Persona;
import ec.com.corebank.banquito.repositories.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;

@DataJpaTest
@ComponentScan(basePackages = "ec.com.corebank.banquito") // Asegúrate de escanear el paquete correcto
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
        Persona persona = new Persona("Adal montes","masculino", 35, "1744347526","tumbaco","09528395821");

        cliente.setPersona(persona);
        // Guardar el Cliente
        clienteRepository.save(cliente);

        // Buscar el Cliente
        Optional<Cliente> foundCliente = clienteRepository.findById(cliente.getIdPersona());

        // Verificar los resultados
        Assertions.assertTrue(foundCliente.isPresent());
        Assertions.assertEquals(cliente.getClienteid(), foundCliente.get().getClienteid());
    }
}
