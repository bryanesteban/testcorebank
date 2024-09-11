package ec.com.corebank.banquito.models.DTO;

public class MovimientosDTO {

    private Long idMovimiento;
    private String fechaMovimiento ;
    private String movimiento;
    private Long valor;
    private Long saldo;
    
    
    
    public MovimientosDTO() {
    }



    public MovimientosDTO(Long idMovimiento, String fechaMovimiento, String movimiento, Long valor, Long saldo) {
        this.idMovimiento = idMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.movimiento = movimiento;
        this.valor = valor;
        this.saldo = saldo;
    }



    public Long getIdMovimiento() {
        return idMovimiento;
    }



    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }



    public String getFechaMovimiento() {
        return fechaMovimiento;
    }



    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }



    public String getMovimiento() {
        return movimiento;
    }



    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }



    public Long getValor() {
        return valor;
    }



    public void setValor(Long valor) {
        this.valor = valor;
    }



    public Long getSaldo() {
        return saldo;
    }



    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }


    



}
