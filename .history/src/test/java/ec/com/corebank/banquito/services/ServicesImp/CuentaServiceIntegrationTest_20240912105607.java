package ec.com.corebank.banquito.services.ServicesImp;

import ec.com.corebank.banquito.models.DTO.CuentaDTO;
import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.models.entities.Cuenta;
import ec.com.corebank.banquito.repositories.ClienteRepository;
import ec.com.corebank.banquito.repositories.CuentaRespository;
import ec.com.corebank.banquito.services.ServicesImp.CuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CuentaServiceIntegrationTest {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRespository cuentaRepository;

    private Cliente cliente;


    @Test
    @Transactional
    public void testSaveAndFindCuenta() {
        // Crear un DTO de cuenta para prueba
        CuentaDTO cuentaDTO = new CuentaDTO("123456", cliente.getClienteid(), "ahorro", "1000", true);

        // Guardar la cuenta
        CuentaDTO savedCuenta = cuentaService.saveCuenta(cuentaDTO);

        // Verificar que la cuenta se guardó correctamente
        assertNotNull(savedCuenta);
        assertEquals(cuentaDTO.getNumeroCuenta(), savedCuenta.getNumeroCuenta());

        // Buscar la cuenta
        Optional<CuentaDTO> foundCuenta = cuentaService.findByNCuenta(cuentaDTO.getNumeroCuenta());

        // Verificar los resultados
        assertTrue(foundCuenta.isPresent());
        assertEquals(cuentaDTO.getNumeroCuenta(), foundCuenta.get().getNumeroCuenta());
    }

    

   
}
