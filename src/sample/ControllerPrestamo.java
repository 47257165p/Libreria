package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.text.SimpleDateFormat;

/**
 * Created by 47257165p on 04/02/16.
 */
public class ControllerPrestamo {

    public TextField altaPrestamoLibro;
    public TextField altaPrestamoSocio;
    public TextField altaPrestamoInicio;
    public TextField altaPrestamoFinal;
    public Text altaPrestamoInfo;
    public Button altaPrestamoAlta;
    Libro libro = null;
    Socio socio = null;

    public void prestarLibro(ActionEvent actionEvent) {

        try {
            for (int i = 0; i < ControllerPrincipal.libros.size(); i++) {
                if (altaPrestamoLibro.getText().equals(ControllerPrincipal.libros.get(i).getTitulo())) {
                    libro = ControllerPrincipal.libros.get(i);
                }
            }
            for (int i = 0; i < ControllerPrincipal.socios.size(); i++) {
                if (altaPrestamoSocio.getText().equals(ControllerPrincipal.socios.get(i).getNombre())) {
                    socio = ControllerPrincipal.socios.get(i);
                }
            }
            if (libro != null &&
                    socio != null &&
                    !altaPrestamoInicio.getText().equals("") &&
                    !altaPrestamoFinal.getText().equals("")) {
                SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
                Prestamo prestamo = new Prestamo(libro, socio, formater.parse(altaPrestamoInicio.getText()), formater.parse(altaPrestamoFinal.getText()));
                ControllerPrincipal.prestamos.add(prestamo);
                DAO dao = new DAO();
                dao.guardarPrestamo(prestamo);
                altaPrestamoInfo.setText("Préstamo guardado correctamente.");
                altaPrestamoInfo.setVisible(true);
            }
        }
        catch (Exception e)
        {
            altaPrestamoInfo.setText("No se han introducido campos válidos.");
        }
    }
}
