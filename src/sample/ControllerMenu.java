package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sample.models.ModelMenu;

public class ControllerMenu {
    @FXML
    private Label menu;
    /*
    @FXML
    private Label entree;

    @FXML
    private Label plat;

    @FXML
    private Label dessert;

    @FXML
    private Label prix;

    @FXML
    private Label calories;
    */

    @FXML
    private ListView<ModelMenu> liste_menus2;

    public void init(ModelMenu menu){

    }
}
