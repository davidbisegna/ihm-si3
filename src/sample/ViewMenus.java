package sample;

import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import sample.controllers.Controller;
import sample.controllers.ControllerMenu;
import sample.models.ModelListOfMenus;

import javafx.util.Callback;
import sample.models.ModelMenu;


public class ViewMenus {
    public static final String XML_ACCUEIL = "resources/Accueil_test.fxml";
    public static final String XML_CREERMENU = "resources/CreerMenu.fxml";
    public static final String XML_LISTECOURSEVALIDATION = "resources/Liste_de_course_validation.fxml";
    public static final String XML_MENUAFFICHAGE = "resources/MenuAffichage.fxml";
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;

    private static int rangeSelectedItem = -1;
    private static ModelListOfMenus model;
    private static Controller controller = new Controller();

    public static int getRangeSelectedItem(){
        return rangeSelectedItem;
    }

    void init(ModelListOfMenus model, Controller controller){
        ViewMenus.model = model;
        ViewMenus.controller = controller;
        controller.getMenusListView().setItems(model.getMenus());
        adaptItems( controller.getMenusListView() );
    }

    private void adaptItems(ListView listView){
        listView.setCellFactory(new Callback<ListView, ListCell>(){
            @Override
            public ListCell call(ListView listView) {
                return new ListCell() {
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            //create the person controller
                            ControllerMenu menuController = new ControllerMenu();
                            //initialize the person controller
                            // Display content of the fxml file
                            adaptItems(listView);
                        }
                    }
                };
            }
        });
    }

}
