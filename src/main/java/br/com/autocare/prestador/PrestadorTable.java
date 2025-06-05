package br.com.autocare.prestador;

import br.com.autocare.components.DeleteConfimAlert;
import br.com.autocare.model.Model;
import br.com.autocare.model.Prestador;
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

public class PrestadorTable {

    private final TableView<Prestador> table;
    private final ObservableList<Prestador> dados;

    private PrestadorForm form;

    public PrestadorTable() {
        this.table = new TableView<>();
        this.dados = FXCollections.observableArrayList();
    }

    public void setForm(PrestadorForm form) {
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
        TableColumn<Prestador, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Prestador, String> nomeOficinaColumn = new TableColumn<>("Nome da Oficina");
        nomeOficinaColumn.setCellValueFactory(new PropertyValueFactory<>("nomeOficina"));

        TableColumn<Prestador, String> cepColumn = new TableColumn<>("CEP");
        cepColumn.setCellValueFactory(new PropertyValueFactory<>("cep"));


        TableColumn<Prestador, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Prestador, Void> acaoColumn = getAcoesColumn(this.form);

        // Adiciona colunas
        table.getColumns().addAll(idColumn, nomeOficinaColumn, cepColumn, emailColumn, acaoColumn);

        updateDados();

        // Faz com que a tabela ocupe toda a largura disponível no VBox
        VBox.setVgrow(table, Priority.ALWAYS);

        content.getChildren().addAll(titulo, table);
        return content;
    }

    private TableColumn<Prestador, Void> getAcoesColumn(PrestadorForm form) {
        // Coluna de ações
        PrestadorTable table = this;

        TableColumn<Prestador, Void> acaoColumn = new TableColumn<>("Ações");
        acaoColumn.setCellFactory(coluna -> new PrestadorTableButtonsCell(this.form, this));

        return acaoColumn;
    }

    public void updateDados() {
        Prestador prestador = new Prestador();
        ArrayList<Model> listaPrestador = prestador.select();

        this.dados.clear();

        for (Model item : listaPrestador) {
            dados.add((Prestador) item);
        }

        table.setItems(dados);
    }
}
