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
import sample.models.ModelListOfMenus;
import sample.models.ModelMenu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<ModelMenu> menus = getMenusFromJSON();
        for(ModelMenu menu : menus){
            Main.listMenus.add_menu(new ModelMenu(menu.getNomMenu(), menu.getEntree(), menu.getPlat(), menu.getDessert(), menu.getPrix(),menu.getCalories()));
        }
    }

    private List<ModelMenu> getMenusFromJSON(){
        List<ModelMenu> parsedMenus= new ArrayList<>();
        try{
            System.out.println();
            FileReader reader = new FileReader(ViewMenus.JSON_MENUS);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray menus = (JSONArray) jsonObject.get("Menus");


            for(Object o : menus){
                JSONObject innerObj = (JSONObject) o;
                String nomMenu = (String) innerObj.get("NomMenu");
                String entree = (String) innerObj.get("Entree");
                String plat = (String) innerObj.get("Plat");
                String dessert = (String) innerObj.get("Dessert");
                Double prix = (Double) innerObj.get("Prix");
                Long calories = (Long) innerObj.get("Calories");
                int parsedCalories = calories.intValue();

                parsedMenus.add(new ModelMenu(nomMenu, entree, plat, dessert, prix, parsedCalories));
            }

        } catch (FileNotFoundException e){
            System.err.println("File not found : " + e.getMessage());
        } catch(ParseException e){
            System.err.println("Erreur de parsing : " + e.getMessage());
        } catch(IOException e){
            System.err.println("Erreur lors de la lecture des données pré-enregistrée : " + e.getMessage());
        }

        return parsedMenus;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
