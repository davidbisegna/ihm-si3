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

    public void remove_menu(ModelMenu menu){
        menus.remove(menu);
        Boolean hasEncounteredFirst = false;
        for(ModelMenu m : this.menus){
            if(m.getNomMenu().equals(menu.getNomMenu())){
                if(hasEncounteredFirst){
                    m.setWarning(true);
                } else {
                    m.setWarning(false);
                    hasEncounteredFirst = true;
                }
            }
        }
    }


}
