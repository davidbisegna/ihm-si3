package sample.models;

import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import sample.Main;

import java.util.Optional;

public class ModelUtilisateur {
    private StringProperty Nom;
    private StringProperty Mail;
    private Button button;

    public ModelUtilisateur(String nom, String mail) {
        this.Nom = new SimpleStringProperty(nom);
        this.Mail = new SimpleStringProperty(mail);
        button = new Button("Supprimer");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Suppression d'un Utilisateur.");
                alert.setContentText("Êtes vous sûr de vouloir supprimer cette utilisateur de votre liste de partage?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    selfRemoveFromObservable();
                }

            }
        });
    }

    public String getNom() {
        return Nom.get();
    }

    public void setNom(String nom) {
        this.Nom = new SimpleStringProperty(nom);
    }

    public StringProperty nomProperty(){
        return Nom;
    }


    public String getMail() {
        return Mail.get();
    }

    public void setMail(String mail) {
        this.Mail = new SimpleStringProperty(mail);
    }

    public StringProperty mailProperty(){ return Mail; }


    public void setButton(Button button){
        this.button = button;
    }

    public Button getButton(){
        return button;
    }



    public void selfRemoveFromObservable(){
        Main.listeUtilisateurs.remove_user(this);
    }
    }
