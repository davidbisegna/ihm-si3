package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.models.ModelListOfMenus;
import sample.models.ModelMenu;

public class Main extends Application {

    public static ModelListOfMenus listMenus;

    @Override
    public void start(Stage primaryStage) throws Exception{
        listMenus = new ModelListOfMenus();
        initDatas();
        Parent root = FXMLLoader.load(getClass().getResource(ViewMenus.XML_ACCUEIL));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, ViewMenus.WIDTH, ViewMenus.HEIGHT));

        primaryStage.show();
    }


    public void initDatas(){
        Main.listMenus.add_menu(new ModelMenu("Repas Italien", "Tomate Mozza", "Polenta", "Tiramisu", 15.50, 500));
        Main.listMenus.add_menu(new ModelMenu("Repas pas bon", "Légumes froid", "Steak trop cuit", "Rien", 6, 200));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
