package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static sample.View.XML_ACCUEIL;
import static sample.View.XML_CREERMENU;
import static sample.View.XML_MENUAFFICHAGE;

public class Controller {

    public void load_accueil(ActionEvent event) throws IOException{
        Parent accueil_parent = FXMLLoader.load(getClass().getResource(XML_ACCUEIL));
        Scene accueil = new Scene(accueil_parent);

        Stage window = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(accueil);
        window.show();
    }

    public void load_menu_affichage(ActionEvent event) throws IOException {
        Parent menus_parent = FXMLLoader.load(getClass().getResource(XML_MENUAFFICHAGE));
        Scene menus = new Scene(menus_parent);

        Stage window = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(menus);
        window.show();
    }

    public void load_creer_menu(ActionEvent event) throws IOException{
        Parent creer_menu_parent = FXMLLoader.load(getClass().getResource(XML_CREERMENU));
        Scene creer_menu = new Scene(creer_menu_parent);

        Stage window = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(creer_menu);
        window.show();
    }
}
