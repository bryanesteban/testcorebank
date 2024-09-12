package ec.com.corebank.banquito.models.entities;

import org.junit.jupiter.api.Test;

import ec.com.corebank.banquito.models.entities.Persona;
import ec.com.corebank.banquito.models.entities.Cliente;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testClienteConstructorAndGettersSetters() {
        // Crear un objeto Cliente
        Persona persona = new Persona("John Doe", "Male", 30, "1234567890", "123 Main St", "555-1234");
        Cliente cliente = new Cliente(
                persona.getNombre(),
                persona.getGenero(),
                persona.getEdad(),
                persona.getIdentificacion(),
                persona.getDireccion(),
                persona.getTelefono(),
                "cliente123",
                "securePassword",
                true
        );
        cliente.setPersona(persona);

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
