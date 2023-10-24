package hibernate.Direccion;

import hibernate.Profesor.Profesor;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "direccion")
public class Direccion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "calle")
    private String calle;
    @Column(name = "poblacion")
    private String poblacion;
    @Column(name = "provincia")
    private String provincia;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Profesor profesor;

    public Direccion() {
    }

    public Direccion(int id, String calle, String poblacion, String provincia) {
        this.id = id;
        this.calle = calle;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

    public Direccion(String calle, String poblacion, String provincia) {
        this.calle = calle;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Direccion{\n" +
                "id = " + id +"\n"+
                "calle = " + calle + "\n"+
                "poblacion = " + poblacion + "\n"+
                "provincia = " + provincia + "\n"+
                '}';
    }
}
