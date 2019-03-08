package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.Main;
import sample.ViewMenus;
import sample.models.ModelMenu;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static void initDatas(){
        List<ModelMenu> menus = getMenusFromJSON();
        for(ModelMenu menu : menus){
            Main.listMenus.add_menu(new ModelMenu(menu.getNomMenu(), menu.getEntree(), menu.getPlat(), menu.getDessert(), menu.getPrix(),menu.getCalories()));
        }
    }

    private static List<ModelMenu> getMenusFromJSON(){
        List<ModelMenu> parsedMenus= new ArrayList<>();
        try{
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

    public static void saveMenusInJSON(){
        try{
            FileWriter writer = new FileWriter(ViewMenus.JSON_MENUS);
            JSONArray array = new JSONArray();
            JSONObject menus = new JSONObject();
            for(ModelMenu menu : Main.listMenus.getMenus()){
                JSONObject obj = new JSONObject();
                obj.put("NomMenu", menu.getNomMenu());
                obj.put("Entree", menu.getEntree());
                obj.put("Plat", menu.getPlat());
                obj.put("Dessert", menu.getDessert());
                obj.put("Prix", menu.getPrix());
                obj.put("Calories", menu.getCalories());
                array.add(obj);
            }
            menus.put("Menus", array);
            writer.write(menus.toJSONString());
            writer.flush();
            writer.close();
        } catch (Exception e){
            System.err.println("Erreur : " + e);
        }
    }
}
