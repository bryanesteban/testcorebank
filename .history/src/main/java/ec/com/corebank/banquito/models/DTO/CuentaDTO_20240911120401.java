package ec.com.corebank.banquito.models.DTO;

import ec.com.corebank.banquito.models.entities.Cuenta;

public class CuentaDTO {

    private String numeroCuenta;
    private String clienteId;
    private String tipoCuenta;
    private String saldoinicial;
    private boolean estado;
    
    
    public CuentaDTO() {
    }



    public CuentaDTO(String numeroCuenta, String clienteId, String tipoCuenta, String saldoinicial, boolean estado) {
        this.numeroCuenta = numeroCuenta;
        this.clienteId = clienteId;
        this.tipoCuenta = tipoCuenta;
        this.saldoinicial = saldoinicial;
        this.estado = estado;
    }


    public String getClienteId() {
        return clienteId;
    }


    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
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


    public String getSaldoinicial() {
        return saldoinicial;
    }


    public void setSaldoinicial(String saldoinicial) {
        this.saldoinicial = saldoinicial;
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
                             cuenta.getCliente().getClienteid(),
                             cuenta.getTipoCuenta(),
                             cuenta.getSaldoinicial(),
                             cuenta.getEstado()

        );
    }
    


}
