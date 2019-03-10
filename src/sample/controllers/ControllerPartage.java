package sample.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.ViewMenus;
import sample.models.ModelMenu;
import sample.models.ModelUtilisateur;

import java.io.IOException;
import java.util.Optional;

public class ControllerPartage extends Controller {

    ModelUtilisateur SelectedUser;

    @FXML
    private TableView tabPartage;

    @FXML
    private TextField textNom;

    @FXML
    private TextField textMail;

    @FXML
    private Button buttonAjouter;

    @FXML
    private TableColumn<ModelUtilisateur, String> colUtilisateurs;

    @FXML
    private TableColumn<ModelUtilisateur, String> colMails;

    private TableColumn colActions;

    public void initialize(){
        super.initialize();
        buttonAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Ajout d'un Utilisateur.");
                alert.setContentText("Êtes vous sûr de vouloir ajouter cette utilisateur à votre liste de partage?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    Main.listeUtilisateurs.add_user(new ModelUtilisateur(textNom.getText(), textMail.getText()));
                }

            }
        });

        colActions = new TableColumn("Action");
        // Création de la colonne action qui contient un bouton supprimer pour chaque ligne
        tabPartage.getColumns().add(colActions);

        tabPartage.setItems(Main.listeUtilisateurs.getUsers());

        tabPartage.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                SelectedUser = (ModelUtilisateur) tabPartage.getSelectionModel().getSelectedItem();
            }
        });

        ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Supprimer");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(SelectedUser != null) {
                    remove_selected_user(SelectedUser);
                }
            }
        });

        // Add MenuItem to ContextMenu
        contextMenu.getItems().addAll(item1);

        // When user right-click on a Menu
        tabPartage.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(tabPartage, event.getScreenX(), event.getScreenY());
            }
        });

        tabPartage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TableView table = (TableView) event.getSource();
                SelectedUser = (ModelUtilisateur) table.getSelectionModel().getSelectedItem();
                contextMenu.hide();
            }
        });

        colActions.setCellValueFactory(new PropertyValueFactory<ModelMenu,Button>("button"));
        colUtilisateurs.setCellValueFactory(c -> c.getValue().nomProperty());
        colMails.setCellValueFactory(c -> c.getValue().mailProperty());



    }
    public void remove_selected_user(ModelUtilisateur userToDelete){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Suppression d'un Utilisateur.");
        alert.setContentText("Êtes vous sûr de vouloir supprimer l'Utilisateur '" + SelectedUser.getNom() + "' ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Main.listeUtilisateurs.remove_user(userToDelete);
        }
    }
}
