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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ec.com.corebank.banquito.models.DTO.CuentaDTO;
import ec.com.corebank.banquito.models.entities.Cuenta;
import ec.com.corebank.banquito.services.ServInterface.CuentaServInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/cuentas")
@CrossOrigin(originPatterns = "*")
public class CuentaController {



    @Autowired
    private CuentaServInterface  cuentaService;


    @GetMapping
    public ResponseEntity<?> getCuentas() {
            try {
                List<CuentaDTO> cuentas = cuentaService.findAll();
                return new ResponseEntity<>(cuentas, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            }
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<?> getBusquedaCuenta(@PathVariable("numeroCuenta") String numeroCuenta ) {
        try {
            Optional<CuentaDTO> cuenta = cuentaService.findByNCuenta(numeroCuenta);
            return cuenta.map(ResponseEntity::ok)
                            .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> crearCuenta(@RequestBody CuentaDTO cuenta, BindingResult result) {
       
        if(result.hasErrors()){
            return validation(result);
        }

        try{
            CuentaDTO guardarcuenta = cuentaService.saveCuenta(cuenta);
            return new ResponseEntity<>(guardarcuenta, HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<?> actualizarCuenta(@PathVariable("numeroCuenta") String numeroCuenta, @RequestBody Cuenta cuenta) {
        
        try {
            Optional<CuentaDTO> updateCuenta = cuentaService.updateCuenta(cuenta, numeroCuenta);
            return updateCuenta.map(ResponseEntity::ok)
                                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<?> borrarCuenta(@PathVariable("numeroCuenta") String numeroCuenta){
        try {
            cuentaService.removeCuenta(numeroCuenta);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
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
