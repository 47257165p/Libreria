package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Created by 47257165p on 04/02/16.
 */
public class ControllerSocio {

    public TextField altaSocioNombre;
    public TextField altaSocioApellido1;
    public TextField altaSocioApellido2;
    public TextField altaSocioDireccion;
    public TextField altaSocioTelefono;
    public DatePicker altaSocioEdad;
    public Button guardarAltaSocio;
    public Text guardarSocioInfo;

    public TextField bajaSocioNombre;
    public TextField bajaSocioApellido;
    public Button darBajaSocio;
    public Text bajaSocioInfo;

    public TextField buscarSocioNombre;
    public TextField buscarSocioApellido;
    public Button buscarSocio;
    public Text buscarSocioInfo;
    public ListView listBuscarSocio;

    public TextField modificarSocioNombre;
    public TextField modificarSocioApellido1;
    public Button modificarSocio;
    public Text modificarSocioInfo;
    public TextField modificarSocioApellido2;
    public TextField modificarSocioDireccion;
    public TextField modificarSocioTelefono;
    public DatePicker modificarSocioFecha;
    public Button modSocio;
    private Socio socio = null;
    private int iterator = 0;


    public void guardarSocio(ActionEvent actionEvent) {
        Socio socio = new Socio(altaSocioNombre.getText(),
                altaSocioApellido1.getText(),
                altaSocioApellido2.getText(),
                ControllerPrincipal.asDate(altaSocioEdad.getValue()),
                altaSocioDireccion.getText(),
                Integer.parseInt(altaSocioTelefono.getText()));
        ControllerPrincipal.socios.add(socio);
        DAO dao = new DAO();
        dao.guardarSocio(socio);
        guardarSocioInfo.setVisible(true);
    }


    public void darBaja(ActionEvent actionEvent) {
        for (int i = 0; i < ControllerPrincipal.socios.size(); i++) {
            if (ControllerPrincipal.socios.get(i).getNombre().equals(bajaSocioNombre.getText())
                    && ControllerPrincipal.socios.get(i).getApellido1().equals(bajaSocioApellido.getText()))
            {
                DAO dao = new DAO();
                dao.borrarSocio(ControllerPrincipal.socios.get(i));
                ControllerPrincipal.socios.remove(i);
                bajaSocioInfo.setText("Eliminado correctamente.");
                bajaSocioInfo.setVisible(true);
            }
        }
        if (!bajaSocioInfo.getText().equals("Eliminado correctamente."))
        {
            bajaSocioInfo.setText("Socio no encontrado.");
            bajaSocioInfo.setVisible(true);
        }
    }
    public void buscarSocio(ActionEvent actionEvent) {

        ObservableList<String> items = FXCollections.observableArrayList();

        if (buscarSocioNombre.getText().equals("") &&
                buscarSocioApellido.getText().equals(""))
        {
            for (int i = 0; i < ControllerPrincipal.socios.size(); i++) {
                items.add(ControllerPrincipal.socios.get(i).toString());
            }
        }
        else
        {
            for (int i = 0; i < ControllerPrincipal.socios.size(); i++)
            {
                if (ControllerPrincipal.socios.get(i).getNombre().equals(buscarSocioNombre.getText())
                        && ControllerPrincipal.socios.get(i).getApellido1().equals(buscarSocioApellido.getText()))
                {
                    items.add(ControllerPrincipal.socios.get(i).toString());
                }

            }
        }
        if (items.size()!=0)
        {
            ocultarBuscar();
            listBuscarSocio.setItems(items);
            listBuscarSocio.setVisible(true);
        }
        else
        {
            buscarSocioInfo.setText("Socio no encontrado.");
            buscarSocioInfo.setVisible(true);
        }
    }
    public void ocultarBuscar()
    {
        buscarSocioInfo.setVisible(false);
        buscarSocioNombre.setVisible(false);
        buscarSocioApellido.setVisible(false);
    }

    public void modificarSocio(ActionEvent actionEvent) {
        for (int i = 0; i < ControllerPrincipal.socios.size(); i++)
        {
            if (ControllerPrincipal.socios.get(i).getNombre().equals(buscarSocioNombre.getText())
                    && ControllerPrincipal.socios.get(i).getApellido1().equals(buscarSocioApellido.getText()))
            {
                socio = ControllerPrincipal.socios.get(i);
                iterator = i;
            }
        }
        if (socio != null)
        {
            mostrarCamposMostrar();
        }
        else
        {
            modificarSocioInfo.setText("Socio no encontrado.");
            modificarSocioInfo.setVisible(true);
        }
    }

    public void modSocio(ActionEvent actionEvent) {
        modificarSocioInfo.setVisible(false);
        if (!modificarSocioNombre.getText().equals(""))
        {
            socio.setNombre(modificarSocioNombre.getText());
        }
        if (!modificarSocioApellido1.getText().equals(""))
        {
            socio.setApellido1(modificarSocioApellido1.getText());
        }
        if (!modificarSocioApellido2.getText().equals(""))
        {
            socio.setApellido2(modificarSocioApellido2.getText());
        }
        if (!modificarSocioDireccion.getText().equals(""))
        {
            socio.setDireccion(modificarSocioDireccion.getText());
        }
        if (modificarSocioTelefono.getText().equals(""))
        {
            socio.setTelefono(Integer.parseInt(modificarSocioTelefono.getText()));
        }
        if (modificarSocioFecha.getValue() != null)
        {
            socio.setEdad(ControllerPrincipal.asDate(modificarSocioFecha.getValue()));
        }
        DAO dao = new DAO();
        dao.modificarSocio(socio);
        ControllerPrincipal.socios.remove(iterator);
        ControllerPrincipal.socios.add(socio);
        modificarSocioInfo.setText("El socio se ha modificado de forma satisfactoria");
        modificarSocioInfo.setVisible(true);
        socio = null;
    }
    public void mostrarCamposMostrar()
    {
        modificarSocioNombre.clear();
        modificarSocioNombre.setPromptText(socio.getNombre());
        modificarSocioApellido1.clear();
        modificarSocioApellido1.setPromptText(socio.getApellido1());
        modificarSocioApellido2.setPromptText(socio.getApellido2());
        modificarSocioApellido2.setVisible(true);
        modificarSocioDireccion.setPromptText(socio.getDireccion());
        modificarSocioDireccion.setVisible(true);
        modificarSocioTelefono.setPromptText(String.valueOf(socio.getTelefono()));
        modificarSocioTelefono.setVisible(true);
        modificarSocioFecha.setPromptText(String.valueOf(socio.getEdad()));
        modificarSocioFecha.setVisible(true);
    }
}
