import org.junit.jupiter.api.Test;
import ec.com.corebank.banquito.models.entities.Persona;
import ec.com.corebank.banquito.models.entities.Cliente;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testClienteConstructorAndGettersSetters() {
        // Crear un objeto Persona
        Persona persona = new Persona("Hades Polydegmon", "Masculino", 30, "1234567890", "Campos Eliseos", "535-1234");

        // Crear un objeto Cliente usando solo Persona
        Cliente cliente = new Cliente();
        cliente.setPersona(persona);
        cliente.setClienteid("cliente123");
        cliente.setContrasena("securePassword");
        cliente.setEstado(true);

        // Verificar que los getters devuelvan los valores correctos
        assertEquals("cliente123", cliente.getClienteid());
        assertEquals("securePassword", cliente.getContrasena());
        assertTrue(cliente.getEstado());
        assertEquals(persona, cliente.getPersona());

        // Verificar que los setters funcionen correctamente
        cliente.setClienteid("cliente456");
        cliente.setContrasena("newPassword");
        cliente.setEstado(false);

        assertEquals("cliente456", cliente.getClienteid());
        assertEquals("newPassword", cliente.getContrasena());
        assertFalse(cliente.getEstado());
    }
}
