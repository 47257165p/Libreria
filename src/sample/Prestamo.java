package sample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by 47257165p on 15/01/16.
 */
@Entity
public class Prestamo implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private Libro libro;
    private Socio socio;
    private Date fechaInicio;
    private Date fechaFinal;

    public Prestamo (Libro libro, Socio socio, Date fechaInicio, Date fechaFinal)
    {
        this.libro = libro;
        this.socio = socio;
        this. fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }
    public Prestamo()
    {

    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
