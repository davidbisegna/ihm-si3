package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.ViewMenus;
import sample.models.ModelListOfMenus;
import sample.models.ModelMenu;


import java.io.IOException;

import static sample.ViewMenus.*;

public class Controller {

    @FXML
    private Button menu_accueil;

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


    private ModelListOfMenus modelListeMenus;


    @FXML
    private ListView<ModelMenu> liste_menus2;

    @FXML
    private Button menu_listedecourse;

    @FXML
    private Button menu_menus;

    @FXML
    public void init(ModelListOfMenus menusList){
        this.modelListeMenus = menusList;
        menu_accueil.setOnAction(event -> createLoadPageEvent(XML_ACCUEIL));
        menu_listedecourse.setOnAction(event -> createLoadPageEvent(XML_LISTECOURSEVALIDATION));
        menu_menus.setOnAction(event -> createLoadPageEvent(XML_MENUAFFICHAGE));


        enregistrer_menu.setOnAction( event -> modelListeMenus.add_menu(new ModelMenu(nom_menu.getText(),entree.getText(),plat.getText(),dessert.getText())));



        //récupère l'observable
        //liste_menus2 = new ListView<>(modelListeMenus.getMenus());
    }

    private void loadPage(ActionEvent event, String pagePath) throws IOException{
        Parent pageParent = FXMLLoader.load(getClass().getResource(pagePath));
        Scene newPage = new Scene(pageParent);

        Stage window = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(newPage);
        window.show();
    }

    public void loadCreateMenuPage(ActionEvent event) throws IOException{
        loadPage(event, XML_CREERMENU);
    }

    private EventHandler<ActionEvent> createLoadPageEvent(String pagePath){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try{
                    loadPage(event, pagePath);
                } catch (IOException e){
                    System.err.println("Error : " + e);
                }
            }
        };
    }


    public ListView getMenusListView(){
        return liste_menus2;
    }
    /*public void add_menu(ActionEvent event) throws IOException{

        String nom = nom_menu.getText();
        String textEntree = entree.getText();
        String textPlat = plat.getText();
        String textDessert = dessert.getText();
        ModelMenu menu = new ModelMenu(nom,textEntree,textPlat,textDessert);
        modelListeMenus.add_menu(menu);

        //System.out.println(liste_menus2.getItems());
        liste_menus2.setItems(modelListeMenus.getMenus());


    }*/



}
