package br.com.autocare.usuario;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UsuarioController {
    private UsuarioForm form;
    private UsuarioTable table;

    public UsuarioController() {
        this.form = new UsuarioForm();
        this.table = new UsuarioTable();
    }

    private HBox getTitulo() {
        HBox tituloBox = new HBox();

        Label tituloLabel = new Label("Classe Usuário");
        tituloLabel.setFont(Font.font("Inter", FontWeight.BOLD, 20));

        tituloBox.getChildren().addAll(tituloLabel);

        return tituloBox;
    }

    public Node show() {
        VBox container = new VBox(5);
        container.setPadding(new Insets(5, 15, 15, 15));
        container.setPrefWidth(Double.MAX_VALUE);

        HBox tituloBox = this.getTitulo();

        HBox content = new HBox(15);
        content.setMaxWidth(Double.MAX_VALUE);                 // permite expansão
        HBox.setHgrow(content, Priority.ALWAYS);               // para crescer horizontalmente
        VBox.setVgrow(content, Priority.ALWAYS);               // se quiser crescer em altura
        content.setBorder(new Border(new BorderStroke(
                Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(6.0), null
        )));

        Node form = this.form.getForm();
        Node table = this.table.getTable();

        content.getChildren().addAll(form, table);

        container.getChildren().addAll(tituloBox, content);
        ScrollPane scrollPane = new ScrollPane(container);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        return scrollPane;
    }

}
