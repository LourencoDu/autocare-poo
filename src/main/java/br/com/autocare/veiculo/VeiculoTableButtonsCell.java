package br.com.autocare.veiculo;

import br.com.autocare.components.DeleteConfimAlert;
import br.com.autocare.model.Veiculo;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class VeiculoTableButtonsCell extends TableCell<Veiculo, Void> {
    private final Button btnEditar = criarBotao("Editar", this::editarVeiculo);
    private final Button btnExcluir = criarBotao("Excluir", this::excluirVeiculo);
    private final HBox box = new HBox(5, btnEditar, btnExcluir);

    private final VeiculoForm form;
    private final VeiculoTable table;

    public VeiculoTableButtonsCell(VeiculoForm form, VeiculoTable table) {
        this.form = form;
        this.table = table;
        this.box.setAlignment(Pos.CENTER);
    }

    private Button criarBotao(String texto, Runnable acao) {
        Button botao = new Button(texto);
        botao.setOnAction(e -> acao.run());
        return botao;
    }

    private Veiculo getVeiculoAtual() {
        return getTableView().getItems().get(getIndex());
    }

    private void editarVeiculo() {
        if (form != null) {
            form.setVeiculo(getVeiculoAtual());
        }
    }

    private void excluirVeiculo() {
        if (DeleteConfimAlert.deleteConfirm()) {
            getVeiculoAtual().delete();
            table.updateDados();

            if (form != null) {
                form.setVeiculo(null);
            }
        }
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(empty ? null : box);
    }
}
