package ec.com.corebank.banquito.models.entities;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Cuenta extends Cliente {


    @NotBlank
    @Column(name = "numeroCuenta", unique = true)
    @Size(min = 4, max = 30)
    private String numeroCuenta;

    @NotBlank
    @Column(name = "tipoCuenta")
    @Size(min = 4, max = 20)
    private String tipoCuenta;

    @NotBlank
    @Column(name = "saldo")
    @Size(min = 4, max = 20)
    private float saldo;

    @NotBlank
    @Column(name = "estado")
    @Size(min = 4, max = 20)
    private boolean estado;

    

}
