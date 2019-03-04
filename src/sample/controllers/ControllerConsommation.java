package sample.controllers;

import javafx.collections.ObservableList;
import sample.Main;
import sample.models.ModelListOfMenus;
import sample.models.ModelMenu;

import java.util.HashMap;

public class ControllerConsommation extends Controller{

    private HashMap<String, Integer> theMap;

    public void initialize(){
        super.initialize();
        theMap = new HashMap<>();
    }

    public void fillMap(){
        ObservableList<ModelMenu> listOfMenus = Main.listMenus.getMenus();
        int val;
        for (ModelMenu aMenu : listOfMenus){
            if (theMap.containsKey(aMenu.getEntree())) {
                val = theMap.get(aMenu.getEntree());
                theMap.put(aMenu.getEntree(), val +1);
            } else {
                theMap.put(aMenu.getEntree(), 1);
            }
            if (theMap.containsKey(aMenu.getPlat())) {
                val = theMap.get(aMenu.getPlat());
                theMap.put(aMenu.getPlat(), val +1);
            } else {
                theMap.put(aMenu.getPlat(), 1);
            }
            if (theMap.containsKey(aMenu.getDessert())) {
                val = theMap.get(aMenu.getDessert());
                theMap.put(aMenu.getDessert(), val +1);
            } else {
                theMap.put(aMenu.getDessert(), 1);
            }

        }
    }

    public float computeSumCalories() {
        float res = 0;
        ObservableList<ModelMenu> listOfMenus = Main.listMenus.getMenus();
        for (ModelMenu aMenu : listOfMenus){
            res += aMenu.getCalories();
        }
        return res;
    }

    public float computeSumPrices() {
        float res = 0;
        ObservableList<ModelMenu> listOfMenus = Main.listMenus.getMenus();
        for (ModelMenu aMenu : listOfMenus){
            res += aMenu.getPrix();
        }
        return res;
    }
}
