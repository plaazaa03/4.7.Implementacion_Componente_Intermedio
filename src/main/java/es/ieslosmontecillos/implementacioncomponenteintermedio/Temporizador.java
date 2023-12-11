package es.ieslosmontecillos.implementacioncomponenteintermedio;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Temporizador extends HBox {
    @FXML
    private Label temporizadorLabel;

    private int tiempo;
    private EventHandler<ActionEvent> onFinCuentaAtras;

    public Temporizador() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.setOnMouseClicked(event -> {
            if (onFinCuentaAtras != null) {
                onFinCuentaAtras.handle(new ActionEvent(this, null));
            }
        });
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
        actualizarTexto();
    }

    public void setOnFinCuentaAtras(EventHandler<ActionEvent> onFinCuentaAtras) {
        this.onFinCuentaAtras = onFinCuentaAtras;
    }

    private void actualizarTexto() {
        temporizadorLabel.setText(String.valueOf(tiempo));
    }
}
