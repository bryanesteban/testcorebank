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
import jakarta.persistence.OneToMany;
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
    private Long idPersona;

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
    private int Edad;


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

    public Persona() {
    
    }


    public Persona(
        @NotBlank @Size(min = 4, max = 30) String nombre,
        @NotBlank @Size(min = 4, max = 20) String genero,
        @NotBlank @Size(min = 4, max = 20) int edad,
        @NotBlank @Size(min = 4, max = 20) String identificacion,
        @NotBlank @Size(min = 4, max = 60) String direccion,
        @NotBlank @Size(min = 4, max = 60) String telefono) {
        
        this.nombre = nombre;
        this.genero = genero;
        Edad = edad;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
    }


    public Long getId() {
        return idPersona;
    }


    public void setId(Long idPersona) {
        this.idPersona = idPersona;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getGenero() {
        return genero;
    }


    public void setGenero(String genero) {
        this.genero = genero;
    }


    public int getEdad() {
        return Edad;
    }


    public void setEdad(int edad) {
        Edad = edad;
    }


    public String getIdentificacion() {
        return identificacion;
    }


    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }


    public String getDireccion() {
        return direccion;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }




    @OneToMany
    @JoinTable(
        name = "persona_cliente",
        joinColumns = @JoinColumn (name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id"),
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
   


}
