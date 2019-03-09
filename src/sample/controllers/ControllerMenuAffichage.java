package sample.controllers;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.util.Callback;
import sample.Main;
import sample.models.ModelMenu;

import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Optional;

import static sample.ViewMenus.XML_CREERMENU;

public class ControllerMenuAffichage extends Controller {

    ModelMenu selectedMenu;

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
    private TableColumn action;

    @FXML
    private Button add_menu_button;

    public void initialize(){
        super.initialize();
        action = new TableColumn("Action");
        // Création de la colonne action qui contient un bouton supprimer pour chaque ligne
        liste_menus.getColumns().add(action);

        liste_menus.setItems(Main.listMenus.getMenus());

        liste_menus.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectedMenu = (ModelMenu) liste_menus.getSelectionModel().getSelectedItem();
            }
        });

        ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Supprimer");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(selectedMenu != null) {
                    remove_selected_menu(selectedMenu);
                }
            }
        });

        // Add MenuItem to ContextMenu
        contextMenu.getItems().addAll(item1);

        // When user right-click on a Menu
        liste_menus.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(liste_menus, event.getScreenX(), event.getScreenY());
            }
        });


        liste_menus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TableView table = (TableView) event.getSource();
                selectedMenu = (ModelMenu) table.getSelectionModel().getSelectedItem();
                System.out.println(selectedMenu);
                contextMenu.hide();
            }
        });

        action.setCellValueFactory(new PropertyValueFactory<ModelMenu,Button>("button"));
        nom_menu.setCellValueFactory(c -> c.getValue().nomMenuProperty());
        entree.setCellValueFactory(c -> c.getValue().entreeProperty());
        plat.setCellValueFactory(c -> c.getValue().platProperty());
        dessert.setCellValueFactory(c -> c.getValue().dessertProperty());
        prix.setCellValueFactory(c -> c.getValue().prixProperty().asObject());
        calories.setCellValueFactory(c -> c.getValue().caloriesProperty().asObject());
        add_menu_button.setOnAction(createLoadPageEvent("../"+XML_CREERMENU));
    }

    @FXML
    public void handleKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.DELETE && !Main.listMenus.getMenus().isEmpty() && selectedMenu != null){
            remove_selected_menu(selectedMenu);
        }
    }

    public void remove_selected_menu(ModelMenu menuToDelete){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Suppression d'un menu");
        alert.setContentText("Êtes vous sûr de vouloir supprimer le menu " + selectedMenu.getNomMenu() + " ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Main.listMenus.remove_menu(menuToDelete);
        }
    }
}
