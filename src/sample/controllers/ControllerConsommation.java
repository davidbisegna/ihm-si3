package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import sample.Main;
import sample.models.ModelMenu;
import sample.models.ModelPlanifiedMenu;
import java.time.LocalDate;

import java.util.HashMap;


public class ControllerConsommation extends Controller{

    private HashMap<String, Integer> theMap;

    @FXML
    private DatePicker start_date;

    @FXML
    private DatePicker end_date;

    @FXML
    private Button compute_dates_button;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label labelCalories;

    @FXML
    private Label labelPrix;

    public void initialize(){
        super.initialize();
        compute_dates_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                labelCalories.setText("Calories : " + computeSumCalories());
                labelPrix.setText("Prix : " + computeSumPrices());
                LocalDate first_date = start_date.getValue();
                LocalDate last_date = end_date.getValue();
                if(!generateChart(first_date, last_date)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Aucun menu disponible");
                    alert.setHeaderText("Création de graphique annulé");
                    alert.setContentText("Il n'y a aucun menu enregistré dans l'intervalle sélectionné.");
                    alert.showAndWait();
                }
            }
        });
    }

    public boolean generateChart(LocalDate date_begin, LocalDate date_end){
        HashMap<String, Integer> theMap = new HashMap<>();
        ObservableList<ModelPlanifiedMenu> listOfMenus = Main.listPlanifiedMenus.getMenus();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int val;

        for (ModelPlanifiedMenu aMenu : listOfMenus){
            if(isMenuInInterval(aMenu)){
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

        if(theMap.isEmpty()){
            return false;
        }

        for (String key : theMap.keySet()) {
            PieChart.Data data = new PieChart.Data(key, theMap.get(key));
            pieChartData.add(data);
        }
        pieChart.setData(pieChartData);

        return true;
    }

    public float computeSumCalories() {
        float res = 0;
        ObservableList<ModelPlanifiedMenu> listOfMenus = Main.listPlanifiedMenus.getMenus();
        for (ModelPlanifiedMenu aMenu : listOfMenus){
            if(isMenuInInterval(aMenu)){
                res += aMenu.getCalories();
            }
        }
        return res;
    }

    public float computeSumPrices() {
        float res = 0;
        ObservableList<ModelPlanifiedMenu> listOfMenus = Main.listPlanifiedMenus.getMenus();
        for (ModelPlanifiedMenu aMenu : listOfMenus){
            if(isMenuInInterval(aMenu)) {
                res += aMenu.getPrix();
            }
        }
        return res;
    }

    public boolean isMenuInInterval(ModelPlanifiedMenu menu){
        return menu.getDate().isAfter(start_date.getValue()) && menu.getDate().isBefore(end_date.getValue()) || menu.getDate().equals(start_date.getValue()) || menu.getDate().equals(end_date.getValue());
    }
}
