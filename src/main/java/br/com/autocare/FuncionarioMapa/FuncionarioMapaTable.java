package br.com.autocare.FuncionarioMapa;

import br.com.autocare.components.DeleteConfimAlert;
import br.com.autocare.model.FuncionarioMapa;
import br.com.autocare.model.Model;
import br.com.autocare.FuncionarioMapa.FuncionarioMapaForm;
import br.com.autocare.FuncionarioMapa.FuncionarioMapaTableButtonsCell;
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

public class FuncionarioMapaTable {

    private final TableView<FuncionarioMapa> table;
    private final ObservableList<FuncionarioMapa> dados;

    private FuncionarioMapaForm form;

    public FuncionarioMapaTable() {
        this.table = new TableView<>();
        this.dados = FXCollections.observableArrayList();
    }

    public void setForm(FuncionarioMapaForm form) {
        this.form = form;
    }

    public Node getTableNode() {
        Label titulo = new Label("Listagem");
        titulo.setFont(Font.font("Inter", FontWeight.BOLD, 16));
        titulo.setMaxWidth(Double.MAX_VALUE);

        VBox content = new VBox(10);
        content.setPadding(new Insets(5, 15, 15, 15));
        content.setFillWidth(true);
        content.setMaxWidth(Double.MAX_VALUE);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMaxWidth(Double.MAX_VALUE);
        table.setPrefWidth(1920);

        // Colunas
        TableColumn<FuncionarioMapa, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<FuncionarioMapa, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));



        TableColumn<FuncionarioMapa, String> telefoneColumn = new TableColumn<>("Telefone");
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        TableColumn<FuncionarioMapa, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<FuncionarioMapa, Void> acaoColumn = getAcoesColumn(this.form);

        table.getColumns().addAll(idColumn, nomeColumn, telefoneColumn, emailColumn, acaoColumn);

        updateDados();

        VBox.setVgrow(table, Priority.ALWAYS);

        content.getChildren().addAll(titulo, table);
        return content;
    }

    private TableColumn<FuncionarioMapa, Void> getAcoesColumn(FuncionarioMapaForm form) {
        TableColumn<FuncionarioMapa, Void> acaoColumn = new TableColumn<>("Ações");
        acaoColumn.setCellFactory(coluna -> new FuncionarioMapaTableButtonsCell(this.form, this));
        return acaoColumn;
    }

    public void updateDados() {
        FuncionarioMapa funcionarioMapa = new FuncionarioMapa();
        ArrayList<Model> lista = funcionarioMapa.select();

        this.dados.clear();

        for (Model item : lista) {
            dados.add((FuncionarioMapa) item);
        }

        table.setItems(dados);
    }
}
