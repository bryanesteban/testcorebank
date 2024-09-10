package ec.com.corebank.banquito.models.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private float valor;

    @NotBlank
    @Column(name = "saldo", unique = true)
    @Size(min = 4, max = 30)
    private String saldo;

     @ManyToOne
    @JoinColumn(name = "numerocuenta") // Asegúrate de que este nombre coincida con la columna en la tabla Movimientos que actúa como clave foránea
    private Cuenta cuenta;

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

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

}
