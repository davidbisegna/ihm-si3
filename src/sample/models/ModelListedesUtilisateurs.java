package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelListedesUtilisateurs {

    private ObservableList<ModelUtilisateur> Users;

    public ModelListedesUtilisateurs(){
        Users = FXCollections.observableArrayList();
    }

    public ObservableList<ModelUtilisateur> getUsers() {
        return Users;
    }

    public void add_user(ModelUtilisateur user){
        Users.add(user);
    }

    public void remove_user(ModelUtilisateur user){
        Users.remove(user);
    }

}
