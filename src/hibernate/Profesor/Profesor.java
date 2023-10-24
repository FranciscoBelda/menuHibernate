package hibernate.Profesor;

import hibernate.Asignatura.Asignatura;
import hibernate.CorreoElectronico.CorreoElectronico;
import hibernate.Direccion.Direccion;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "profesor")
public class Profesor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Direccion direccion;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    private Set<Asignatura> asignaturas;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    @OrderColumn(name = "idx")
    private List<CorreoElectronico> correoElectronicos;

    public Profesor() {
    }

    public Profesor(String nombre, String apellido1, String apellido2) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public Profesor(int id, String nombre, String apellido1, String apellido2) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Set<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Set<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public List<CorreoElectronico> getCorreoElectronicos() {
        return correoElectronicos;
    }

    public void setCorreoElectronicos(List<CorreoElectronico> correoElectronicos) {
        this.correoElectronicos = correoElectronicos;
    }

    @Override
    public String toString() {
        return "Profesor{\n" +
                "id = " + id + "\n"+
                "nombre = " + nombre + "\n"+
                "apellido1 = " + apellido1 + "\n"+
                "apellido2 = " + apellido2 + "\n"+
                direccion+ "\n" +
                asignaturas+ "\n" +
                correoElectronicos + "\n" +
                '}';
    }
}
