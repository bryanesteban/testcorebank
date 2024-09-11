package ec.com.corebank.banquito.models.DTO;

import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.models.entities.Cuenta;
import ec.com.corebank.banquito.models.entities.Movimientos;

public class MovimientosDTO {

    private long idmovimiento;
    private String fechaMovimiento ;
    private String cliente;
    private String numerocuenta;
    private String tipo;
    private String saldoInicial;
    private boolean estado;
    private String movimiento;
    private String saldo;
    
    
    
    public MovimientosDTO() {
    }






    public MovimientosDTO( String fechaMovimiento, String movimiento, String saldoInicial, String numerocuenta) {

        this.fechaMovimiento = fechaMovimiento;
        this.movimiento = movimiento;
        this.saldoInicial = saldoInicial;
        this.numerocuenta = numerocuenta;
    }



    public MovimientosDTO(long idmovimiento, String fechaMovimiento, String cliente, String numerocuenta, String tipo, String saldoInicial,
            boolean estado, String movimiento, String saldo) {
        this.idmovimiento = idmovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.cliente = cliente;
        this.numerocuenta = numerocuenta;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.movimiento = movimiento;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
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
                                    movimiento.getMovimiento(),
                                    movimiento.getSaldo()
        );

    }

}
