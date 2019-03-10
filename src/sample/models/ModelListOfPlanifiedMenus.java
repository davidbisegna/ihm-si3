package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import sample.Main;

import java.time.LocalDate;

public class ModelListOfPlanifiedMenus {

    private ObservableList<ModelPlanifiedMenu> planifiedMenus;
    private ModelPlanifiedMenu ProchainRepas = new ModelPlanifiedMenu(new ModelMenu("Aucun","vide","vide","vide",0.0,0),LocalDate.of(3000,12,12));

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

    public ModelPlanifiedMenu nextPlanifiedMenu(){
        ModelPlanifiedMenu nextMenu = ProchainRepas;
        for (ModelPlanifiedMenu currentMenu : Main.listPlanifiedMenus.getMenus()) {
            if (currentMenu.getDate().isBefore(nextMenu.getDate())){
                nextMenu = currentMenu;
            }
        }
        return nextMenu;
    }

}
