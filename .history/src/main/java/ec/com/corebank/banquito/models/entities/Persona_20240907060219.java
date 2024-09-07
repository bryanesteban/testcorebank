package ec.com.corebank.banquito.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PERSONA")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPERSONA")
    private Integer idPersona;

    @NotBlank
    @Column(name = "NOMBRE", length = 50, nullable = false)
    @Size(min = 4, max = 50)
    private String nombre;

    @Column(name = "GENERO", length = 20)
    @Size(min = 4, max = 20)
    private String genero;

    @Column(name = "EDAD")
    private Integer edad;

    @NotBlank
    @Column(name = "IDENTIFICACION", length = 20, nullable = false, unique = true)
    @Size(min = 4, max = 20)
    private String identificacion;

    @NotBlank
    @Column(name = "DIRECCION", length = 60, nullable = false)
    @Size(min = 4, max = 60)
    private String direccion;

    @NotBlank
    @Column(name = "TELEFONO", length = 60, nullable = false)
    @Size(min = 4, max = 60)
    private String telefono;

    public Persona() {}

    // Constructor con parámetros y getters/setters
}
