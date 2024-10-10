package ec.com.corebank.banquito.services.ServicesImp;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.corebank.banquito.config.ManagmentException;
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
        try{
            movimientosLista = (List<Movimiento>) movimientoRepository.findAll();

            movimientos = movimientosLista.stream().map(
            movimiento -> {
                Cuenta cuenta = movimiento.getCuenta();
                Cliente cliente  = cuenta.getCliente();
               
                return MovimientoDTO.build(cliente, cuenta, movimiento);
            }
           ).collect(Collectors.toList());


        }catch( Exception e){
            e.printStackTrace();
        }

        return movimientos;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovimientoDTO> findByidMovimiento(Long idmovimiento) {
        
        MovimientoDTO movimientoDTO = null;

        try{
            
            Optional<Movimiento> optionalMovimiento = movimientoRepository.findByIdmovimiento(idmovimiento);

            if( optionalMovimiento.isPresent()){
                
                Movimiento movimientoConsulta = optionalMovimiento.get();
    
                if(movimientoConsulta.getCuenta() == null){
                    throw new RuntimeException("No se encontró una cuenta asociada al movimiento ID: " + idmovimiento);
                }

                Cuenta cuenta = movimientoConsulta.getCuenta();
                Cliente cliente = cuenta.getCliente();

                movimientoDTO = MovimientoDTO.build(cliente, cuenta, movimientoConsulta);
            }
        }catch (NumberFormatException e) {
            System.out.println("Error al convertir los valores numéricos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ocurrió un error al obtener el movimiento: " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.of(movimientoDTO);

    }


    @Override
    @Transactional(readOnly = true)
    public List<MovimientoDTO> findMovimientosByFechaRange(String fechaInicio, String fechaFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(fechaInicio, formatter);
        LocalDate endDate = LocalDate.parse(fechaFin, formatter);
        
        List<Movimiento> movimientosLista = movimientoRepository.findByFechamovimientoBetween(startDate, endDate);
        
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
        
        try{
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
                            newmovimiento.setFechaMovimiento(LocalDate.now());
                            
                            newmovimiento.setCuenta(cuentavinculada);
                            newmovimiento.setTipomovimiento(movimiento.getTipomovimiento());
                            newmovimiento.setSaldo(String.valueOf(saldoMovimiento));
                            newmovimiento.setValor(movimiento.getValor());
                            Movimiento movimientoagregado = movimientoRepository.save(newmovimiento);
                            movimientoResultado = MovimientoDTO.build(clientevinculado, cuentavinculada, movimientoagregado);
                        }
                          
                    }else{
                        throw new ManagmentException("Saldo insuficiente para realizar la operación.");
                    }

                }
             return  movimientoResultado;  

        }catch (ManagmentException e) {
            // Propagar la excepción de saldo insuficiente
            throw e;
        }
        catch (DataIntegrityViolationException e) {
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
    public Optional<MovimientoDTO> updateMovimiento(Movimiento movimiento, Long idMovimiento) {
        
        Optional<MovimientoDTO> movimientoResultado = null;
        
        try {
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
            }

            return movimientoResultado;
        }  catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el Movimiento con numero:"+ idMovimiento+ " - " + e.getMessage());
        }

    }

    @Override
    @Transactional
    public void removeMovimiento(Long idMovimiento) {
        try {
            Optional<Movimiento> verificaMovimiento = movimientoRepository.findByIdmovimiento(idMovimiento);

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


    public static String getCurrentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        return today.format(formatter);
    }

    
}
