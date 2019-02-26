package sample.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.models.ModelMenu;

import static sample.ViewMenus.XML_CREERMENU;

public class ControllerMenuAffichage extends Controller {

    @FXML
    private TableView liste_menus;

    @FXML
    private TableColumn<ModelMenu, String> nom_menu;

    @FXML
    private TableColumn<ModelMenu, String> entree;

    @FXML
    private TableColumn<ModelMenu, String> plat;

    @FXML
    private TableColumn<ModelMenu, String> dessert;

    @FXML
    private TableColumn<ModelMenu, Double> prix;

    @FXML
    private TableColumn<ModelMenu, Integer> calories;

    @FXML
    private Button add_menu_button;

    public void initialize(){
        super.initialize();
        liste_menus.setItems(Main.listMenus.getMenus());
        nom_menu.setCellValueFactory(c -> c.getValue().nomMenuProperty());
        entree.setCellValueFactory(c -> c.getValue().entreeProperty());
        plat.setCellValueFactory(c -> c.getValue().platProperty());
        dessert.setCellValueFactory(c -> c.getValue().dessertProperty());
        prix.setCellValueFactory(c -> c.getValue().prixProperty().asObject());
        calories.setCellValueFactory(c -> c.getValue().caloriesProperty().asObject());
        add_menu_button.setOnAction(createLoadPageEvent("../"+XML_CREERMENU));
    }

}
