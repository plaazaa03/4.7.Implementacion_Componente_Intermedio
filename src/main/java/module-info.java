module es.ieslosmontecillos.implementacioncomponenteintermedio {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.ieslosmontecillos.implementacioncomponenteintermedio to javafx.fxml;
    exports es.ieslosmontecillos.implementacioncomponenteintermedio;
}