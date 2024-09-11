package ec.com.corebank.banquito.services.ServicesImp;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.corebank.banquito.models.DTO.MovimientosDTO;
import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.models.entities.Cuenta;
import ec.com.corebank.banquito.models.entities.Movimientos;
import ec.com.corebank.banquito.repositories.ClienteRepository;
import ec.com.corebank.banquito.repositories.CuentaRespository;
import ec.com.corebank.banquito.repositories.MovimientosRepository;
import ec.com.corebank.banquito.services.ServInterface.MovimientosServInterface;

@Service
public class movimientoService implements MovimientosServInterface {

    @Autowired
    private MovimientosRepository movimientoRepository;


    @Override
    public List<MovimientosDTO> findAll() {
       
        List<Movimientos> movimientosLista = new ArrayList<>();
        List <MovimientosDTO> movimientos = new ArrayList<>();
        try{
            movimientosLista = (List<Movimientos>) movimientoRepository.findAll();

            movimientos = movimientosLista.stream().map(
            movimiento -> {
                Cuenta cuenta = movimiento.getCuentas().iterator().next();
                Cliente cliente  = cuenta.getCliente();
               
                return MovimientosDTO.build(cliente, cuenta, movimiento);
            }
           ).collect(Collectors.toList());


        }catch( Exception e){
            e.printStackTrace();
        }

        return movimientos;
    }

    @Override
    public Optional<MovimientosDTO> findByidMovimiento(String movimiento) {
        
        Optional<MovimientosDTO> movimientoDTO;

        try{
            movimiento = movimientoRepository
                         findByidMovimiento(movimiento);

        }catch(){

        }



    }

    @Override
    public MovimientosDTO saveMovimiento(Movimientos movimiento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveMovimiento'");
    }

    @Override
    public Optional<MovimientosDTO> updateMovimiento(Movimientos movimiento, String idMovimiento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMovimiento'");
    }

    @Override
    public void removeMovimiento(String idMovimiento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeMovimiento'");
    }


    
}
