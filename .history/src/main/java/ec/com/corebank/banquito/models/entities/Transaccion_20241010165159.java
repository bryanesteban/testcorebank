package ec.com.corebank.banquito.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Transaccion")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

}
