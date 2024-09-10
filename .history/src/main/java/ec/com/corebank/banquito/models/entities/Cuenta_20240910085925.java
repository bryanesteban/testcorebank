package ec.com.corebank.banquito.models.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="cuenta")
public class Cuenta {


    @Id
    @NotBlank
    @Column(name = "numerocuenta", nullable = false, length = 30)
    private String numerocuenta;

    @NotBlank
    @Column(name = "tipocuenta")
    @Size(min = 4, max = 20)
    private String tipocuenta;

    @Column(name = "saldo")
    private float saldo;


    @Column(name = "estado")
    private boolean estado;


    public Cuenta() {}

    public Cuenta (
        String numerocuenta,
        String tipoCuenta,
        float saldo,
        boolean estado){

        this.numerocuenta = numerocuenta;
        this.tipocuenta = tipoCuenta;
        this.saldo = saldo;
        this.estado = estado;
    

    }

    public Cuenta (
        String numerocuenta,
        Cliente cliente,
        String tipoCuenta,
        float saldo,
        boolean estado){

        this.numerocuenta = numerocuenta;
        this.cliente = cliente;
        this.tipocuenta = tipoCuenta;
        this.saldo = saldo;
        this.estado = estado;
    

    }


    public String getNumeroCuenta() {
        return numerocuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numerocuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipocuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipocuenta = tipoCuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "clienteid", nullable = false, referencedColumnName = "username")
    private Cliente cliente;


    @ManyToMany
    @JoinTable(
        name = "CUENTAXMOVIMIENTO",
        joinColumns = @JoinColumn(name = "NUMEROCUENTA"),
        inverseJoinColumns = @JoinColumn(name = "IDMOVIMIENTO")
    )private List<Movimientos> movimientos;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public List<Movimientos> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimientos> movimientos) {
        this.movimientos = movimientos;
    }

    

}
