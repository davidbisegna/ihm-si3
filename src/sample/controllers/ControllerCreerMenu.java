package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.Main;
import sample.ViewMenus;
import sample.models.ModelMenu;

import java.io.IOException;
import java.util.Date;

public class ControllerCreerMenu extends Controller {
    @FXML
    private TextField nom_menu;

    @FXML
    private TextField entree;

    @FXML
    private TextField plat;

    @FXML
    private TextField dessert;

    @FXML
    private TextField prix;

    @FXML
    private TextField calories;

    @FXML
    private DatePicker date;

    @FXML
    private Button enregistrer_menu;


    public void initialize(){
        super.initialize();
        enregistrer_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.listMenus.add_menu(new ModelMenu(nom_menu.getText(), entree.getText(), plat.getText(), dessert.getText(), Float.parseFloat(prix.getText()), Integer.parseInt(calories.getText())));
                try {
                    loadPage(event,"../"+ ViewMenus.XML_MENUAFFICHAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
