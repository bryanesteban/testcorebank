package ec.com.corebank.banquito.services.ServicesImp;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.corebank.banquito.ErrorManagment.CustomException;
import ec.com.corebank.banquito.ErrorManagment.ResourceNotFoundException;
import ec.com.corebank.banquito.models.DTO.MovimientoDTO;
import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.models.entities.Cuenta;
import ec.com.corebank.banquito.models.entities.Movimiento;
import ec.com.corebank.banquito.models.entities.Transaccion;
import ec.com.corebank.banquito.repositories.CuentaRespository;
import ec.com.corebank.banquito.repositories.MovimientoRepository;
import ec.com.corebank.banquito.repositories.TransaccionRepository;
import ec.com.corebank.banquito.services.ServInterface.MovimientoInterface;

@Service
public class MovimientoServiceImp implements MovimientoInterface {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRespository cuentaRespository;


    @Autowired
    private TransaccionRepository transaccionRepository;


    @Override
    @Transactional(readOnly = true)
    public List<MovimientoDTO> findAll() {
       
        List<Movimiento> movimientosLista = new ArrayList<>();
        List <MovimientoDTO> movimientos = new ArrayList<>();



            movimientosLista = (List<Movimiento>) movimientoRepository.findAll();

            if(movimientosLista == null || movimientosLista.size() == 0)
            {
                throw new CustomException("Error al devolver los movimientos: ", HttpStatus.NOT_FOUND);
            }

            movimientos = movimientosLista.stream().map(
            movimiento -> {
                Cuenta cuenta = movimiento.getCuenta();
                Cliente cliente  = cuenta.getCliente();
               
                return MovimientoDTO.build(cliente, cuenta, movimiento);
            }
           ).collect(Collectors.toList());


        return movimientos;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovimientoDTO> findByidMovimiento(Long idmovimiento) {
        
        MovimientoDTO movimientoDTO = null;

            
            Optional<Movimiento> optionalMovimiento = movimientoRepository.findByIdmovimiento(idmovimiento);

            if( optionalMovimiento.isPresent()){
                
                Movimiento movimientoConsulta = optionalMovimiento.get();
    
                if(movimientoConsulta.getCuenta() == null){
                    throw new ResourceNotFoundException("No se encontró una cuenta asociada al movimiento ID: " + idmovimiento);
                }

                Cuenta cuenta = movimientoConsulta.getCuenta();
                Cliente cliente = cuenta.getCliente();

                movimientoDTO = MovimientoDTO.build(cliente, cuenta, movimientoConsulta);

                return Optional.of(movimientoDTO);
            }else {
                throw new CustomException("Ocurrió un error al obtener el movimiento ", HttpStatus.NOT_FOUND );
            }


       

    }


    @Override
    @Transactional(readOnly = true)
    public List<MovimientoDTO> findMovimientosByFechaRange(String fechaInicio, String fechaFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(fechaInicio, formatter);
        LocalDate endDate = LocalDate.parse(fechaFin, formatter);
        
        List<Movimiento> movimientosLista = movimientoRepository.findByFechamovimientoBetween(startDate, endDate);
        
        if(movimientosLista == null || movimientosLista.size() == 0)
            {
                throw new CustomException("No se encontraron movimientos", HttpStatus.NOT_FOUND );
            }

        return movimientosLista.stream()
            .map(movimiento -> {
                Cuenta cuenta = movimiento.getCuenta();
                Cliente cliente = cuenta.getCliente();
                return MovimientoDTO.build(cliente, cuenta, movimiento);
            })
            .collect(Collectors.toList());
    }
    

    @Override
    @Transactional
    public MovimientoDTO saveMovimiento(MovimientoDTO movimiento) {
        
        MovimientoDTO movimientoResultado = null;
        

                Optional<Movimiento> verificaMovimiento = movimientoRepository.findByIdmovimiento(movimiento.getIdmovimiento());
                Optional<Transaccion> optionalAccion = transaccionRepository.findByDescripcion(movimiento.getTipomovimiento());
                if(!verificaMovimiento.isPresent()){
                    Movimiento newmovimiento = new Movimiento();

                     Optional<Cuenta> optionalCuenta = cuentaRespository.findByNumerocuenta(movimiento.getNumerocuenta());
                     Cuenta cuentavinculada =  optionalCuenta.get();
                     Cliente clientevinculado = cuentavinculada.getCliente();
                     Transaccion accion = optionalAccion.get();
                  Long saldoMovimiento = Long.valueOf(cuentavinculada.getSaldoinicial()) +(Long.valueOf(accion.getFormula()))*Long.valueOf(movimiento.getValor());
                    if( saldoMovimiento >= 0 ){
                        
                        //Actualizacion de la cuenta
                        cuentavinculada.setSaldoinicial(String.valueOf(saldoMovimiento));
                        Cuenta operacion = cuentaRespository.save(cuentavinculada);
                        //Guardado del Movimiento
                        if(operacion.getSaldoinicial().equals(String.valueOf(saldoMovimiento)))
                        {
                            newmovimiento.setFechamovimiento(LocalDate.now());

                            newmovimiento.setCuenta(cuentavinculada);
                            newmovimiento.setTipomovimiento(movimiento.getTipomovimiento());
                            newmovimiento.setSaldo(String.valueOf(saldoMovimiento));
                            newmovimiento.setValor(movimiento.getValor());
                            Movimiento movimientoagregado = movimientoRepository.save(newmovimiento);
                            movimientoResultado = MovimientoDTO.build(clientevinculado, cuentavinculada, movimientoagregado);
                        }
                          
                    }else{
                        throw new CustomException("Saldo insuficiente para realizar la operación." , HttpStatus.NOT_FOUND);
                    }

                }
             return  movimientoResultado;  
    }

    @Override
    @Transactional
    public Optional<MovimientoDTO> updateMovimiento(Movimiento movimiento, Long idMovimiento) {
        
        Optional<MovimientoDTO> movimientoResultado = null;
        
            Optional<Movimiento> verificaMovimiento = movimientoRepository.findByIdmovimiento(idMovimiento);

            if(verificaMovimiento.isPresent()){
                Movimiento movimientobd = verificaMovimiento.get();
                Cuenta cuentabd = movimientobd.getCuenta();
                Cliente clientebd = cuentabd.getCliente();
                movimientobd.setTipomovimiento(movimiento.getTipomovimiento());

                Long saldoMovimiento = Long.valueOf(String.valueOf(cuentabd.getSaldoinicial())) + Long.valueOf(movimiento.getSaldo());
                movimientobd.setSaldo(String.valueOf(saldoMovimiento));
                movimientobd.setValor(movimiento.getValor());
                Movimiento movimientoagregado = movimientoRepository.save(movimientobd);
                movimientoResultado = Optional.of(MovimientoDTO.build(clientebd, cuentabd, movimientoagregado));

                return movimientoResultado;
            }else {
                throw new CustomException("Error al actualizar el Movimiento con numero:"+ idMovimiento, HttpStatus.NOT_FOUND);
            }



    }

    @Override
    @Transactional
    public void removeMovimiento(Long idMovimiento) {
        
            Optional<Movimiento> verificaMovimiento = movimientoRepository.findByIdmovimiento(idMovimiento);

            if(verificaMovimiento.isPresent()){
                movimientoRepository.delete(verificaMovimiento.get());
            }else{
                    throw new RuntimeException("Movimiento con numero: "+idMovimiento+",no encontrada!");
            }

    }



    
}
