package br.com.autocare.components;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FormControl {
    private final String label;
    private TextField textField;
    private Node customInput; // NEW: Support for custom inputs

    public FormControl(String label) {
        this.label = label;
    }

    // NEW: Method to set custom input components
    public void setCustomInput(Node customInput) {
        this.customInput = customInput;
    }

    public Node getInput() {
        if (customInput != null) {
            return buildControl(customInput);
        }
        this.textField = createTextField(false);
        return buildControl(this.textField);
    }

    public Node getPasswordInput() {
        this.textField = createTextField(true);
        return buildControl(this.textField);
    }

    public String getValue() {
        return this.textField != null ? this.textField.getText() : "";
    }

    public void setValue(String value) {
        if (this.textField != null) {
            this.textField.setText(value);
        }
    }

    private TextField createTextField(boolean isPassword) {
        TextField field = isPassword ? new PasswordField() : new TextField();
        field.setFont(Font.font("Inter", FontWeight.NORMAL, 12));
        field.setPrefWidth(240.0);
        return field;
    }

    private Node buildControl(Node field) {
        Label lbl = new Label(this.label);
        lbl.setFont(Font.font("Inter", FontWeight.BOLD, 12));
        lbl.setMinWidth(96.0);

        VBox control = new VBox(2, lbl, field);
        control.setAlignment(Pos.CENTER_LEFT);
        return control;
    }
}