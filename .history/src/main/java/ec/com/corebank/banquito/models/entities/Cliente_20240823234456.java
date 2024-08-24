package ec.com.corebank.banquito.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Cliente {


     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @NotBlank
    @Column(name = "identificacion", unique = true)
    @Size(min = 4, max = 30)
    private String nombre;

    @NotBlank
    @Column(name = "genero")
    @Size(min = 4, max = 20)
    private String genero;
}
