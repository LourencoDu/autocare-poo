package br.com.autocare.FuncionarioMapa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class FuncionarioMapaDropBox {
    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "Option 1",
                    "Option 2",
                    "Option 3"
            );
    final ComboBox comboBox = new ComboBox(options);
}
