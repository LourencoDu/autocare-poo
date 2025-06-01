package br.com.autocare.usuario;

import br.com.autocare.components.DeleteConfimAlert;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class UsuarioTable {

    private final TableView<Usuario> table;
    private final ObservableList<Usuario> dados;

    private UsuarioForm form;

    public UsuarioTable() {
        this.table = new TableView<>();
        this.dados = FXCollections.observableArrayList();
    }

    public void setForm(UsuarioForm form) {
        this.form = form;
    }

    public Node getTableNode() {
        Label titulo = new Label("Listagem");
        titulo.setFont(Font.font("Inter", FontWeight.BOLD, 16));
        titulo.setMaxWidth(Double.MAX_VALUE);

        VBox content = new VBox(10);
        content.setPadding(new Insets(5, 15, 15, 15));
        content.setFillWidth(true);  // Permite que filhos usem a largura completa
        content.setMaxWidth(Double.MAX_VALUE);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Faz as colunas se ajustarem automaticamente
        table.setMaxWidth(Double.MAX_VALUE);
        table.setPrefWidth(1920);

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

        TableColumn<Usuario, Void> acaoColumn = getAcoesColumn(this.form);

        // Adiciona colunas
        table.getColumns().addAll(idColumn, nomeColumn, sobrenomeColumn, telefoneColumn, emailColumn, acaoColumn);

        updateDados();

        // Faz com que a tabela ocupe toda a largura disponível no VBox
        VBox.setVgrow(table, Priority.ALWAYS);

        content.getChildren().addAll(titulo, table);
        return content;
    }

    private TableColumn<Usuario, Void> getAcoesColumn(UsuarioForm form) {
        // Coluna de ações
        UsuarioTable table = this;

        TableColumn<Usuario, Void> acaoColumn = new TableColumn<>("Ações");
        acaoColumn.setCellFactory(coluna -> new UsuarioTableButtonsCell(this.form, this));

        return acaoColumn;
    }

    public void updateDados() {
        Usuario usuario = new Usuario();
        ArrayList<Model> lista = usuario.select();

        this.dados.clear();

        for (Model item : lista) {
            dados.add((Usuario) item);
        }

        table.setItems(dados);
    }
}
