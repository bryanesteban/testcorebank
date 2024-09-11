package ec.com.corebank.banquito.models.entities;


import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Movimientos")
public class Movimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmovimiento;


    @NotBlank
    @Column(name = "fechaMovimiento")
    @Size(min = 4, max = 30)
    private String fechamovimiento;

    @NotBlank
    @Column(name = "tipomovimiento")
    @Size(min = 4, max = 30)
    private String tipomovimiento;

    @NotBlank
    @Column(name = "valor")
    private String valor;

    @NotBlank
    @Column(name = "saldo")
    @Size(min = 4, max = 30)
    private String saldo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "numerocuenta", nullable = false, referencedColumnName = "numerocuenta")
    private Cuenta cuenta;

    public Movimientos() {
    }

    public Movimientos(Long idmovimiento, @NotBlank @Size(min = 4, max = 30) String fechamovimiento,
            @NotBlank @Size(min = 4, max = 30) String tipomovimiento, @NotBlank @Size(min = 4, max = 30) String valor,
            @NotBlank @Size(min = 4, max = 30) String saldo) {
        this.idmovimiento = idmovimiento;
        this.fechamovimiento = fechamovimiento;
        this.tipomovimiento = tipomovimiento;
        this.valor = valor;
        this.saldo = saldo;
    }

    
    public Movimientos(Long idmovimiento, @NotBlank @Size(min = 4, max = 30) String fechamovimiento,
            @NotBlank @Size(min = 4, max = 30) String tipomovimiento, String valor,
            @NotBlank @Size(min = 4, max = 30) String saldo, Cuenta cuenta) {
        this.idmovimiento = idmovimiento;
        this.fechamovimiento = fechamovimiento;
        this.tipomovimiento = tipomovimiento;
        this.valor = valor;
        this.saldo = saldo;
        this.cuenta = cuenta;
    }

    public Long getIdMovimiento() {
        return idmovimiento;
    }

    public void setIdMovimiento(Long idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public String getFechaMovimiento() {
        return fechamovimiento;
    }

    public void setFechaMovimiento(String fechamovimiento) {
        this.fechamovimiento = fechamovimiento;
    }

    public String getTipoMovimiento() {
        return tipomovimiento;
    }

    public void setTipoMovimiento(String tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

}
