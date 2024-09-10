package ec.com.corebank.banquito.models.DTO;

import ec.com.corebank.banquito.models.entities.Cuenta;

public class CuentaDTO {

    private String numeroCuenta;
    private String clienteId;
    private String tipoCuenta;
    private float saldo;
    private boolean estado;
    
    
    public CuentaDTO() {
    }


    public CuentaDTO(String numeroCuenta, String tipoCuenta, float saldo, boolean estado) {
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

    public static CuentaDTO build(Cuenta cuenta){

        if( cuenta == null){
            throw new RuntimeException("Debe pasar el entity Cuenta!");
        }

        return new CuentaDTO(cuenta.getNumeroCuenta(),
                             cuenta.getTipoCuenta(),
                             cuenta.getSaldo(),
                             cuenta.getEstado()

        );
    }
    


}
