package ec.com.corebank.banquito.models.DTO;

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



    public MovimientosDTO(String fechaMovimiento, String cliente, String numerocuenta, String tipo, String saldoInicial,
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

    








    


    



}
