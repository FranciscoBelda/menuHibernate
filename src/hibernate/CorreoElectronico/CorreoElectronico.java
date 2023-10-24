package hibernate.CorreoElectronico;

import hibernate.Profesor.Profesor;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "correoelectronico")
public class CorreoElectronico implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "correo")
    private String correo;

    @ManyToOne
    @JoinColumn(name = "idProfesor")
    private Profesor profesor;

    public CorreoElectronico() {
    }

    public CorreoElectronico(int id, String correo, Profesor profesor) {
        this.id = id;
        this.correo = correo;
        this.profesor = profesor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "CorreoElectronico{\n" +
                "id = " + id + "\n"+
                "correo = " + correo + "\n"+
                '}';
    }
}
