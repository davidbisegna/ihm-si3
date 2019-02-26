package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.Controller;
import sample.controllers.ControllerMenu;
import sample.models.ModelListOfMenus;

import javax.swing.text.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        ViewMenus view = new ViewMenus();

        Controller controller = new Controller();
        loader.setController(controller);


        Parent root = FXMLLoader.load(getClass().getResource(ViewMenus.XML_ACCUEIL));

        ModelListOfMenus model = new ModelListOfMenus();
        controller.init(model);
        view.init(model, controller);


        primaryStage.setScene(new Scene(root, ViewMenus.WIDTH, ViewMenus.HEIGHT));
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
