package ec.com.corebank.banquito;

import ec.com.corebank.banquito.models.entities.Cliente;
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
        cliente.setClienteid("testclienteid");
        cliente.setContrasena("testpassword");
        cliente.setEstado(true);
        // Aquí debes crear e insertar una Persona en la base de datos, ya que es requerida por Cliente.

        // Guardar el Cliente
        clienteRepository.save(cliente);

        // Buscar el Cliente
        Optional<Cliente> foundCliente = clienteRepository.findById(cliente.getId());

        // Verificar los resultados
        Assertions.assertTrue(foundCliente.isPresent());
        Assertions.assertEquals(cliente.getClienteid(), foundCliente.get().getClienteid());
    }
}
