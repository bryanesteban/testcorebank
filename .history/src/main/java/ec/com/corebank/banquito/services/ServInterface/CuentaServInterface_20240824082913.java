package ec.com.corebank.banquito.services.ServInterface;

import java.util.List;
import java.util.Optional;

import ec.com.corebank.banquito.models.DTO.CuentaDTO;
import ec.com.corebank.banquito.models.entities.Cuenta;

public interface CuentaServInterface {


    List<CuentaDTO> findAll();

    Optional<CuentaDTO> findByIdClient( Cuenta cuenta);
    
    CuentaDTO saveClient ( Cuenta cuenta);

    Optional<CuentaDTO> update(Cuenta cuenta, String clienteId);

    void remove(String clienteId );
}
