package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelListOfMenus {

    private ObservableList<ModelMenu> menus;

    public ModelListOfMenus(){
        menus = FXCollections.observableArrayList();
    }

    public ObservableList<ModelMenu> getMenus() {
        return menus;
    }

    public void add_menu(ModelMenu menu){
        menus.add(menu);
    }


}
