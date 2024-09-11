package ec.com.corebank.banquito.models.entities;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Movimientos")
public class Movimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimiento;


    @NotBlank
    @Column(name = "fechaMovimiento")
    @Size(min = 4, max = 30)
    private String fechaMovimiento;

    @NotBlank
    @Column(name = "movimiento", unique = true)
    @Size(min = 4, max = 30)
    private String movimiento;

    @NotBlank
    @Column(name = "valor")
    private String valor;

    @NotBlank
    @Column(name = "saldo", unique = true)
    @Size(min = 4, max = 30)
    private String saldo;

    @ManyToMany(mappedBy = "movimientos")
    private Set<Cuenta> cuentas;

    public Movimientos() {
    }

    public Movimientos(Long idMovimiento, @NotBlank @Size(min = 4, max = 30) String fechaMovimiento,
            @NotBlank @Size(min = 4, max = 30) String movimiento, @NotBlank @Size(min = 4, max = 30) float valor,
            @NotBlank @Size(min = 4, max = 30) String saldo) {
        this.idMovimiento = idMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.movimiento = movimiento;
        this.valor = valor;
        this.saldo = saldo;
    }

    
    public Movimientos(Long idMovimiento, @NotBlank @Size(min = 4, max = 30) String fechaMovimiento,
            @NotBlank @Size(min = 4, max = 30) String movimiento, float valor,
            @NotBlank @Size(min = 4, max = 30) String saldo, Set<Cuenta> cuentas) {
        this.idMovimiento = idMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.movimiento = movimiento;
        this.valor = valor;
        this.saldo = saldo;
        this.cuentas = cuentas;
    }

    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public Set<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(Set<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

}
