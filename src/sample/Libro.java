package sample;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by 47257165p on 15/01/16.
 */
public class Libro {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String titulo;
    private int numero;
    private String editorial;
    private int paginas;
    private LocalDate añoEdicion;

    public Libro (String titulo, int numero, String editorial, int paginas, LocalDate añoEdicion)
    {
        this.titulo = titulo;
        this.numero = numero;
        this.editorial = editorial;
        this. paginas = paginas;
        this.añoEdicion = añoEdicion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public LocalDate getAñoEdicion() {
        return añoEdicion;
    }

    public void setAñoEdicion(LocalDate añoEdicion) {
        this.añoEdicion = añoEdicion;
    }
}