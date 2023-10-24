package hibernate.Asignatura;

import hibernate.Profesor.Profesor;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "asignatura")
public class Asignatura implements Serializable {
    @Id
    @Column(name = "id")
    private  int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "horas")
    private int horas;

    @ManyToOne
    @JoinColumn(name = "idProfesor")
    private Profesor profesor;

    public Asignatura() {
    }

    public Asignatura(int id, String nombre, int horas, Profesor profesor) {
        this.id = id;
        this.nombre = nombre;
        this.horas = horas;
        this.profesor = profesor;
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

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Asignatura{\n" +
                "id = " + id + "\n" +
                "nombre = " + nombre + "\n" +
                "horas = " + horas + "\n" +
                '}';
    }
}
