package br.com.autocare.usuario;

import br.com.autocare.components.control.FormControl;
import br.com.autocare.model.Model;
import br.com.autocare.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class UsuarioTable {
    public Node getTable() {
        Label titulo = new Label("Listagem");
        titulo.setFont(Font.font("Inter", FontWeight.BOLD, 16));
        titulo.setMaxWidth(Double.MAX_VALUE);

        VBox content = new VBox(10);
        content.setPadding(new Insets(5, 15, 15, 15));
        content.setFillWidth(true);  // Permite que filhos usem a largura completa
        content.setMaxWidth(Double.MAX_VALUE);

        Usuario usuario = new Usuario();
        ArrayList<Model> lista = usuario.select();

        TableView<Usuario> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Faz as colunas se ajustarem automaticamente
        table.setMaxWidth(Double.MAX_VALUE);
        table.setPrefWidth(900);

        // Colunas
        TableColumn<Usuario, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Usuario, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Usuario, String> sobrenomeColumn = new TableColumn<>("Sobrenome");
        sobrenomeColumn.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));

        TableColumn<Usuario, String> telefoneColumn = new TableColumn<>("Telefone");
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        TableColumn<Usuario, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Coluna de ações
        TableColumn<Usuario, Void> acaoColumn = new TableColumn<>("Ações");
        acaoColumn.setCellFactory(coluna -> new TableCell<>() {
            private final Button btnEditar = new Button("Editar");
            private final Button btnExcluir = new Button("Excluir");
            private final HBox box = new HBox(5, btnEditar, btnExcluir);

            {
                box.setAlignment(Pos.CENTER);
                btnEditar.setOnAction(event -> {
                    Usuario usuario = getTableView().getItems().get(getIndex());
                    // ação editar
                });

                btnExcluir.setOnAction(event -> {
                    Usuario usuario = getTableView().getItems().get(getIndex());
                    // ação excluir
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : box);
            }
        });

        // Adiciona colunas
        table.getColumns().addAll(idColumn, nomeColumn, sobrenomeColumn, telefoneColumn, emailColumn, acaoColumn);

        // Dados
        ObservableList<Usuario> dados = FXCollections.observableArrayList();
        for (Model item : lista) {
            dados.add((Usuario) item);
        }

        table.setItems(dados);

        // Faz com que a tabela ocupe toda a largura disponível no VBox
        VBox.setVgrow(table, Priority.ALWAYS);

        content.getChildren().addAll(titulo, table);
        return content;
    }

}
