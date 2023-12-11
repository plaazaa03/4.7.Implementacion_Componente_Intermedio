package es.ieslosmontecillos.implementacioncomponenteintermedio;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TemporizadorController {

    @FXML
    private Label temporizadorLabel;

    private int tiempo;
    private Timeline timeline;

    public TemporizadorController() {
        this.tiempo = 10; // Tiempo inicial de 10 segundos
    }

    @FXML
    private void initialize() {
        // Configurar la animación de la cuenta regresiva
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), this::updateTime));
        timeline.setCycleCount(tiempo + 1); // +1 para manejar correctamente el tiempo inicial

        // Iniciar la cuenta atrás cuando se inicializa el controlador
        timeline.play();
    }

    private void updateTime(ActionEvent event) {
        tiempo--;
        temporizadorLabel.setText(String.valueOf(tiempo));

        if (tiempo == 0) {
            timeline.stop();
            // Aquí puedes manejar la lógica cuando la cuenta atrás llega a cero
            System.out.println("Cuenta atrás finalizada");
        }
    }
}
