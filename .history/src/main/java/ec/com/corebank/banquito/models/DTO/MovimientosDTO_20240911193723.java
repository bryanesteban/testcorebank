package ec.com.corebank.banquito.models.DTO;

import ec.com.corebank.banquito.models.entities.Cliente;
import ec.com.corebank.banquito.models.entities.Cuenta;
import ec.com.corebank.banquito.models.entities.Movimientos;

public class MovimientosDTO {

    private long idmovimiento;
    private LocalDate fechaMovimiento ;
    private String cliente;
    private String numerocuenta;
    private String tipomovimiento;
    private String saldoInicial;
    private boolean estado;
    private String valor;
    private String saldo;
    
    
    
    public MovimientosDTO() {
    }



    public MovimientosDTO(String numerocuenta, String tipomovimiento, String valor) {

        this.numerocuenta = numerocuenta;
        this.tipomovimiento = tipomovimiento;
        this.valor = valor;
    }



    public MovimientosDTO(long idmovimiento, LocalDate fechaMovimiento, String cliente, String numerocuenta, String tipomovimiento, String saldoInicial,
            boolean estado , String valor, String saldo) {
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



    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }



    public void setFechaMovimiento(LocalDate fechaMovimiento) {
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



    public String getTipomovimiento() {
        return tipomovimiento;
    }



    public void setTipomovimiento(String tipomovimiento) {
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
                                    movimiento.getTipomovimiento(),
                                    cuenta.getSaldoinicial(),
                                    cuenta.getEstado(),
                                    movimiento.getValor(),
                                    movimiento.getSaldo()
        );

    }

}
