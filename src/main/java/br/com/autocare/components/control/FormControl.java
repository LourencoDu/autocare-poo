package br.com.autocare.components.control;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FormControl {
    private String label;
    private TextField textField;

    public FormControl(String label) {
        this.label = label;
    }

    public Node getInput() {
        Label label1 = new Label(this.label + ":");
        label1.setFont(Font.font("Inter", FontWeight.BOLD, 14));
        label1.setMinWidth(120.0);

        this.textField = new TextField();
        this.textField.setFont(Font.font("Inter", FontWeight.NORMAL, 14));
        this.textField.setPrefWidth(480.0);
        this.textField.setPrefColumnCount(10);

        HBox control = new HBox();
        control.getChildren().addAll(label1, this.textField);
        control.setSpacing(10);
        control.setAlignment(Pos.CENTER_LEFT);

        return control;
    }

    public String getValue() {
        return this.textField.getText();
    }
}
