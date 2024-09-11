package ec.com.corebank.banquito.services.ServicesImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.corebank.banquito.models.DTO.CuentaDTO;
import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.models.entities.Cuenta;
import ec.com.corebank.banquito.repositories.ClienteRepository;
import ec.com.corebank.banquito.repositories.CuentaRespository;
import ec.com.corebank.banquito.services.ServInterface.CuentaServInterface;


@Service
public class CuentaService implements CuentaServInterface {

    @Autowired
    private CuentaRespository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

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
                        .findByNumerocuenta(idCuenta)
                        .map(CuentaDTO::build);
                         
                    
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar la cuenta con ID: " + idCuenta + " - " + e.getMessage());

        }

        return cuentaDTO;
    }

    @Override
    @Transactional
    public CuentaDTO saveCuenta(CuentaDTO cuentaDTO) {
        try {
            Optional <Cuenta> cuentaValidacion = cuentaRepository.findByNumerocuenta(cuentaDTO.getNumeroCuenta());
            
            Cliente cliente = clienteRepository.findByClienteid(cuentaDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            System.out.println("Cliente:"+cliente.getClienteid());


            if(!cuentaValidacion.isPresent()){
                Cuenta cuenta = new Cuenta();
                cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
                cuenta.setCliente(cliente);
                cuenta.setEstado(cuentaDTO.isEstado());
                cuenta.setSaldo(cuentaDTO.getSaldo());
                cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());


                return CuentaDTO.build(cuentaRepository.save(cuenta));
            }
            return null;
        } catch (DataIntegrityViolationException e) {
        // Manejar violación de integridad de datos
        e.printStackTrace();
        throw new RuntimeException("Error de integridad de datos: " + e.getLocalizedMessage());
        } catch (Exception e) {
            // Manejar otras excepciones
            e.printStackTrace();
            throw new RuntimeException("Error al guardar cuenta: " + e.getLocalizedMessage());
        }
    }

    @Override
    @Transactional
    public Optional<CuentaDTO> updateCuenta(Cuenta cuenta, String numeroCuenta) {
        try {
            Optional<Cuenta> verificaCuenta = cuentaRepository.findByNumerocuenta(numeroCuenta);
            
            if(verificaCuenta.isPresent()){
                Cuenta cuentaDb = verificaCuenta.get();

                cuentaDb.setTipoCuenta(cuenta.getTipoCuenta());
                cuentaDb.setSaldo(cuentaDb.getSaldoinicial());
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
    @Transactional
    public void removeCuenta(String numeroCuenta) {
        try {
            Optional<Cuenta> verificaCuenta = cuentaRepository.findByNumerocuenta(numeroCuenta);
            if(verificaCuenta.isPresent()){
                cuentaRepository.delete(verificaCuenta.get());
            }else{
                    throw new RuntimeException("Cuenta con numero:"+ numeroCuenta+"no encontrada!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar la cuenta con numero: " + numeroCuenta + " - " + e.getMessage());
        }
    }

    
    
   




}
