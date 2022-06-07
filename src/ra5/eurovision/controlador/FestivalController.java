package ra5.eurovision.controlador;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FestivalController {


    public FestivalController() {


    }

    @FXML
    private Button btnClear;

    @FXML
    private Button btnMostrarGanador;

    @FXML
    private Button btnMostrarPuntos;

    @FXML
    private CheckBox chkGuardarFichero;

    @FXML
    private MenuItem menuLeer;

    @FXML
    private MenuItem salir;

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtPais;


    @FXML
    void salir(ActionEvent event) {
        Platform.exit();
    }


    @FXML
    void setBtnMostrarGanador(){

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

