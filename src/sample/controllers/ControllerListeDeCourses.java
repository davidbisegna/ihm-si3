package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import sample.Main;
import sample.ViewMenus;
import sample.models.ModelMenu;
import sample.models.ModelPlanifiedMenu;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.*;

public class ControllerListeDeCourses extends Controller {

    @FXML
    private Button generate_list_button;

    @FXML
    private TextField nb_day_course;

    public void initialize(){
        super.initialize();
        generate_list_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int nb_day = Integer.parseInt(nb_day_course.getText());
                if(generateListeDeCourses(nb_day)){
                    LocalDate date = LocalDate.now();
                    date = date.plusDays(nb_day);
                    String pathFile = ViewMenus.LISTE_COURSE_FILE + "_" + date.getDayOfMonth() + "_" + date.getMonthValue() + "_" + date.getYear() + ".txt";
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Liste de courses créée");
                    alert.setHeaderText("Création d'une liste de course");
                    alert.setContentText("La liste de course a été créée dans le fichier " + pathFile);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Aucun menu disponible");
                    alert.setHeaderText("Création de liste annulé");
                    alert.setContentText("Il n'y a aucun menu planifié pour dans " + nb_day + " jours.");
                    alert.showAndWait();
                }
            }
        });
    }

    public Boolean generateListeDeCourses(int nb_day){
        try{
            LocalDate date = LocalDate.now();
            date = date.plusDays(nb_day);
            String pathFile = ViewMenus.LISTE_COURSE_FILE + "_" + date.getDayOfMonth() + "_" + date.getMonthValue() + "_" + date.getYear() + ".txt";
            HashMap<String, Integer> menusToAdd = new HashMap<>();
            for(ModelPlanifiedMenu m : Main.listPlanifiedMenus.getMenus()){
                ModelMenu menu = m.getMenu();
                if(m.getDate().isBefore(date)){
                    if(menusToAdd.containsKey(menu.getEntree())){
                        int nb_entree = menusToAdd.get(menu.getEntree());
                        menusToAdd.put(menu.getEntree(), new Integer(nb_entree +1));
                    } else {
                        menusToAdd.put(menu.getEntree(), new Integer(1));
                    }
                    if(menusToAdd.containsKey(menu.getPlat())){
                        int nb_plat = menusToAdd.get(menu.getPlat());
                        menusToAdd.put(menu.getPlat(), new Integer(nb_plat +1));
                    } else {
                        menusToAdd.put(menu.getPlat(), new Integer(1));
                    }
                    if(menusToAdd.containsKey(menu.getDessert())){
                        int nb_dessert = menusToAdd.get(menu.getDessert());
                        menusToAdd.put(menu.getDessert(), new Integer(nb_dessert +1));
                    } else {
                        menusToAdd.put(menu.getDessert(), new Integer(1));
                    }
                }
            }
            if(menusToAdd.isEmpty()){
                return false;
            }
            File menusFile = new File(pathFile);
            menusFile.createNewFile();

            StringBuilder stringToWriteInFile = new StringBuilder("");
            for(Map.Entry<String, Integer> entry : menusToAdd.entrySet()){
                stringToWriteInFile.append(entry.getKey());
                stringToWriteInFile.append(" x");
                stringToWriteInFile.append(entry.getValue());
                stringToWriteInFile.append("\n");

            }
            System.out.println("string : " + stringToWriteInFile.toString());
            FileWriter writer = new FileWriter(pathFile);

            writer.write(stringToWriteInFile.toString());
            writer.flush();
            writer.close();



        } catch (Exception e){
            System.err.println("Error : " + e.getMessage());
        }
        return true;
    }
}
