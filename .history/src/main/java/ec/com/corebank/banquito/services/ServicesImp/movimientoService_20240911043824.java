package ec.com.corebank.banquito.services.ServicesImp;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.corebank.banquito.models.DTO.CuentaDTO;
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
    private CuentaRespository cuentaRespository;

    @Override
    public List<MovimientosDTO> findAll() {
       
        List<Movimientos> movimientosLista = new ArrayList<>();
        List <MovimientosDTO> movimientos = new ArrayList<>();
        try{
            movimientosLista = (List<Movimientos>) movimientoRepository.findAll();

            movimientos = movimientosLista.stream().map(
            movimiento -> {
                Cuenta cuenta = movimiento.getCuenta();
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
    @Transactional(readOnly = true)
    public MovimientosDTO findByidMovimiento(String idmovimiento) {
        
        MovimientosDTO movimientoDTO = null;

        try{
            
            Optional<Movimientos> optionalMovimiento = movimientoRepository.findByIdMovimiento(idmovimiento);

            if( optionalMovimiento.isPresent()){
                
                Movimientos movimientoConsulta = optionalMovimiento.get();
    
                if(movimientoConsulta.getCuenta() == null){
                    throw new RuntimeException("No se encontró una cuenta asociada al movimiento ID: " + idmovimiento);
                }

                Cuenta cuenta = movimientoConsulta.getCuenta();
                Cliente cliente = cuenta.getCliente();

                movimientoDTO = MovimientosDTO.build(cliente, cuenta, movimientoConsulta);
            }
        }catch (NumberFormatException e) {
            System.out.println("Error al convertir los valores numéricos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ocurrió un error al obtener el movimiento: " + e.getMessage());
            e.printStackTrace();
        }

        return movimientoDTO;

    }

    @Override
    @Transactional
    public MovimientosDTO saveMovimiento(MovimientosDTO movimiento) {
        
        MovimientosDTO movimientoResultado = null;
        
        try{
                Optional<Movimientos> verificaMovimiento = movimientoRepository.findByIdMovimiento(String.valueOf(movimiento.getIdmovimiento()));

                if(!verificaMovimiento.isPresent()){
                    Movimientos newmovimiento = new Movimientos();

                     Optional<Cuenta> optionalCuenta = cuentaRespository.findByNumerocuenta(movimiento.getNumerocuenta());
                     Cuenta cuentavinculada =  optionalCuenta.get();
                     Cliente clientevinculado = cuentavinculada.getCliente();
                  Long saldoMovimiento = Long.valueOf(String.valueOf(cuentavinculada.getSaldo())) + Long.valueOf(movimiento.getValor());
                    if( saldoMovimiento < 0 ){
                        newmovimiento.setFechaMovimiento(movimiento.getFechaMovimiento());
                        newmovimiento.setCuenta(cuentavinculada);
                        newmovimiento.setTipoMovimiento(movimiento.getTipoMovimiento());
                        newmovimiento.setSaldo(String.valueOf(saldoMovimiento));
                        newmovimiento.setValor(movimiento.getValor());
                        Movimientos movimientoagregado = movimientoRepository.save(newmovimiento);
                        movimientoResultado = MovimientosDTO.build(clientevinculado, cuentavinculada, movimientoagregado);
                    }

                }
             return  movimientoResultado;  

        }catch (DataIntegrityViolationException e) {
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
    public Optional<MovimientosDTO> updateMovimiento(Movimientos movimiento, String idMovimiento) {
        
        Optional<MovimientosDTO> movimientoResultado = null;
        
        try {
            Optional<Movimientos> verificaMovimiento = movimientoRepository.findByIdMovimiento(idMovimiento);

            if(verificaMovimiento.isPresent()){
                Movimientos movimientobd = verificaMovimiento.get();
                Cuenta cuentabd = movimientobd.getCuenta();
                Cliente clientebd = cuentabd.getCliente();
                movimientobd.setFechaMovimiento(movimiento.getFechaMovimiento());
                movimientobd.setTipoMovimiento(movimiento.getTipoMovimiento());

                Long saldoMovimiento = Long.valueOf(String.valueOf(cuentabd.getSaldo())) + Long.valueOf(movimiento.getSaldo());
                movimientobd.setSaldo(String.valueOf(saldoMovimiento));
                movimientobd.setValor(movimiento.getValor());
                Movimientos movimientoagregado = movimientoRepository.save(movimientobd);
                movimientoResultado = Optional.of(MovimientosDTO.build(clientebd, cuentabd, movimientoagregado));
            }

            return movimientoResultado;
        }  catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el Movimiento con numero:"+ idMovimiento+ " - " + e.getMessage());
        }

    }

    @Override
    @Transactional
    public void removeMovimiento(String idMovimiento) {
        try {
            Optional<Movimientos> verificaMovimiento = movimientoRepository.findByIdMovimiento(idMovimiento);

            if(verificaMovimiento.isPresent()){
                movimientoRepository.delete(verificaMovimiento.get());
            }else{
                    throw new RuntimeException("Movimiento con numero: "+idMovimiento+"no encontrada!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar el movimiento con numero: " + idMovimiento + " - " + e.getMessage());
        }
    }


    
}
