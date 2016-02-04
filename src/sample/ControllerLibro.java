package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.util.ArrayList;

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
    public Button darBajaLibro;

    public Text buscarLibroInfo;
    public TextField buscarLibroTitulo;
    public TextField buscarLibroEditorial;
    public DatePicker buscarLibroAño;
    public Button buscarLibros;
    public ListView listBuscar;

    public TextField modificarLibroTitulo;
    public TextField modificarLibroEditorial;
    public Button modificarLibros;
    public TextField modEjemplares;
    public TextField modPaginas;
    public DatePicker modAñoEdicion;
    public Button modLibros;
    public Text modInfo;
    Libro libro = null;
    int iterator = 0;

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
                !bajaLibroEditorial.getText().equals(""))
        {
            String titulo = bajaLibroTitulo.getText();
            String editorial = bajaLibroEditorial.getText();
            int borrado = 1;

            for (int i = 0; i < ControllerPrincipal.libros.size(); i++) {

                if (ControllerPrincipal.libros.get(i).getTitulo().equals(titulo) &&
                        ControllerPrincipal.libros.get(i).getEditorial().equals(editorial))
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

            ObservableList<String> items = FXCollections.observableArrayList();

            for (int x=0; x<ControllerPrincipal.libros.size(); x++)
            {
                items.add(ControllerPrincipal.libros.get(x).toString());
            }
            despuesBuscar(items);

        }
        else if (!buscarLibroTitulo.getText().equals("") &&
                buscarLibroEditorial.getText().equals("") &&
                buscarLibroAño.getValue() == null)
        {
            ArrayList<String> encontrados = new ArrayList<>();

            for (int i = 0; i < ControllerPrincipal.libros.size(); i++) {
                if (ControllerPrincipal.libros.get(i).getTitulo().equals(buscarLibroTitulo.getText()))
                {
                    encontrados.add(ControllerPrincipal.libros.get(i).toString());
                }
            }

            ObservableList<String> items = FXCollections.observableArrayList();
            items.addAll(encontrados);
            despuesBuscar(items);
        }
        else if (buscarLibroTitulo.getText().equals("") &&
                !buscarLibroEditorial.getText().equals("") &&
                buscarLibroAño.getValue() == null)
        {
            ArrayList<String> encontrados = new ArrayList<>();

            for (int i = 0; i < ControllerPrincipal.libros.size(); i++) {
                if (ControllerPrincipal.libros.get(i).getEditorial().equals(buscarLibroEditorial.getText()))
                {
                    encontrados.add(ControllerPrincipal.libros.get(i).toString());
                }
            }

            ObservableList<String> items = FXCollections.observableArrayList();

            items.addAll(encontrados);
            despuesBuscar(items);
        }
        else if (buscarLibroTitulo.getText().equals("") &&
                buscarLibroEditorial.getText().equals("") &&
                buscarLibroAño.getValue() != null)
        {
            ArrayList<String> encontrados = new ArrayList<>();

            for (int i = 0; i < ControllerPrincipal.libros.size(); i++) {
                if (ControllerPrincipal.libros.get(i).getAñoEdicion().equals(buscarLibroAño.getValue()))
                {
                    encontrados.add(ControllerPrincipal.libros.get(i).toString());
                }
            }

            ObservableList<String> items = FXCollections.observableArrayList();

            items.addAll(encontrados);
            despuesBuscar(items);
        }
        else if (!buscarLibroTitulo.getText().equals("") &&
                !buscarLibroEditorial.getText().equals("") &&
                buscarLibroAño.getValue() == null)
        {
            ArrayList<String> encontrados = new ArrayList<>();

            for (int i = 0; i < ControllerPrincipal.libros.size(); i++) {
                if (ControllerPrincipal.libros.get(i).getTitulo().equals(buscarLibroTitulo.getText()) &&
                        ControllerPrincipal.libros.get(i).getEditorial().equals(buscarLibroEditorial.getText()))
                {
                    encontrados.add(ControllerPrincipal.libros.get(i).toString());
                }
            }

            ObservableList<String> items = FXCollections.observableArrayList();

            items.addAll(encontrados);
            despuesBuscar(items);
        }
        else if (!buscarLibroTitulo.getText().equals("") &&
                buscarLibroEditorial.getText().equals("") &&
                buscarLibroAño.getValue() != null)
        {
            ArrayList<String> encontrados = new ArrayList<>();

            for (int i = 0; i < ControllerPrincipal.libros.size(); i++) {
                if (ControllerPrincipal.libros.get(i).getTitulo().equals(buscarLibroTitulo.getText()) &&
                        ControllerPrincipal.libros.get(i).getAñoEdicion().equals(buscarLibroAño.getValue()))
                {
                    encontrados.add(ControllerPrincipal.libros.get(i).toString());
                }
            }

            ObservableList<String> items = FXCollections.observableArrayList();

            items.addAll(encontrados);
            despuesBuscar(items);
        }
        else if (buscarLibroTitulo.getText().equals("") &&
                !buscarLibroEditorial.getText().equals("") &&
                buscarLibroAño.getValue() != null)
        {
            ArrayList<String> encontrados = new ArrayList<>();

            for (int i = 0; i < ControllerPrincipal.libros.size(); i++) {
                if (ControllerPrincipal.libros.get(i).getEditorial().equals(buscarLibroEditorial.getText()) &&
                        ControllerPrincipal.libros.get(i).getAñoEdicion().equals(buscarLibroAño.getValue()))
                {
                    encontrados.add(ControllerPrincipal.libros.get(i).toString());
                }
            }

            ObservableList<String> items = FXCollections.observableArrayList();

            items.addAll(encontrados);
            despuesBuscar(items);
        }
        else if (!buscarLibroTitulo.getText().equals("") &&
                !buscarLibroEditorial.getText().equals("") &&
                buscarLibroAño.getValue() != null)
        {
            ArrayList<String> encontrados = new ArrayList<>();

            for (int i = 0; i < ControllerPrincipal.libros.size(); i++) {
                if (ControllerPrincipal.libros.get(i).getTitulo().equals(buscarLibroTitulo.getText()) &&
                        ControllerPrincipal.libros.get(i).getEditorial().equals(buscarLibroEditorial.getText()) &&
                        ControllerPrincipal.libros.get(i).getAñoEdicion().equals(buscarLibroAño.getValue()))
                {
                    encontrados.add(ControllerPrincipal.libros.get(i).toString());
                }
            }

            ObservableList<String> items = FXCollections.observableArrayList();

            items.addAll(encontrados);
            despuesBuscar(items);
        }
    }
    public void ocultarBuscar()
    {
        buscarLibroTitulo.setVisible(false);
        buscarLibroEditorial.setVisible(false);
        buscarLibroAño.setVisible(false);
        buscarLibroInfo.setVisible(false);
        listBuscar.setVisible(true);
    }
    public void despuesBuscar(ObservableList<String> items)
    {
        if (items.size()==0)
        {
            buscarLibroInfo.setText("No se ha encontrado ningún libro.");
            buscarLibroInfo.setVisible(true);
        }
        else
        {
            listBuscar.setItems(items);
            ocultarBuscar();
        }
    }

    public void modLibro(ActionEvent actionEvent) {
        modInfo.setVisible(false);
        if (!modificarLibroTitulo.getText().equals(""))
        {
            libro.setTitulo(modificarLibroTitulo.getText());
        }
        if (!modificarLibroEditorial.getText().equals(""))
        {
            libro.setEditorial(modificarLibroEditorial.getText());
        }
        if (modAñoEdicion.getValue()!=null)
        {
                libro.setAñoEdicion(ControllerPrincipal.asDate(modAñoEdicion.getValue()));
        }
        if (!modEjemplares.getText().equals(""))
        {
            libro.setNumero(Integer.parseInt(modEjemplares.getText()));
        }
        if (!modPaginas.getText().equals(""))
        {
            libro.setPaginas(Integer.parseInt(modPaginas.getText()));
        }
        DAO dao = new DAO();
        dao.modificarLibro(libro);
        ControllerPrincipal.libros.remove(iterator);
        ControllerPrincipal.libros.add(libro);
        modInfo.setText("El libro se ha modificado de forma satisfactoria");
        modInfo.setVisible(true);
        libro = null;
    }

    public void modificarLibros(ActionEvent actionEvent) {
        for (int i = 0; i < ControllerPrincipal.libros.size(); i++) {
            if (modificarLibroTitulo.getText().equals(ControllerPrincipal.libros.get(i).getTitulo())
                    && modificarLibroEditorial.getText().equals(ControllerPrincipal.libros.get(i).getEditorial()))
            {
                libro = ControllerPrincipal.libros.get(i);
                iterator = i;

            }
        }
        if (libro!=null)
        {
            modificarLibroTitulo.clear();
            modificarLibroTitulo.setPromptText(libro.getTitulo());
            modificarLibroEditorial.clear();
            modificarLibroEditorial.setPromptText(libro.getEditorial());
            modAñoEdicion.setPromptText(libro.getAñoEdicion().toString());
            modAñoEdicion.setVisible(true);
            modEjemplares.setPromptText(String.valueOf(libro.getNumero()));
            modEjemplares.setVisible(true);
            modPaginas.setPromptText(String.valueOf(libro.getPaginas()));
            modPaginas.setVisible(true);
            modificarLibros.setVisible(false);
            modLibros.setVisible(true);
        }
        else
        {
            modInfo.setText("No se ha encontrado el libro.");
            modInfo.setVisible(true);
        }
    }
}
