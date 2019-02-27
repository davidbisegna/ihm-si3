package sample.controllers;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.models.ModelMenu;

import java.awt.print.Book;

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
    private Button add_menu_button;

    public void initialize(){
        super.initialize();
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
                Main.listMenus.remove_menu(selectedMenu);
                selectedMenu = null;
            }
        });

        // Add MenuItem to ContextMenu
        contextMenu.getItems().addAll(item1);

        // When user right-click on Circle

        liste_menus.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                System.out.println("coucou");
                contextMenu.show(liste_menus, event.getScreenX(), event.getScreenY());
            }
        });



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
            Main.listMenus.remove_menu(selectedMenu);
        }
    }

}
