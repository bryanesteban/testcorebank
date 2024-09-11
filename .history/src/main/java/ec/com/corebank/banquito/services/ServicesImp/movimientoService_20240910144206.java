package ec.com.corebank.banquito.services.ServicesImp;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

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

    @Autowired 
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRespository cuentaRespository;

    @Override
    public List<MovimientosDTO> findAll() {
       
        List<Movimientos> movimientosLista = new ArrayList<>();

        try{
            movimientosLista = (List<Movimientos>) movimientoRepository.findAll();

           return movimientosLista.stream().map(
            movimiento -> {
                Cuenta cuenta = movimiento.getCuentas().iterator().next();
                Cliente cliente  = cuenta.getCliente();



            MovimientosDTO movimientoDTO = new MovimientosDTO();  
            movimientoDTO.setIdmovimiento(movimiento.getIdMovimiento());
            movimientoDTO.setFechaMovimiento(movimiento.getFechaMovimiento());
            movimientoDTO.setNumerocuenta(cuenta.getNumeroCuenta());
            movimientoDTO.setTipo(cuenta.getTipoCuenta());
            movimientoDTO.setIdmovimiento(movimiento.getIdMovimiento());
            movimientoDTO.setSaldoInicial(String.valueOf(cuenta.getSaldo()));
            movimientoDTO.setEstado(cuenta.getEstado());
            movimientoDTO.setMovimiento(movimiento.getMovimiento());
            movimientoDTO.setSaldo(movimiento.getSaldo());
            }
           )


        }catch( Exception e){
            e.printStackTrace();
        }

       




    }

    @Override
    public Optional<MovimientosDTO> findByidMovimiento(Movimientos movimiento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByidMovimiento'");
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
