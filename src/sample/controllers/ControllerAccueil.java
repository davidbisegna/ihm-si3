package sample.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import sample.Main;
import sample.ViewMenus;
import sample.models.ModelMenu;
import sample.models.ModelPlanifiedMenu;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class ControllerAccueil extends Controller {

    ModelPlanifiedMenu selectedMenu;

    @FXML
    private TableView table_planified_repas;

    @FXML
    private TableColumn<ModelPlanifiedMenu, String> nom_menu;

    @FXML
    private TableColumn<ModelPlanifiedMenu, String> date_repas;

    @FXML
    private TableColumn action;

    @FXML
    private Button new_menu_button;

    @FXML
    private DatePicker date_new_repas;

    @FXML
    private Button button_planify_repas;

    @FXML
    private HBox combo_box_layout;

    public void initialize(){
        super.initialize();

        date_new_repas.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

        ComboBox<ModelMenu> combo_box_repas = new ComboBox<>();
        for(ModelMenu menu : Main.listMenus.getMenus()){
            combo_box_repas.getItems().add(menu);
        }
        combo_box_repas.setPrefWidth(150);
        combo_box_layout.getChildren().add(1,combo_box_repas);
        new_menu_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    loadPage(event,"../"+ ViewMenus.XML_CREERMENU);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button_planify_repas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate date = date_new_repas.getValue();
                Main.listPlanifiedMenus.add_menu(new ModelPlanifiedMenu(combo_box_repas.getValue(), date));
            }
        });

        action = new TableColumn("Action");
        action.setPrefWidth(100);
        // Création de la colonne action qui contient un bouton supprimer pour chaque ligne
        table_planified_repas.getColumns().add(action);

        table_planified_repas.setItems(Main.listPlanifiedMenus.getMenus());

        table_planified_repas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectedMenu = (ModelPlanifiedMenu) table_planified_repas.getSelectionModel().getSelectedItem();
            }
        });


        ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Supprimer");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(selectedMenu != null) {
                    remove_menu(selectedMenu);
                }
            }
        });

        // Add MenuItem to ContextMenu
        contextMenu.getItems().addAll(item1);

        // When user right-click on a Menu
        table_planified_repas.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(table_planified_repas, event.getScreenX(), event.getScreenY());
            }
        });


        table_planified_repas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TableView table = (TableView) event.getSource();
                selectedMenu = (ModelPlanifiedMenu) table.getSelectionModel().getSelectedItem();
                contextMenu.hide();
            }
        });

        nom_menu.setCellValueFactory(c -> c.getValue().nomMenuProperty());
        date_repas.setCellValueFactory(c -> c.getValue().getDateStringProperty());
        action.setCellValueFactory(new PropertyValueFactory<ModelPlanifiedMenu,Button>("button"));
    }

    public void remove_menu(ModelPlanifiedMenu menuToDelete){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Suppression d'un menu");
        alert.setContentText("Êtes vous sûr de vouloir supprimer le menu " + menuToDelete.getNomMenu() + " ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Main.listPlanifiedMenus.remove_menu(menuToDelete);
        }
    }


}
