package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.ViewMenus;


import java.io.IOException;

public class Controller {

    @FXML
    protected Button menu_accueil;

    @FXML
    protected Button menu_listedecourse;

    @FXML
    protected Button menu_menus;

    @FXML
    protected Button menu_consommation;


    @FXML
    public void initialize(){
        menu_accueil.setOnAction(createLoadPageEvent("../"+ViewMenus.XML_ACCUEIL));
        menu_listedecourse.setOnAction(createLoadPageEvent("../"+ViewMenus.XML_LISTECOURSEVALIDATION));
        menu_consommation.setOnAction(createLoadPageEvent("../"+ViewMenus.XML_CONSOMMATION));
        menu_menus.setOnAction(createLoadPageEvent("../"+ViewMenus.XML_MENUAFFICHAGE));
    }

    protected void loadPage(ActionEvent event, String pagePath) throws IOException{
        Parent pageParent = FXMLLoader.load(getClass().getResource(pagePath));
        Scene newPage = new Scene(pageParent);
        pageParent.getStylesheets().add(ViewMenus.CSS);
        Stage window = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(newPage);
        window.show();
    }

    public void loadCreateMenuPage(ActionEvent event) throws IOException{
        loadPage(event, "../"+ViewMenus.XML_CREERMENU);
    }

    protected EventHandler<ActionEvent> createLoadPageEvent(String pagePath){
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
}
