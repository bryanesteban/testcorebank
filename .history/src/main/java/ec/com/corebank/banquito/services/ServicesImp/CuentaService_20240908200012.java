package ec.com.corebank.banquito.services.ServicesImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ec.com.corebank.banquito.models.DTO.CuentaDTO;
import ec.com.corebank.banquito.models.entities.Cuenta;
import ec.com.corebank.banquito.repositories.CuentaRespository;
import ec.com.corebank.banquito.services.ServInterface.CuentaServInterface;
import jakarta.transaction.Transactional;


public class CuentaService implements CuentaServInterface {

    @Autowired
    private CuentaRespository cuentaRepository;

    @Override
    public List<CuentaDTO> findAll() {
        
        List<Cuenta> cuentas = new ArrayList<>();

        try{
            cuenta = cuentaRepository.findAll();
        }catch(Exception e){
            e.printStackTrace();
        }
    
    
    
    }

    @Override
    public Optional<CuentaDTO> findByNCuenta(Cuenta cuenta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNCuenta'");
    }

    @Override
    public CuentaDTO saveCuenta(Cuenta cuenta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCuenta'");
    }

    @Override
    public Optional<CuentaDTO> updateCuenta(Cuenta cuenta, String numeroCuenta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCuenta'");
    }

    @Override
    public void removeCuenta(String numeroCuenta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeCuenta'");
    }

    
    
   




}
