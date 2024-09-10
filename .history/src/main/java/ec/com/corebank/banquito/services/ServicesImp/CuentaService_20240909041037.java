package ec.com.corebank.banquito.services.ServicesImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ec.com.corebank.banquito.models.DTO.CuentaDTO;
import ec.com.corebank.banquito.models.entities.Cuenta;
import ec.com.corebank.banquito.repositories.CuentaRespository;
import ec.com.corebank.banquito.services.ServInterface.CuentaServInterface;



public class CuentaService implements CuentaServInterface {

    @Autowired
    private CuentaRespository cuentaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CuentaDTO> findAll() {
        
        List<Cuenta> cuentas = new ArrayList<>();

        try{
            cuentas = (List<Cuenta>) cuentaRepository.findAll();
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return cuentas
            .stream()
            .map(u -> CuentaDTO.build(u))
            .collect(Collectors.toList());
    
    
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CuentaDTO> findByNCuenta(String idCuenta) {
      
        Optional<CuentaDTO> cuentaDTO;

        try {
            cuentaDTO = cuentaRepository
                        .findByNumeroCuenta(idCuenta)
                        .map(CuentaDTO::build);
                         
                    
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar la cuenta con ID: " + idCuenta + " - " + e.getMessage());

        }

        return cuentaDTO;
    }

    @Override
    public CuentaDTO saveCuenta(Cuenta cuenta) {
        try {
            Optional <Cuenta> cuentaValidacion = cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta());

            if(!cuentaValidacion.isPresent()){
                return CuentaDTO.build(cuentaRepository.save(cuenta));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error el cliente ya existe:" + e.getLocalizedMessage());
        }
    }

    @Override
    public Optional<CuentaDTO> updateCuenta(Cuenta cuenta, String numeroCuenta) {
        try {
            Optional<Cuenta> verifyCuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);
            
            if(verifyCuenta.isPresent()){
                Cuenta cuentaDb = verifyCuenta.get();

                cuentaDb.setTipoCuenta(cuenta.getTipoCuenta());
                cuentaDb.setSaldo(cuentaDb.getSaldo());
                cuentaDb.setEstado(cuenta.getEstado());


                cuentaRepository.save(cuentaDb);
                
                return Optional.of(CuentaDTO.build(cuentaDb));
            }else {
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la cuenta con numero:"+ numeroCuenta+ " - " + e.getMessage());
        }
    }

    @Override
    public void removeCuenta(String numeroCuenta) {
        try {
            Optional<Cuenta> verifyCuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);
            if(verifyCuenta.isPresent()){
                cuentaRepository.delete(verifyCuenta.get());
            }else{
                    throw new RuntimeException("Cuenta con numero:"+ numeroCuenta+"no encontrada!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar la cuenta con numero: " + numeroCuenta + " - " + e.getMessage());
        }
    }

    
    
   




}
