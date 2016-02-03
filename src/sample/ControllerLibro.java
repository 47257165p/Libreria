package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 47257165p on 29/01/16.
 */
public class ControllerLibro {

    public Text altaLibroInfo;
    public TextField altaLibroTitulo;
    public TextField altaLibroEjemplares;
    public TextField altaLibroPaginas;
    public TextField altaLibroEditorial;
    public DatePicker altaLibroAño;
    public Button guardarAltaLibro;

    public Text bajaLibroInfo;
    public TextField bajaLibroTitulo;
    public TextField bajaLibroEditorial;
    public DatePicker bajaLibroAño;
    public Button darBajaLibro;

    public Text buscarLibroInfo;
    public TextField buscarLibroTitulo;
    public TextField buscarLibroEditorial;
    public DatePicker buscarLibroAño;
    public Button buscarLibros;
    public ListView listListarLibros;

    DAO dao = new DAO();

    public void guardarAltaLibroEvent(ActionEvent actionEvent) {

        if (!altaLibroTitulo.getText().equals("") &&
                !altaLibroEjemplares.getText().equals("") &&
                !altaLibroEditorial.getText().equals("") &&
                !altaLibroPaginas.getText().equals("") &&
                altaLibroAño.getValue()!=null)
        {
            if (ControllerPrincipal.comprobarNumero(altaLibroEjemplares.getText()) &&  ControllerPrincipal.comprobarNumero(altaLibroPaginas.getText()))
            {
                Libro libro = new Libro(altaLibroTitulo.getText(),
                        Integer.parseInt(altaLibroEjemplares.getText()),
                        altaLibroEditorial.getText(),
                        Integer.parseInt(altaLibroPaginas.getText()),
                        ControllerPrincipal.asDate(altaLibroAño.getValue()));
                ControllerPrincipal.libros.add(libro);
                dao.guardarLibro(libro);

                altaLibroInfo.setText("El libro se ha dado de alta satisfactoriamente.");
                altaLibroInfo.setVisible(true);
            }
            else
            {
                altaLibroInfo.setText("Los campos ''Número de ejemplares'' y ''Páginas'' deben ser números.");
                altaLibroInfo.setVisible(true);
            }
        }
        else
        {
            altaLibroInfo.setText("Faltan campos por rellenar.");
            altaLibroInfo.setVisible(true);
        }

    }

    public void aceptarBajaLibro(ActionEvent actionEvent) {

        if (!bajaLibroTitulo.getText().equals("") &&
                !bajaLibroEditorial.getText().equals("") &&
                bajaLibroAño.getValue()!=null)
        {
            String titulo = bajaLibroTitulo.getText();
            String editorial = bajaLibroEditorial.getText();
            Date año = ControllerPrincipal.asDate(bajaLibroAño.getValue());
            int borrado = 1;

            for (int i = 0; i < ControllerPrincipal.libros.size(); i++) {

                if (ControllerPrincipal.libros.get(i).getTitulo().equals(titulo) &&
                        ControllerPrincipal.libros.get(i).getEditorial().equals(editorial) &&
                        ControllerPrincipal.libros.get(i).getAñoEdicion().equals(año))
                {
                    if (dao.borrarLibro(ControllerPrincipal.libros.get(i)))
                    {
                        ControllerPrincipal.libros.remove(i);
                        borrado = 0;
                    }
                    else {
                        bajaLibroInfo.setText("No se ha podido eliminar el elemento de la base de datos.");
                        bajaLibroInfo.setVisible(true);
                    }
                }
            }
            switch (borrado)
            {
                case 0: {
                    bajaLibroInfo.setText("El libro se ha dado de baja satisfactoriamente.");
                    bajaLibroInfo.setVisible(true);
                    break;
                }

                case 1: {
                    bajaLibroInfo.setText("No se ha encontrado el libro indicado");
                    bajaLibroInfo.setVisible(true);
                    break;
                }
            }
        }
        else
        {
            bajaLibroInfo.setText("Faltan campos por rellenar.");
            bajaLibroInfo.setVisible(true);
        }

    }

    public void buscarLibro(ActionEvent actionEvent) {

        buscarLibroInfo.setVisible(false);
        if (buscarLibroTitulo.getText().equals("") &&
                buscarLibroEditorial.getText().equals("") &&
                buscarLibroAño.getValue() == null)
        {
            ControllerPrincipal controller = new ControllerPrincipal();
            controller.nuevaVentana("layouts/libro/listaLibros.fxml", "Lista de libros");

            ObservableList<String> items = FXCollections.observableArrayList();

            for (int x=0; x<ControllerPrincipal.libros.size(); x++)
            {
                System.out.println(ControllerPrincipal.libros.get(x).getTitulo());
                items.add(ControllerPrincipal.libros.get(x).getTitulo());
            }

            listListarLibros.setItems(items);

        }
    }
}
