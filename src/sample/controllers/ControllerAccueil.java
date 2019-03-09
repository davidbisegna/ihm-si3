package sample.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.util.Optional;

public class ControllerAccueil extends Controller {

    @FXML
    private Circle nouveau_repas_button;

    @FXML
    private Label plus_label_button;

    public void initialize(){
        super.initialize();
        EventHandler<MouseEvent> planMenuButtonEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Planification");
                alert.setHeaderText("Planifier un menu");
                alert.setContentText("test");
                alert.

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){

                }*/
            }
        };
        nouveau_repas_button.setOnMouseClicked(planMenuButtonEvent);
        plus_label_button.setOnMouseClicked(planMenuButtonEvent);
    }
}
