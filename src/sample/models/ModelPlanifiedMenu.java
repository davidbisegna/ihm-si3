package sample.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import sample.Main;
import sample.controllers.Controller;

import java.time.LocalDate;
import java.util.Optional;

public class ModelPlanifiedMenu extends ModelMenu{

    private ModelMenu menu;
    private LocalDate date;
    private Button button;

    public ModelPlanifiedMenu(ModelMenu menu, LocalDate date){
        super(menu);
        this.menu = menu;
        this.date = date;

        button = new Button("Supprimer");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Suppression d'un menu");
                alert.setContentText("Êtes vous sûr de vouloir supprimer ce menu ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    selfRemoveFromObservable();
                }

            }
        });
    }

    public void selfRemoveFromObservable(){
        Main.listPlanifiedMenus.remove_menu(this);
    }

    public String getNomMenu() {
        return menu.getNomMenu();
    }

    public void setNomMenu(String nomMenu) {
        this.menu.setNomMenu(nomMenu);
    }

    public StringProperty nomMenuProperty(){
        return menu.nomMenuProperty();
    }

    public LocalDate getDate() {return this.date; }

    public StringProperty getDateStringProperty() { return new SimpleStringProperty(this.dateToString()); }

    public String dateToString(){
        return this.date.getDayOfMonth() + " " + this.date.getMonth().toString() + " " + this.date.getYear();
    }

    public void setButton(Button button){
        this.button = button;
    }

    public Button getButton(){
        return button;
    }
}
