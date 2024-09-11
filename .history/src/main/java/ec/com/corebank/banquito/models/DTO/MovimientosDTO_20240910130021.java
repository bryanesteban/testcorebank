package ec.com.corebank.banquito.models.DTO;

public class MovimientosDTO {


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






    


    



}
