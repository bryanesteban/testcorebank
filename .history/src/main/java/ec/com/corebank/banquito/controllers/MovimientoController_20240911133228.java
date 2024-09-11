package ec.com.corebank.banquito.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.corebank.banquito.models.DTO.MovimientosDTO;
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
            // TODO: handle exception
        }
    }



}
