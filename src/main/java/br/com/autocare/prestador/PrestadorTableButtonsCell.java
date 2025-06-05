package br.com.autocare.prestador;

import br.com.autocare.components.DeleteConfimAlert;
import br.com.autocare.model.Prestador;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class PrestadorTableButtonsCell extends TableCell<Prestador, Void> {
    private final Button btnEditar = criarBotao("Editar", this::editarPrestador);
    private final Button btnExcluir = criarBotao("Excluir", this::excluirPrestador);
    private final HBox box = new HBox(5, btnEditar, btnExcluir);

    private final PrestadorForm form;
    private final PrestadorTable table;

    public PrestadorTableButtonsCell(PrestadorForm form, PrestadorTable table) {
        this.form = form;
        this.table = table;
        this.box.setAlignment(Pos.CENTER);
    }

    private Button criarBotao(String texto, Runnable acao) {
        Button botao = new Button(texto);
        botao.setOnAction(e -> acao.run());
        return botao;
    }

    private Prestador getPrestadorAtual() {
        return getTableView().getItems().get(getIndex());
    }

    private void editarPrestador() {
        if (form != null) {
            form.setPrestador(getPrestadorAtual());
        }
    }

    private void excluirPrestador() {
        if (DeleteConfimAlert.deleteConfirm()) {
            getPrestadorAtual().delete();
            table.updateDados();

            if (form != null) {
                form.setPrestador(null);
            }
        }
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(empty ? null : box);
    }
}
