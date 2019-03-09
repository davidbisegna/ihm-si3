package sample;

import javafx.beans.InvalidationListener;
import javafx.css.PseudoClass;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import sample.models.ModelMenu;

public class WarningRowFactory implements Callback<TableView<ModelMenu>, TableRow<ModelMenu>> {

    private static final PseudoClass WARNING = PseudoClass.getPseudoClass("warning");

    @Override
    public TableRow<ModelMenu> call(TableView<ModelMenu> table) {
        return new TableRow<ModelMenu>() {

            private final InvalidationListener listener = observable -> pseudoClassStateChanged(WARNING, getItem() != null && getItem().isWarning());

            @Override
            public void updateItem(ModelMenu item, boolean empty) {
                if (getItem() != null) {
                    getItem().warningProperty().removeListener(listener);
                }

                super.updateItem(item, empty);

                if (item != null) {
                    item.warningProperty().addListener(listener);
                }

                listener.invalidated(null);
            }
        };
    }
}
