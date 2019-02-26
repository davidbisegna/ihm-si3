package sample;

import sample.controllers.Controller;
import sample.models.ModelListOfMenus;


public class ViewMenus {
    public static final String XML_ACCUEIL = "resources/Accueil_test.fxml";
    public static final String XML_CREERMENU = "resources/CreerMenu.fxml";
    public static final String XML_LISTECOURSEVALIDATION = "resources/Liste_de_course_validation.fxml";
    public static final String XML_MENUAFFICHAGE = "resources/MenuAffichage.fxml";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private static int rangeSelectedItem = -1;
    private static ModelListOfMenus model;
    private static Controller controller = new Controller();

    public static int getRangeSelectedItem(){
        return rangeSelectedItem;
    }

    void init(ModelListOfMenus model, Controller controller){
        ViewMenus.model = model;
        ViewMenus.controller = controller;
        //controller.getMenusListView().setItems(model.getMenus());
    }

    /*private void adaptItems(ListView listView){
        listView.setCellFactory(new Callback<ListView, ListCell>(){
            @Override
            public ListCell call(ListView listView) {
                return new ListCell() {
                    @Override
                    protected void updateItem(Object item, boolean empty){
                        super.updateItem(item, empty);
                        if(item != null) {
                            Parent listElement = null;
                        }
                    }
                }
            }

        });
    }*/

}
