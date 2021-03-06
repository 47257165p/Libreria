package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class ControllerPrincipal {


    public MenuItem altaLibro;
    public MenuItem bajaLibro;
    public MenuItem buscarLibro;
    public MenuItem modificarLibro;

    public MenuItem altaSocio;
    public MenuItem bajaSocio;
    public MenuItem modificarSocio;
    public MenuItem buscarSocio;
    public MenuItem prestar;
    public MenuItem librosPrestados;
    public MenuItem sociosPrestados;
    public MenuItem prestamosExcedidosSocios;
    public MenuItem prestamosExcedidosLibros;

    public static ArrayList<Libro> libros = new ArrayList<>();
    public static ArrayList<Socio> socios;
    public static ArrayList<Prestamo> prestamos;
    public ListView listSociosPrestados;


    public void initialize ()
    {
        DAO dao = new DAO();

        try {libros = dao.actualizarArrayListLibros();}
        catch(Exception e){};
        try {socios = dao.actualizarArrayListSocios();}
        catch(Exception e){};
        try {prestamos = dao.actualizarArrayListPrestamos();}
        catch(Exception e){};
    }

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
        ObservableList<String> items = FXCollections.observableArrayList();

        if (prestamos != null)
        {
            for (int i = 0; i < prestamos.size(); i++) {
                items.add(prestamos.toString());
                System.out.println(prestamos.get(i).toString());
            }
            listSociosPrestados.setItems(items);
        }
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

    public static boolean comprobarNumero(String numero)
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
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public void borrarBBDD(ActionEvent actionEvent) {
        DAO dao = new DAO();
        dao.borrarBasseDatos();
    }
}
