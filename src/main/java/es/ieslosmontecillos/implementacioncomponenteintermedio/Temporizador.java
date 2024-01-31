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
/**
 * El componente Temporizador representa un temporizador con cuenta atrás en JavaFX.
 * Cuando la cuenta atrás llega a cero, se imprime un mensaje indicando que el tiempo se ha agotado.
 *
* @author Raul Plaza Galvez
* @version 1.0 31/01/2024
* */
public class Temporizador extends HBox {
    @FXML
    private Label temporizadorLabel;

    private SimpleIntegerProperty tiempoProperty = new SimpleIntegerProperty();
    private Timeline timeline;

    public Temporizador() {
        /**
        * Contructor de la clase Temporizador. En ella estamos cargando el fxml "Temporizador.fxml"
        * */
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
    /**
    *  Obtiene el tiempo actual del tiempo.
     *
     *  @return devuelve el valor tiempo en segundos.
    * */
    public int getTiempo() {
        return tiempoProperty.get();
    }

    /**
    * Establece el tiempo Inicial del temporizador.
    * */
    public void setTiempo(int tiempo) {
        tiempoProperty.set(tiempo);
        actualizarEtiqueta();
    }
    /**
    * Obtiene la propiedad del SimpleIntegerProperty
    * */
    public SimpleIntegerProperty tiempoProperty() {
        return tiempoProperty;
    }

    /**
    * La etiqueta se actualiza cada vez que avanza el tiempo.
    * */

    private void actualizarEtiqueta() {
        temporizadorLabel.setText("Tiempo restante: " + tiempoProperty.get() + " segundos");
    }

    @FXML
    private void initialize() {
        actualizarEtiqueta();
    }

    /**
     * Inicia el cronometro ademas de la cuenta atras.
     *
     * */
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
