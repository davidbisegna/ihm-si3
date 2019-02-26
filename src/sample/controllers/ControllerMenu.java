package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.ViewMenus;
import sample.models.ModelListOfMenus;
import sample.models.ModelMenu;

import javax.swing.event.TableColumnModelListener;

public class ControllerMenu {
    /*@FXML
    private Label menu;

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
    private TextField nom_menu;

    @FXML
    private TextField entree;

    @FXML
    private TextField plat;

    @FXML
    private TextField dessert;

    @FXML
    private Button enregistrer_menu;

    @FXML
    private ListView<ModelMenu> liste_menus2;

    private ModelListOfMenus modelListOfMenus = null;

    public ListView getMenusListView(){
        return liste_menus2;
    }

    public void init(ModelListOfMenus liste){
        this.modelListOfMenus = liste;
    }
}
