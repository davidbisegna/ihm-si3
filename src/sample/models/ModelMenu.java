package sample.models;

import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import sample.Main;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

public class ModelMenu {
    private StringProperty nomMenu;
    private StringProperty entree;
    private StringProperty plat;
    private StringProperty dessert;
    private DoubleProperty prix;
    private IntegerProperty calories;
    private Button button;
    private BooleanProperty warning;

    public ModelMenu(ModelMenu menu){
        this.nomMenu = menu.nomMenuProperty();
        this.entree = menu.entreeProperty();
        this.plat = menu.platProperty();
        this.dessert = menu.dessertProperty();
        this.prix = menu.prixProperty();
        this.calories = menu.caloriesProperty();
        this.warning = menu.warningProperty();
        for(ModelMenu m : Main.listMenus.getMenus()){
            if(m.getNomMenu().equals(menu.getNomMenu())){
                this.warning = new SimpleBooleanProperty(true);
            }
        }
        this.initButton();
    }

    public ModelMenu(String nomMenu, String entree, String plat, String dessert, double prix, int calories){
        this.nomMenu = new SimpleStringProperty(nomMenu);
        this.entree = new SimpleStringProperty(entree);
        this.plat = new SimpleStringProperty(plat);
        this.dessert = new SimpleStringProperty(dessert);
        this.prix = new SimpleDoubleProperty(prix);
        this.calories = new SimpleIntegerProperty(calories);
        this.warning = new SimpleBooleanProperty(false);
        for(ModelMenu menu : Main.listMenus.getMenus()){
            if(menu.getNomMenu().equals(nomMenu)){
                this.warning = new SimpleBooleanProperty(true);
            }
        }
        this.initButton();
    }

    public void initButton(){
        button = new Button("Supprimer");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Suppression d'un menu");
                alert.setContentText("Êtes vous sûr de vouloir supprimer ce menu ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    selfRemoveFromObservable();
                }

            }
        });
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

    public void setButton(Button button){
        this.button = button;
    }

    public Button getButton(){
        return button;
    }

    public Boolean isWarning(){ return this.warning.getValue(); }

    public BooleanProperty warningProperty(){ return this.warning; }

    public void setWarning(Boolean b){ this.warning.setValue(b);}

    public void selfRemoveFromObservable(){
        Main.listMenus.remove_menu(this);
    }

    @Override
    public String toString(){
        return this.nomMenu.get();
    }

}
