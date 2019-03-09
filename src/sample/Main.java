package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.controllers.Controller;
import sample.models.ModelListOfMenus;
import sample.models.ModelListedesUtilisateurs;
import sample.models.ModelMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static ModelListOfMenus listMenus;
    public static ModelListedesUtilisateurs listeUtilisateurs;

    @Override
    public void start(Stage primaryStage) throws Exception{
        listMenus = new ModelListOfMenus();
        listeUtilisateurs = new ModelListedesUtilisateurs();
        Controller.initDatas();
        Parent root = FXMLLoader.load(getClass().getResource(ViewMenus.XML_ACCUEIL));
        root.getStylesheets().add(ViewMenus.CSS);
        primaryStage.setTitle(ViewMenus.APP_NAME);
        primaryStage.setScene(new Scene(root, ViewMenus.WIDTH, ViewMenus.HEIGHT));
        primaryStage.show();
    }

    @Override
    public void stop(){
        Controller.saveMenusInJSON();
        Controller.saveUtilisateursInJSON();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
