package ec.com.corebank.banquito.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersona")
    private Long idPersona;

    @NotBlank
    @Column(name = "nombre")
    @Size(min = 4, max = 50)
    private String nombre;

    @Column(name = "genero")
    @Size(min = 4, max = 20)
    private String genero;


    @Column(name = "edad")
    private int edad;

    @NotBlank
    @Column(name = "identificacion", unique = true)
    @Size(min = 4, max = 20)
    private String identificacion;

    @NotBlank
    @Column(name = "direccion")
    @Size(min = 4, max = 60)
    private String direccion;

    @NotBlank
    @Column(name = "telefono")
    @Size(min = 4, max = 60)
    private String telefono;

    public Persona() {
    }

    public Persona(
        @NotBlank @Size(min = 4, max = 30) String nombre,
        @NotBlank @Size(min = 4, max = 20) String genero,
        @NotBlank int edad,
        @NotBlank @Size(min = 4, max = 20) String identificacion,
        @NotBlank @Size(min = 4, max = 60) String direccion,
        @NotBlank @Size(min = 4, max = 60) String telefono) {

        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters and Setters

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
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
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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
}