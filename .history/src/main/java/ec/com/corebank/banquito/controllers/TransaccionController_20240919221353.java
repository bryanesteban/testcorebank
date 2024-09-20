package ec.com.corebank.banquito.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ec.com.corebank.banquito.models.entities.Transaccion;
import ec.com.corebank.banquito.services.ServicesImp.TransaccionService;

@RestController
@RequestMapping("/transaccion")
@CrossOrigin(originPatterns = "*")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;


    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody Transaccion transaccion, BindingResult result) {
        
        try {

            Transaccion savedTransaccion= transaccionService.postSave(transaccion);
            return new ResponseEntity<>(savedTransaccion, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
