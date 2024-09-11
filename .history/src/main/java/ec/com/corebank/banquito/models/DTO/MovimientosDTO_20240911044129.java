package ec.com.corebank.banquito.models.DTO;

import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.models.entities.Cuenta;
import ec.com.corebank.banquito.models.entities.Movimientos;

public class MovimientosDTO {

    private long idmovimiento;
    private String fechaMovimiento ;
    private String cliente;
    private String numerocuenta;
    private String tipomovimiento;
    private String saldoInicial;
    private boolean estado;
    private String valor;
    private String saldo;
    
    
    
    public MovimientosDTO() {
    }






    public MovimientosDTO( String fechaMovimiento, String tipomovimiento, String saldoInicial, String numerocuenta) {

        this.fechaMovimiento = fechaMovimiento;
        this.tipomovimiento = tipomovimiento;
        this.saldoInicial = saldoInicial;
        this.numerocuenta = numerocuenta;
        this.valor = valor;
    }



    public MovimientosDTO(long idmovimiento, String fechaMovimiento, String cliente, String numerocuenta, String tipo, String saldoInicial,
            boolean estado, String movimiento, String saldo) {
        this.idmovimiento = idmovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.cliente = cliente;
        this.numerocuenta = numerocuenta;
        this.tipomovimiento = tipomovimiento;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.valor = valor;
        this.saldo = saldo;
    }






    public long getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(long idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(String numerocuenta) {
        this.numerocuenta = numerocuenta;
    }

    public String getTipoMovimiento() {
        return tipomovimiento;
    }

    public void setTipoMovimiento(String tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    public String getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(String saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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

    public static MovimientosDTO build (Cliente cliente, Cuenta cuenta, Movimientos movimiento)
    {
        if(cliente == null || cuenta == null || movimiento == null  ){
            throw new RuntimeException("Error en cunsulta de datos, Datos insuficientes");
        }


        return new MovimientosDTO(movimiento.getIdMovimiento(),
                                    movimiento.getFechaMovimiento(),
                                    cliente.getNombre(),
                                    cuenta.getNumeroCuenta(),
                                    cuenta.getTipoCuenta(),
                                    String.valueOf(cuenta.getSaldo()),
                                    cuenta.getEstado(),
                                    movimiento.getTipoMovimiento(),
                                    movimiento.getSaldo()
        );

    }

}
