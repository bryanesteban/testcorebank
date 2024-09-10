package ec.com.corebank.banquito.models.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="Cuenta")
public class Cuenta {


    @Id
    @NotBlank
    @Column(name = "numerocuenta", unique = true)
    @Size(min = 4, max = 30)
    private String numerocuenta;

    @NotBlank
    @Column(name = "tipocuenta")
    @Size(min = 4, max = 20)
    private String tipocuenta;

    @NotBlank
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


    @ManyToOne
    @JoinColumn(name = "clienteid")
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    private List<Movimientos> movimientos;


    public List<Movimientos> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimientos> movimientos) {
        this.movimientos = movimientos;
    }

    

}
