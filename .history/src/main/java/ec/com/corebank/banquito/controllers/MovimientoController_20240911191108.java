package ec.com.corebank.banquito.controllers;


import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.corebank.banquito.config.ManagmentException;
import ec.com.corebank.banquito.models.DTO.ErrorResponse;
import ec.com.corebank.banquito.models.DTO.MovimientosDTO;
import ec.com.corebank.banquito.models.entities.Movimientos;
import ec.com.corebank.banquito.services.ServInterface.MovimientosServInterface;

@RestController
@RequestMapping("/movimientos")
@CrossOrigin(originPatterns = "*")
public class MovimientoController {


    @Autowired
    private MovimientosServInterface movimientoService;




    @GetMapping
    public ResponseEntity<?> getMovimientos(){
        try {
            List<MovimientosDTO> movimientos = movimientoService.findAll();
            return new ResponseEntity<>(movimientos, HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idMovimientos}")
    public ResponseEntity<?> getBusquedaMovimiento(@PathVariable("idMovimientos") Long idMovimientos){
        try {
            Optional<MovimientosDTO> movimiento = movimientoService.findByidMovimiento(idMovimientos);
            return movimiento.map(ResponseEntity::ok)
                                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


    @PostMapping
    public ResponseEntity<?> crearMovimiento(@RequestBody MovimientosDTO movimiento, BindingResult result)
    {
        System.out.println("tipo de movimiento:"+movimiento.getTipomovimiento());

        if(result.hasErrors()){
            return validation(result);
        }


        try {
            MovimientosDTO guardarmovimiento = movimientoService.saveMovimiento(movimiento);
            return new ResponseEntity<>(guardarmovimiento, HttpStatus.CREATED);
        } catch (ManagmentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Saldo insuficiente", e.getMessage()));
        }catch (Exception e) {
            e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idMovimientos}")
    public ResponseEntity<?> actualizarMovimiento (@PathVariable("idMovimientos") Long idMovimientos, @RequestBody Movimientos movimiento )
    {
     
        try {
            Optional<MovimientosDTO> updateMovimiento = movimientoService.updateMovimiento(movimiento, idMovimientos);
            return updateMovimiento.map(ResponseEntity::ok)
                                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
       
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{idMovimientos}")
    public ResponseEntity<?> borrarMovimiento(@PathVariable("idMovimientos") Long idMovimientos)
    {
        try {
            movimientoService.removeMovimiento(idMovimientos);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo "+err.getField()+" "+err.getDefaultMessage());
        });        
        return ResponseEntity.badRequest().body(errors);
    }


}
