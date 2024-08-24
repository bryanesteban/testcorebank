package ec.com.corebank.banquito.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="Cuenta")
public class Cuenta {


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


    public Cuenta() {}

    public Cuenta (
        String numeroCuenta,
        String tipoCuenta,
        float saldo,
        boolean estado){

        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.estado = estado;
    

    }



    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Getters y setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    

}
