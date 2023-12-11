package es.ieslosmontecillos.implementacioncomponenteintermedio;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;

public class Temporizador extends HBox {
    @FXML
    private Label temporizadorLabel;

    private boolean autoReverse;
    private IntegerProperty segundos;
    private Timeline temporizador;
    private EventHandler<ActionEvent> onFinished;

    public Temporizador() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        segundos = new SimpleIntegerProperty();

        segundos.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue.toString());
                temporizadorLabel.setText(newValue.toString());
            }
        });
    }

    public void play() {
        temporizador = new Timeline();
        temporizador.setAutoReverse(false);
        final KeyValue kvTemp = new KeyValue(segundos, 0);
        final KeyFrame kfTemp = new KeyFrame(Duration.seconds(segundos.doubleValue()), onFinished, kvTemp);
        temporizador.getKeyFrames().add(kfTemp);
        temporizador.play();
    }

    public void setOnFinished(EventHandler<ActionEvent> onFinished) {
        this.onFinished = onFinished;
    }

    public final EventHandler<ActionEvent> getOnFinished() {
        return onFinished;
    }

    public int getSegundos() {
        return segundos.get();
    }

    public IntegerProperty segundosProperty() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos.set(segundos);
    }

    public boolean isAutoReverse() {
        return autoReverse;
    }

    public void setAutoReverse(boolean autoReverse) {
        this.autoReverse = autoReverse;
        temporizador.setAutoReverse(autoReverse);
    }
}
