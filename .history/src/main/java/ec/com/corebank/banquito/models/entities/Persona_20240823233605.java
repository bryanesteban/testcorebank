package ec.com.corebank.banquito.models.entities;

import java.util.List;

import org.hibernate.annotations.ManyToAny;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre")
    @Size(min = 4, max = 30)
    private String nombre;

    @NotBlank
    @Column(name = "genero")
    @Size(min = 4, max = 20)
    private String genero;

    @NotBlank
    @Column(name = "Edad")
    @Size(min = 4, max = 20)
    private String Edad;


    @NotBlank
    @Column(name = "identificacion", unique = true)
    @Size(min = 4, max = 20)
    private String identificacion;

    @NotBlank
    @Column(name = "direccion", unique = true)
    @Size(min = 4, max = 60)
    private String direccion;

    @NotBlank
    @Column(name = "telefono", unique = true)
    @Size(min = 4, max = 60)
    private String telefono;




    @ManyToMany
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn (name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id"),
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
   


}
