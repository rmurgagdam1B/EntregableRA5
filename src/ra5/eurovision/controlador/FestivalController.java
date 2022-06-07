package ra5.eurovision.controlador;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import ra5.eurovision.modelo.Festival;
import ra5.eurovision.modelo.PaisExcepcion;

import java.io.File;
import java.io.IOException;

public class FestivalController {
    Festival festival;

    public FestivalController() {
        festival = new Festival();
    }

    @FXML
    private Button clear;

    @FXML
    private Button btnMostrarGanador; // OK

    @FXML
    private Button btnMostrarPuntos; // OK

    @FXML
    private CheckBox chkGuardarFichero; // OK

    @FXML
    private MenuItem menuLeer; //

    @FXML
    private MenuItem salir; //

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtPais;

    @FXML
    void salir(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void setMenuLeer(){
        FileChooser selector = new FileChooser();
        selector.setTitle("Abrir fichero de datos");
        selector.setInitialDirectory(new File("."));
        selector.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("txt",
                        "*.txt"));
        File f = selector.showOpenDialog(null);
        if (f != null) {
            txtArea.setText("Había " + festival.leerPuntuaciones(f.getAbsolutePath()) + "errores");
        }

    }

    @FXML
    void salvarEnFichero() throws IOException {
        festival.guardarResultados();
    }

    @FXML
    void setBtnMostrarGanador(){
        festival.ganador();
        txtArea.setText(festival.ganador());
    }

    @FXML
    void setBtnMostrarPuntos() throws PaisExcepcion {
        String pais = txtPais.getText();
        festival.puntuacionDe(pais);
        txtArea.setText(String.valueOf(festival.puntuacionDe(pais)));
    }

    private void cogerFoco() {
        txtPais.requestFocus();
        txtPais.selectAll();
    }

    @FXML
    void clear(ActionEvent event) {
        txtArea.setText("");
        cogerFoco();

    }

}

