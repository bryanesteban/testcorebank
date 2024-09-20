package ec.com.corebank.banquito.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtransaccion;


    @NotBlank
    @Column(name = "descripcion")
    @Size(max = 30)
    private String descripcion;


    @NotBlank
    @Column(name = "formula")
    @Size(max = 30)
    private String formula;


    public Transaccion() {
    }


    public Transaccion(Long idtransaccion, @NotBlank @Size(min = 4, max = 30) String descripcion,
            @NotBlank @Size(min = 4, max = 30) String formula) {
        this.idtransaccion = idtransaccion;
        this.descripcion = descripcion;
        this.formula = formula;
    }


    public Long getIdTransaccion() {
        return idtransaccion;
    }


    public void setIdTransaccion(Long idtransaccion) {
        this.idtransaccion = idtransaccion;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getFormula() {
        return formula;
    }


    public void setFormula(String formula) {
        this.formula = formula;
    }


    


}
