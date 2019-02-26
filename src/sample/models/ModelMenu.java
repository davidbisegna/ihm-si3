package sample.models;

import javafx.beans.property.*;

public class ModelMenu {
    private StringProperty nomMenu;
    private StringProperty entree;
    private StringProperty plat;
    private StringProperty dessert;
    private DoubleProperty prix;
    private IntegerProperty calories;

    public ModelMenu(String nomMenu, String entree, String plat, String dessert, double prix, int calories){
        this.nomMenu = new SimpleStringProperty(nomMenu);
        this.entree = new SimpleStringProperty(entree);
        this.plat = new SimpleStringProperty(plat);
        this.dessert = new SimpleStringProperty(dessert);
        this.prix = new SimpleDoubleProperty(prix);
        this.calories = new SimpleIntegerProperty(calories);
    }

    public String getNomMenu() {
        return nomMenu.get();
    }

    public void setNomMenu(String nomMenu) {
        this.nomMenu = new SimpleStringProperty(nomMenu);
    }

    public StringProperty nomMenuProperty(){
        return nomMenu;
    }


    public String getEntree() {
        return entree.get();
    }

    public void setEntree(String entree) {
        this.entree = new SimpleStringProperty(entree);
    }

    public StringProperty entreeProperty(){
        return entree;
    }


    public String getPlat() {
        return plat.get();
    }

    public void setPlat(String plat) {
        this.plat  = new SimpleStringProperty(plat);
    }

    public StringProperty platProperty(){
        return plat;
    }


    public String getDessert() {
        return dessert.get();
    }

    public void setDessert(String dessert) {
        this.dessert  = new SimpleStringProperty(dessert);
    }

    public StringProperty dessertProperty(){
        return dessert;
    }


    public double getPrix() {
        return prix.get();
    }

    public void setPrix(double prix) {
        this.prix = new SimpleDoubleProperty(prix);
    }

    public DoubleProperty prixProperty(){
        return prix;
    }


    public int getCalories() {
        return calories.get();
    }

    public void setCalories(int calories) {
        this.calories = new SimpleIntegerProperty(calories);
    }

    public IntegerProperty caloriesProperty(){
        return calories;
    }

    @Override
    public String toString(){
        return this.nomMenu.get() + " " + this.entree.get() + " " + this.plat.get() + " " + this.dessert.get();
    }

}
