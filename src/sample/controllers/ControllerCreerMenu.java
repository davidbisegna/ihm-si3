package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.Main;
import sample.models.ModelMenu;

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
    private Button enregistrer_menu;


    //@FXML
    //private ListView<ModelMenu> liste_menus2;

    public void initialize(){
        super.initialize();
        enregistrer_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.listMenus.add_menu(new ModelMenu(nom_menu.getText(), entree.getText(), plat.getText(), dessert.getText(), Float.parseFloat(prix.getText()), Integer.parseInt(calories.getText())));
            }
        });

    }


    /*public void add_menu(){
        String nom = nom_menu.getText();
        String textEntree = entree.getText();
        String textPlat = plat.getText();
        String textDessert = dessert.getText();
        ModelMenu menu = new ModelMenu(nom,textEntree,textPlat,textDessert);
        modelListeMenus.add_menu(menu);

        //liste_menus2.setItems(modelListeMenus.getMenus());
    }*/
}
