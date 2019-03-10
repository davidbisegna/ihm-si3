package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.time.LocalDate;

public class ModelListOfPlanifiedMenus {

    private ObservableList<ModelPlanifiedMenu> planifiedMenus;

    public ModelListOfPlanifiedMenus(){
        planifiedMenus = FXCollections.observableArrayList();
    }

    public ObservableList<ModelPlanifiedMenu> getMenus() {
        return planifiedMenus;
    }

    public void add_menu(ModelPlanifiedMenu planifiedMenu){
        planifiedMenus.add(planifiedMenu);
    }

    public void remove_menu(ModelPlanifiedMenu menu){
        planifiedMenus.remove(menu);
    }

}
