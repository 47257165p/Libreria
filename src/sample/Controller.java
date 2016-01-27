package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    public MenuItem altaLibro;
    public TextField altaLibroTitulo;
    public TextField altaLibroEjemplares;
    public TextField altaLibroPaginas;
    public TextField altaLibroEditorial;
    public DatePicker altaLibroAño;
    public Button guardarAltaLibro;

    public MenuItem bajaLibro;
    public TextField bajaLibroTitulo;
    public TextField bajaLibroEditorial;
    public TextField bajaLibroAño;
    public Button darBajaLibro;

    public MenuItem modificarLibro;
    public TextField modificarLibroTitulo;
    public TextField modificarLibroEditorial;
    public TextField modificarLibroAño;
    public Button modificarLibros;

    public MenuItem buscarLibro;
    public TextField buscarLibroTitulo;
    public TextField buscarLibroEjemplares;
    public TextField buscarLibroPaginas;
    public TextField buscarLibroEditorial;
    public TextField buscarLibroAño;
    public Button buscarLibros;

    public MenuItem altaSocio;
    public MenuItem bajaSocio;
    public MenuItem modificarSocio;
    public MenuItem buscarSocio;
    public MenuItem prestar;
    public MenuItem librosPrestados;
    public MenuItem sociosPrestados;
    public MenuItem prestamosExcedidosSocios;
    public MenuItem prestamosExcedidosLibros;

    private ArrayList<Libro> libros = new ArrayList<>();
    private ArrayList<Socio> socios;
    private ArrayList<Prestamo> prestamos;


    public void nuevaVentana(String type, String title)
    {
        try {

            Parent root = FXMLLoader.load(getClass().getResource(type));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void altaLibro(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/libro/altaLibro.fxml", "Alta de libro");
    }

    public void bajaLibro(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/libro/bajaLibro.fxml", "Baja de libro");
    }

    public void modificarLibro(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/libro/modificarLibro.fxml", "Modificar el libro");
    }

    public void buscarLibro(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/libro/buscarLibro.fxml", "Buscar un libro");
    }

    public void altaSocio(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/socio/altaSocio.fxml", "Alta de socio");
    }

    public void bajaSocio(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/socio/bajaSocio.fxml", "Baja de socio");
    }

    public void modificarSocio(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/socio/modificarSocio.fxml", "Modificar un socio");
    }

    public void buscarSocio(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/socio/buscarSocio.fxml", "Buscar un socio");
    }

    public void prestar(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/prestamo/prestar.fxml", "Prestamo de libro");
    }

    public void librosPrestados(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/prestamo/librosPrestados.fxml", "Libros en prestamo");
    }

    public void sociosPrestados(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/prestamo/sociosPrestados.fxml", "Socios con prestamos");
    }

    public void prestamosExcedidosSocios(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/prestamo/prestamosExcedidosSocios.fxml", "Prestamos excedidos por socios");
    }

    public void prestamosExcedidosLibros(ActionEvent actionEvent)
    {
        nuevaVentana("layouts/prestamo/prestamosExcedidosLibros.fxml", "Prestamos excedidos por libros");
    }

    public void guardarAltaLibroEvent(ActionEvent actionEvent) {

        if (!altaLibroTitulo.getText().equals("") &&
                !altaLibroEjemplares.getText().equals("") &&
                !altaLibroEditorial.getText().equals("") &&
                !altaLibroPaginas.getText().equals("") &&
                altaLibroAño.getValue()!=null)
        {
            if (comprobarNumero(altaLibroEjemplares.getText()) &&  comprobarNumero(altaLibroPaginas.getText()))
            {
                Libro libro = new Libro(altaLibroTitulo.getText(),
                        Integer.parseInt(altaLibroEjemplares.getText()),
                        altaLibroEditorial.getText(),
                        Integer.parseInt(altaLibroPaginas.getText()),
                        altaLibroAño.getValue());
                libros.add(libro);
                DAO dao = new DAO();
                dao.saveBook(libro);
            }
            else
            {

            }
        }
        else
        {
            System.out.println("hay campos vacíos");
        }

    }
    public boolean comprobarNumero(String numero)
    {
        try
        {
            Integer.parseInt(numero);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
