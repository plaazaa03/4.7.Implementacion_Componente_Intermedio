package es.ieslosmontecillos.implementacioncomponenteintermedio;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;

public class Temporizador extends HBox {
    @FXML
    private Label temporizadorLabel;

    private SimpleIntegerProperty tiempoProperty = new SimpleIntegerProperty();
    private Timeline timeline;

    public Temporizador() {
        // Cargar el archivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Inicializar la línea de tiempo
        timeline = new Timeline();
        timeline.setOnFinished(event -> {
            System.out.println("Tiempo agotado");
        });
    }

    public int getTiempo() {
        return tiempoProperty.get();
    }

    public void setTiempo(int tiempo) {
        tiempoProperty.set(tiempo);
        actualizarEtiqueta();
    }

    public SimpleIntegerProperty tiempoProperty() {
        return tiempoProperty;
    }

    private void actualizarEtiqueta() {
        temporizadorLabel.setText("Tiempo restante: " + tiempoProperty.get() + " segundos");
    }

    @FXML
    private void initialize() {
        actualizarEtiqueta();
    }

    public void iniciarCronometro() {
        tiempoProperty.set(getTiempo());
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            tiempoProperty.set(tiempoProperty.get() - 1);
            if (tiempoProperty.get() <= 0) {
                timeline.stop();
                System.out.println("Tiempo agotado");
            }
        });
        timeline.getKeyFrames().setAll(keyFrame);
        timeline.setCycleCount(tiempoProperty.get());
        timeline.playFromStart();
    }
}
