package sample.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelMenu {
    private StringProperty nomMenu;
    private StringProperty entree;
    private StringProperty plat;
    private StringProperty dessert;
    private IntegerProperty prix;
    private IntegerProperty calories;

    public ModelMenu(String nomMenu, String entree, String plat, String dessert){
        this.nomMenu = new SimpleStringProperty(nomMenu);
        this.entree = new SimpleStringProperty(entree);
        this.plat = new SimpleStringProperty(plat);
        this.dessert = new SimpleStringProperty(dessert);
    }

    public String getNomMenu() {
        return nomMenu.get();
    }

    public void setNomMenu(String nomMenu) {
        this.nomMenu = new SimpleStringProperty(nomMenu);
    }

    public String getEntree() {
        return entree.get();
    }

    public void setEntree(String entree) {
        this.entree = new SimpleStringProperty(entree);
    }

    public String getPlat() {
        return plat.get();
    }

    public void setPlat(String plat) {
        this.plat  = new SimpleStringProperty(plat);
    }

    public String getDessert() {
        return dessert.get();
    }

    public void setDessert(String dessert) {
        this.dessert  = new SimpleStringProperty(dessert);
    }

    public int getPrix() {
        return prix.get();
    }

    public void setPrix(int prix) {
        this.prix = new SimpleIntegerProperty(prix);
    }

    public int getCalories() {
        return calories.get();
    }

    public void setCalories(int calories) {
        this.calories = new SimpleIntegerProperty(calories);
    }

    @Override
    public String toString(){
        return this.nomMenu+" "+this.entree+" "+this.plat+" "+this.dessert;
    }

}
