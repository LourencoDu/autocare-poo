package br.com.autocare.veiculo;

import br.com.autocare.components.DeleteConfimAlert;
import br.com.autocare.model.Model;
import br.com.autocare.model.Veiculo;
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

public class VeiculoTable {

    private final TableView<Veiculo> table;
    private final ObservableList<Veiculo> dados;

    private VeiculoForm form;

    public VeiculoTable() {
        this.table = new TableView<>();
        this.dados = FXCollections.observableArrayList();
    }

    public void setForm(VeiculoForm form) {
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
        TableColumn<Veiculo, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Veiculo, String> modeloColumn = new TableColumn<>("Modelo");
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<Veiculo, String> placaColumn = new TableColumn<>("Placa");
        placaColumn.setCellValueFactory(new PropertyValueFactory<>("placa"));


        TableColumn<Veiculo, String> corColumn = new TableColumn<>("Cor");
        corColumn.setCellValueFactory(new PropertyValueFactory<>("cor"));

        TableColumn<Veiculo, Void> acaoColumn = getAcoesColumn(this.form);

        // Adiciona colunas
        table.getColumns().addAll(idColumn, modeloColumn, placaColumn, corColumn, acaoColumn);

        updateDados();

        // Faz com que a tabela ocupe toda a largura disponível no VBox
        VBox.setVgrow(table, Priority.ALWAYS);

        content.getChildren().addAll(titulo, table);
        return content;
    }

    private TableColumn<Veiculo, Void> getAcoesColumn(VeiculoForm form) {
        // Coluna de ações
        VeiculoTable table = this;

        TableColumn<Veiculo, Void> acaoColumn = new TableColumn<>("Ações");
        acaoColumn.setCellFactory(coluna -> new VeiculoTableButtonsCell(this.form, this));

        return acaoColumn;
    }

    public void updateDados() {
        Veiculo veiculo = new Veiculo();
        ArrayList<Model> listaVeiculos = veiculo.select();

        this.dados.clear();

        for (Model item : listaVeiculos) {
            dados.add((Veiculo) item);
        }

        table.setItems(dados);
    }
}
