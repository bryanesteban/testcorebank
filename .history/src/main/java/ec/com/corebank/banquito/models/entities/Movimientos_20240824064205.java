package ec.com.corebank.banquito.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Movimientos")
public class Movimientos {


    @NotBlank
    @Column(name = "fechaMovimiento")
    @Size(min = 4, max = 30)
    private String fechaMovimiento;

    @NotBlank
    @Column(name = "Movimiento", unique = true)
    @Size(min = 4, max = 30)
    private String Movimiento;

    @NotBlank
    @Column(name = "valor")
    @Size(min = 4, max = 30)
    private float valor;

    @NotBlank
    @Column(name = "saldo", unique = true)
    @Size(min = 4, max = 30)
    private String saldo;





}
