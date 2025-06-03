package br.com.autocare.FuncionarioMapa;

import br.com.autocare.components.DeleteConfimAlert;
import br.com.autocare.model.FuncionarioMapa;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class FuncionarioMapaTableButtonsCell extends TableCell<FuncionarioMapa, Void> {
    private final Button btnEditar = criarBotao("Editar", this::editarFuncionario);
    private final Button btnExcluir = criarBotao("Excluir", this::excluirFuncionario);
    private final HBox box = new HBox(5, btnEditar, btnExcluir);

    private final FuncionarioMapaForm form;
    private final FuncionarioMapaTable table;

    public FuncionarioMapaTableButtonsCell(FuncionarioMapaForm form, FuncionarioMapaTable table) {
        this.form = form;
        this.table = table;
        this.box.setAlignment(Pos.CENTER);
    }

    private Button criarBotao(String texto, Runnable acao) {
        Button botao = new Button(texto);
        botao.setOnAction(e -> acao.run());
        return botao;
    }

    private FuncionarioMapa getFuncionarioAtual() {
        return getTableView().getItems().get(getIndex());
    }

    private void editarFuncionario() {
        if (form != null) {
            form.setFuncionario(getFuncionarioAtual());
        }
    }

    private void excluirFuncionario() {
        if (DeleteConfimAlert.deleteConfirm()) {
            getFuncionarioAtual().delete();
            table.updateDados();

            if (form != null) {
                form.setFuncionario(null);
            }
        }
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(empty ? null : box);
    }
}

