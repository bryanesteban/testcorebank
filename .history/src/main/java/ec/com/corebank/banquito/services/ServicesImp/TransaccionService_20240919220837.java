package ec.com.corebank.banquito.services.ServicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.corebank.banquito.models.entities.Transaccion;
import ec.com.corebank.banquito.repositories.TransaccionRepository;
import ec.com.corebank.banquito.services.ServInterface.TransaccionServInterface;

@Service
public class TransaccionService implements TransaccionServInterface {


    @Autowired
    private TransaccionRepository transaccionRepository;


    @Override
    public Transaccion postSave(Transaccion trasaccion) {
        
        return transaccionRepository.save(trasaccion);
        
    }

}
